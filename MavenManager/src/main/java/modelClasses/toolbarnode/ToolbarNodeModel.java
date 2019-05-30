package modelClasses.toolbarnode;

import modelClasses.MavenModel;

/**
 * Model for toolbar node classes. Holds the information a toolbar node needs from the user.
 * Class should be filled out through the wizard
 * @author jacob
 *
 */
public class ToolbarNodeModel extends MavenModel{

	private String serviceClassName, contributionClassName, iconPath;

	public ToolbarNodeModel(String iconPath, String serviceClassName, String contributionClassName) {
		super();
		this.serviceClassName = serviceClassName;
		this.contributionClassName = contributionClassName;
		this.iconPath = iconPath;
		
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
	
	public void setIconPath(String iconPath) {
		this.iconPath = iconPath;
	}
	
	public String getIconPath() {
		return this.iconPath;
	}

}
