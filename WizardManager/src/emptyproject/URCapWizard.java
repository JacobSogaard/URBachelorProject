package emptyproject;

import org.eclipse.jface.wizard.Wizard;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.BusyIndicator;
import org.eclipse.swt.graphics.Cursor;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import mavenGenerator.URCapProjectGenerator;
import mavenImport.MavenProjectImporter;
import modelClasses.*;

/**
 * Class which handles pages in wizard.
 * Each page should have it's own class and instances of the should be created here. 
 * @author jacob
 *
 */
public class URCapWizard extends Wizard {

	protected SetupURCapPage urcapSetupPage;
	private EmptyProjectModel projectInfo;
	private static Cursor cursor = null;
	//protected MyPageTwo two;

	public URCapWizard() {
		super();
		this.projectInfo = new EmptyProjectModel();
		setNeedsProgressMonitor(true);
	}

	@Override
	public void addPages() {
		this.urcapSetupPage = new SetupURCapPage();
		addPage(urcapSetupPage);
	}

	@Override
	public boolean performFinish() {
		URCapProjectGenerator urgen = new URCapProjectGenerator();
		Display display = Display.getDefault();
		Cursor waitCursor = new Cursor(display, SWT.CURSOR_WAIT);		
		Shell shell = getShell();
		shell.setCursor(waitCursor);
		
		urgen.generate(urcapSetupPage.getProjectModel()); //Generates project with object model made through NewURCapWizardPage
		
		//MavenProjectImporter importer = new MavenProjectImporter();
		//importer.importProjectAsMavenProject(urcapSetupPage.getProjectModel().getProjectPath());
		
		
		shell.setCursor(null);
		waitCursor.dispose();				
		return true;
	}
}