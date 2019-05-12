package deploy.vm;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.wizard.Wizard;

import deployModels.DeployURSimVM;
import deployModels.IDeploy;

/**
 * Sets up the wizard for deploy to URSim on VM wizard page Adds the pages to
 * the wizard
 * 
 * @author jacob
 *
 */
public class DeployToVMWizard extends Wizard {
	private DeployToVMWizardPage deployVMWizard;
	private String projectPath, artifactID;

	public DeployToVMWizard(String path, String projectArtifactId) {
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
		deployVMWizard = new DeployToVMWizardPage();
		addPage(deployVMWizard);
	}

	/**
	 * Takes the program node information filled out in the wizard and creates a
	 * programNodeModel class from them
	 */
	@Override
	public boolean performFinish() {
		IDeploy model = new DeployURSimVM(this.deployVMWizard.getIp(), this.deployVMWizard.getUsername(), this.deployVMWizard.getPassword(),
				this.projectPath, this.artifactID);

		String resultMessage = model.deploy();

		MessageDialog.openInformation(this.getShell(), "Deploy Message", resultMessage);

		return true;
	}

}
