package emptyproject;

import org.eclipse.core.internal.resources.refresh.win32.Convert;
import org.eclipse.core.runtime.IPath;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.BusyIndicator;
import org.eclipse.swt.graphics.Cursor;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.handlers.HandlerUtil;

import mavenGenerator.MavenInvokerHandler;
import mavenImport.MavenProjectImporter;
import modelClasses.*;

/**
 * Class which handles pages in wizard. Each page should have it's own class and
 * instances of the should be created here.
 * 
 * @author jacob
 *
 */
public class URCapWizard extends Wizard {

	protected SetupURCapPage urcapSetupPage;
	private IURCapMaven projectModel;
	private static Cursor cursor = null;
	// protected MyPageTwo two;

	public URCapWizard() {
		super();
		setNeedsProgressMonitor(true);
	}

	@Override
	public void addPages() {
		this.urcapSetupPage = new SetupURCapPage();
		addPage(urcapSetupPage);
	}

	@Override
	public boolean performFinish() {
		MavenInvokerHandler invoker = new MavenInvokerHandler();
		this.projectModel = new URCapProjectModel(urcapSetupPage.getProjectModel());
		Display display = Display.getDefault();
		Cursor waitCursor = new Cursor(display, SWT.CURSOR_WAIT);
		Shell shell = getShell();
		shell.setCursor(waitCursor);

		invoker.invokeMavenExecution(this.projectModel); // Generates project with object model made through

		
		MavenProjectImporter importer = new MavenProjectImporter();
		String message = importer.importProjectAsMavenProject(urcapSetupPage.getProjectModel().getProjectPath(), urcapSetupPage.getProjectModel().getProjectArtifactId());
		
		MessageDialog.openInformation(shell, "Import project message", message);
		
		shell.setCursor(null);
		waitCursor.dispose();
		return true;
	}
}