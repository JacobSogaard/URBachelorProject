package modelClasses;

/**
 * Model for program node classes. Holds the information a program node needs from the user.
 * Class should be filled out through the wizard
 * @author jacob
 *
 */
public class ProgramNodeModel extends NodeModel{
	private String nodeId;
	private boolean setChildrenAllowed = false;
	
	public ProgramNodeModel(String serviceClassname, String viewClassname, String contributionClassname, String nodeId, String nodeTitle) {
		setServiceClassName(serviceClassname);
		setContributionClassName(contributionClassname);
		setViewClassName(viewClassname);
		this.nodeId = nodeId;
		setTitle(nodeTitle);
		
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
	
	@Override
	public String toString() {
		String out = "Service name: " + getServiceClassName() 
				+ "\nView name: " + getViewClassName() 
				+ "\nContribution name: " + getContributionClassName()
				+ "\nNode id: " + this.getNodeId() 
				+ "\nNode title: " + getTitle()
				+ "\nIs children allowed: " + this.isSetChildrenAllowed();
		return out;
	}
}
