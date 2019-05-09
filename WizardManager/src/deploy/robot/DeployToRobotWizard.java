package deploy.robot;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.wizard.Wizard;

import deployModels.DeployToRobot;
import deployModels.IDeploy;

/**
 * Sets up the wizard for deploy to robot wizard page Adds the pages to the
 * wizard
 * 
 * @author jacob
 *
 */
public class DeployToRobotWizard extends Wizard {
	private DeployToRobotWizardPage deployRobotWizard;
	private String projectPath, artifactID;

	public DeployToRobotWizard(String path, String projectArtifactId) {
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
		deployRobotWizard = new DeployToRobotWizardPage();
		addPage(deployRobotWizard);
	}

	/**
	 * Takes the program node information filled out in the wizard and creates a
	 * programNodeModel class from them
	 */
	@Override
	public boolean performFinish() {
		IDeploy model = new DeployToRobot(this.deployRobotWizard.getIp(), this.deployRobotWizard.getUsername(), this.deployRobotWizard.getPassword(),
				this.projectPath, this.artifactID);
		String resultMessage = model.deploy();

		MessageDialog.openInformation(this.getShell(), "Deploy Message", resultMessage);

		return true;
	}

}
