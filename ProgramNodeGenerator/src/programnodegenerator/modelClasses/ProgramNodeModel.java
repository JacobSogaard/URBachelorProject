package programnodegenerator.modelClasses;


/**
 * Model for program node classes. Holds the information a program node needs from the user.
 * Class should be filled out through the wizard
 * @author jacob
 *
 */
public class ProgramNodeModel {
	private String serviceClassname, viewClassname, contributionClassname, nodeId, nodeTitle;
	private boolean setChildrenAllowed = false;
	
	public ProgramNodeModel(String serviceClassname, String viewClassname, String contributionClassname, String nodeId, String nodeTitle) {
		this.setServiceClassname(serviceClassname);
		this.setViewClassname(viewClassname);
		this.setContributionClassname(contributionClassname);
		this.setNodeId(nodeId);
		this.setNodeTitle(nodeTitle);
	}

	public String getServiceClassname() {
		return serviceClassname;
	}

	public void setServiceClassname(String serviceClassname) {
		this.serviceClassname = serviceClassname;
	}

	public String getViewClassname() {
		return viewClassname;
	}

	public void setViewClassname(String viewClassname) {
		this.viewClassname = viewClassname;
	}

	public String getContributionClassname() {
		return contributionClassname;
	}

	public void setContributionClassname(String contributionClassname) {
		this.contributionClassname = contributionClassname;
	}

	public String getNodeId() {
		return nodeId;
	}

	public void setNodeId(String nodeId) {
		this.nodeId = nodeId;
	}

	public String getNodeTitle() {
		return nodeTitle;
	}

	public void setNodeTitle(String nodeTitle) {
		this.nodeTitle = nodeTitle;
	}

	public boolean isSetChildrenAllowed() {
		return setChildrenAllowed;
	}

	public void setSetChildrenAllowed(boolean setChildrenAllowed) {
		this.setChildrenAllowed = setChildrenAllowed;
	}
	
	@Override
	public String toString() {
		String out = "Service name: " + this.getServiceClassname() 
				+ "\nView name: " + this.getViewClassname() 
				+ "\nContribution name: " + this.getContributionClassname()
				+ "\nNode id: " + this.getNodeId() 
				+ "\nNode title: " + this.getNodeTitle()
				+ "\nIs children allowed: " + this.isSetChildrenAllowed();
		return out;
	}
}
