package modelClasses.installationnode;

import modelClasses.MavenModel;

/**
 * Model for program node classes. Holds the information a program node needs
 * from the user. Class should be filled out through the wizard
 * 
 * @author jacob
 *
 */
public class InstallationNodeModel extends MavenModel {

	private String serviceClassName, contributionClassName, viewClassName, nodeTitle;

	public InstallationNodeModel(String serviceClassName, String contributionClassName, String viewClassName, String nodeTitle) {
		super();
		this.serviceClassName = serviceClassName;
		this.contributionClassName = contributionClassName;
		this.viewClassName = viewClassName;
		this.nodeTitle = nodeTitle;
	}

	public String getServiceClassName() {
		return serviceClassName;
	}

	public void setServiceClassName(String serviceClassName) {
		this.serviceClassName = serviceClassName;
	}

	public String getContributionClassName() {
		return contributionClassName;
	}

	public void setContributionClassName(String contributionClassName) {
		this.contributionClassName = contributionClassName;
	}

	public String getViewClassName() {
		return viewClassName;
	}

	public void setViewClassName(String viewClassName) {
		this.viewClassName = viewClassName;
	}

	public String getNodeTitle() {
		return nodeTitle;
	}

	public void setNodeTitle(String nodeTitle) {
		this.nodeTitle = nodeTitle;
	}

}
