package deploy.local;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.wizard.Wizard;

import deployModels.DeployURSimLocal;
import deployModels.IDeploy;

/**
 * Sets up the wizard for deploy to robot wizard page Adds the pages to the
 * wizard
 * 
 * @author jacob
 *
 */
public class DeployToLocalWizard extends Wizard {
	private DeployToLocalWizardPage deployLocalWizard;
	private String projectPath, artifactID;

	public DeployToLocalWizard(String path, String projectArtifactId) {
		super();
		setNeedsProgressMonitor(true);
		this.projectPath = path;
		this.artifactID = projectArtifactId;
		
	}
	


	/**
	 * Adds the pages to the wizard.
	 */
	@Override
	public void addPages() {
		deployLocalWizard = new DeployToLocalWizardPage();
		addPage(deployLocalWizard);
		
		//EDITED::
		deployLocalWizard.setProjectPathDeploy(this.projectPath);
	}

	/**
	 * Takes the program node information filled out in the wizard and creates a
	 * programNodeModel class from them
	 */
	@Override
	public boolean performFinish() {
		
		IDeploy model = new DeployURSimLocal(this.deployLocalWizard.getURSimPath(), this.projectPath, this.artifactID);
		String resultMessage = model.deploy();

		MessageDialog.openInformation(this.getShell(), "Deploy Message", resultMessage);

		return true;
	}

}
