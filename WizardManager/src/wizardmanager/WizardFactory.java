package wizardmanager;

import programnode.ProgramNodeWizard;
import toolbarnode.ToolbarNodeWizard;

import org.eclipse.jface.wizard.Wizard;

import deploy.local.DeployToLocalWizard;
import deploy.robot.DeployToRobotWizard;
import deploy.vm.DeployToVMWizard;
import emptyproject.URCapWizard;
import installationnode.InstallationNodeWizard;


/**
 * Factory class to setup specific wizard type.
 * Returns the wizard of requested type.
 * Wizard type should be set in the main plugin class as a description on the command attached to the specific button. 
 * fx. if the wizard for a program node should be should be called, the description is set on the program node command for the Program Node extension point, 
 * and the exact same is added as a case in the switch case on getWizard method. The case the return a creation call on the specified wizard class.
 * @author jacob
 *
 */
public class WizardFactory implements IWizardFactory {
	/**
	 * Get wizard of specified string type. Types a set as the description on the extension point command. Ex: "ProgramNode" for the program node menu item
	 * Remember to null check when calling the method, since default return is null. 
	 * @param wizardType
	 * @return - New wizard of the specified wizard. If no wizard type match, return null.
	 */
	@Override
	public Wizard getWizard(String wizardType, String projectArtifactId, String projectPath, String groupId) {
		switch (wizardType) {
		case "EmptyProject":
			return new URCapWizard();
		case "ProgramNode":
			return new ProgramNodeWizard(projectArtifactId, projectPath, groupId);
		case "InstallationNode":
			return new InstallationNodeWizard(projectArtifactId, projectPath, groupId);
		case "ToolbarNode":
			return new ToolbarNodeWizard(projectArtifactId, projectPath, groupId);
		case "DeployToRobot":
			return new DeployToRobotWizard(projectPath,projectArtifactId);
		case "DeployLocal":
			return new DeployToLocalWizard(projectPath,projectArtifactId);
		case "DeployToVM":
			return new DeployToVMWizard(projectPath,projectArtifactId);
		default:
			return null;
		}
	
	}
}
