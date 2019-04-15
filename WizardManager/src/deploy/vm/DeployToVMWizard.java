package deploy.vm;

import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.jface.dialogs.DialogSettings;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.IDialogSettings;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Cursor;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

/**
 * Sets up the wizard for deploy to URSim on VM wizard page
 * Adds the pages to the wizard
 * @author jacob
 *
 */
public class DeployToVMWizard extends Wizard{
	private DeployToVMWizardPage1 page1;
	
	public DeployToVMWizard() {
		super();
		setNeedsProgressMonitor(true);
	}

	
	/**
	 * Adds the pages to the wizard. 
	 */
	@Override
	public void addPages() {
		page1 = new DeployToVMWizardPage1();
		addPage(page1);
	}
	
	/**
	 * Takes the program node information filled out in the wizard and creates a 
	 * programNodeModel class from them 
	 */
	@Override
	public boolean performFinish() {
		//System.out.println("Finished deploy to VM wizard page");
		//IDialogSettings settings = new DialogSettings("root");
		//settings.put("VMHost", page1.getHost());
		//settings.get
		return true;
	}

}
