package modelClasses.installationnode;

import modelClasses.NodeModel;

/**
 * Model for program node classes. Holds the information a program node needs from the user.
 * Class should be filled out through the wizard
 * @author jacob
 *
 */
public class InstallationNodeModel extends NodeModel{

	public InstallationNodeModel(String serviceClassName, String viewClassName, String contributionClassName, String title,
			String groupId, String artifactId, String version, String projectPath) {
		super(serviceClassName, viewClassName, contributionClassName, title, groupId, artifactId, version, projectPath);

	}

}
