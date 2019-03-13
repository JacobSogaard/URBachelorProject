package urcaplugin.wizard;

import org.eclipse.jface.wizard.Wizard;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.BusyIndicator;
import org.eclipse.swt.graphics.Cursor;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import urcapproject.Generator.NewProjectModel;
import urcapproject.Generator.urcapGenerator;

/**
 * Class which handles pages in wizard.
 * Each page should have it's own class and instances of the should be created here. 
 * @author jacob
 *
 */
public class URCapWizard extends Wizard {

	protected NewURCapPage one;
	private NewProjectModel projectInfo;
	private static Cursor cursor = null;
	//protected MyPageTwo two;

	public URCapWizard() {
		super();
//		this.projectInfo = new NewProjectModel();
		setNeedsProgressMonitor(true);
	}

	@Override
	public void addPages() {
		one = new NewURCapPage(this.projectInfo);
		//two = new MyPageTwo();
		addPage(one);
		//addPage(two);
	}

	@Override
	public boolean performFinish() {
		urcapGenerator urgen = new urcapGenerator();
		Display display = Display.getDefault();
		Cursor waitCursor = new Cursor(display, SWT.CURSOR_WAIT);		
		Shell shell = getShell();
		shell.setCursor(waitCursor);
		urgen.executeMavenCommand(one.getProjectModel()); //Generates project with object model made through NewURCapWizardPage
		shell.setCursor(null);
		waitCursor.dispose();				
		return true;
	}
}