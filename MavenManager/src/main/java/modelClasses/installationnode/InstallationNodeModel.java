package modelClasses.installationnode;

import modelClasses.MavenModel;

/**
 * Model for installation node classes. Holds the information an instalaltion node needs
 * from the user. Class should be filled out through the wizard.
 * Subclass of MavenModel
 * @author jacob
 *
 */
public class InstallationNodeModel extends MavenModel {

	private String serviceClassName, contributionClassName, viewClassName, nodeTitle;

	/**
	 * Constructor.
	 * @param serviceClassName - String
	 * @param contributionClassName - String
	 * @param viewClassName - String
	 * @param nodeTitle - String
	 */
	public InstallationNodeModel(String serviceClassName, String contributionClassName, String viewClassName, String nodeTitle) {
		super();
		this.serviceClassName = serviceClassName;
		this.contributionClassName = contributionClassName;
		this.viewClassName = viewClassName;
		this.nodeTitle = nodeTitle;
	}

	/**
	 * Get service class name
	 * @return - String
	 */
	public String getServiceClassName() {
		return serviceClassName;
	}

	/**
	 * Set service class name
	 * @param serviceClassName - String 
	 */
	public void setServiceClassName(String serviceClassName) {
		this.serviceClassName = serviceClassName;
	}

	/**
	 * Get contribution class name
	 * @return - String
	 */
	public String getContributionClassName() {
		return contributionClassName;
	}

	/**
	 * Set contribution class name
	 * @param contributionClassName - String
	 */
	public void setContributionClassName(String contributionClassName) {
		this.contributionClassName = contributionClassName;
	}

	/**
	 * Get view class name
	 * @return - String
	 */
	public String getViewClassName() {
		return viewClassName;
	}

	/**
	 * Set view class name
	 * @param viewClassName - String
	 */
	public void setViewClassName(String viewClassName) {
		this.viewClassName = viewClassName;
	}

	/**
	 * Get node title
	 * @return - String
	 */
	public String getNodeTitle() {
		return nodeTitle;
	}

	/**
	 * Set node title
	 * @param nodeTitle - String
	 */
	public void setNodeTitle(String nodeTitle) {
		this.nodeTitle = nodeTitle;
	}

}
