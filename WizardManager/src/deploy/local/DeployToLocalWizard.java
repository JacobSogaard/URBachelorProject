package deploy.local;

import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Cursor;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import deployModels.DeployURSimLocal;
import deployModels.IDeploy;
import mavenGenerator.MavenInvokerHandler;
import modelClasses.IURCapMaven;

/**
 * Sets up the wizard for deploy to robot wizard page Adds the pages to the
 * wizard
 * 
 * @author jacob
 *
 */
public class DeployToLocalWizard extends Wizard {
	private DeployToLocalWizardPage1 page1;
	private String path, artifactID;

	public DeployToLocalWizard(String path, String projectArtifactId) {
		super();
		setNeedsProgressMonitor(true);
		this.path = path;
		this.artifactID = projectArtifactId;
	}

	/**
	 * Adds the pages to the wizard.
	 */
	@Override
	public void addPages() {
		page1 = new DeployToLocalWizardPage1();
		addPage(page1);
	}

	/**
	 * Takes the program node information filled out in the wizard and creates a
	 * programNodeModel class from them
	 */
	@Override
	public boolean performFinish() {
		
		IDeploy model = new DeployURSimLocal(this.page1.getURSimPath(), this.path, this.artifactID);
		String resultMessage = model.deploy();

		MessageDialog.openInformation(this.getShell(), "Deploy Message", resultMessage);

		return true;
	}

}
