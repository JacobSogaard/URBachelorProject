package modelClasses.programnode;

import modelClasses.MavenModel;

/**
 * Model for program node classes. Holds the information a program node needs from the user.
 * Class should be filled out through the wizard
 * @author jacob
 *
 */
public class ProgramNodeModel extends MavenModel{

	private String serviceClassName, contributionClassName, viewClassName, nodeTitle, nodeId;
	private boolean setChildrenAllowed;

	public ProgramNodeModel(String nodeTitle, String nodeId, boolean setChildrenAllowed, String serviceClassName, 
			String contributionClassName, String viewClassName) {
		super();
		this.serviceClassName = serviceClassName;
		this.contributionClassName = contributionClassName;
		this.viewClassName = viewClassName;
		this.nodeTitle = nodeTitle;
		this.nodeId = nodeId;
		this.setChildrenAllowed = setChildrenAllowed;
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

	public String getNodeId() {
		return nodeId;
	}

	public void setNodeId(String nodeId) {
		this.nodeId = nodeId;
	}
	
	public boolean isSetChildrenAllowed() {
		return setChildrenAllowed;
	}

	public void setSetChildrenAllowed(boolean setChildrenAllowed) {
		this.setChildrenAllowed = setChildrenAllowed;
	}
	
}
