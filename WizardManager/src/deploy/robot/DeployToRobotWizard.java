package deploy.robot;

import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Cursor;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

/**
 * Sets up the wizard for deploy to robot wizard page
 * Adds the pages to the wizard
 * @author jacob
 *
 */
public class DeployToRobotWizard extends Wizard{
	private DeployToRobotWizardPage1 page1;
	
	public DeployToRobotWizard() {
		super();
		setNeedsProgressMonitor(true);
	}

	
	/**
	 * Adds the pages to the wizard. 
	 */
	@Override
	public void addPages() {
		page1 = new DeployToRobotWizardPage1();
		addPage(page1);
	}
	
	/**
	 * Takes the program node information filled out in the wizard and creates a 
	 * programNodeModel class from them 
	 */
	@Override
	public boolean performFinish() {
		System.out.println("Finished deploy to robot wizard");
		return true;
	}

}
