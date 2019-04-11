package modelClasses;

public abstract class NodeModel {

	private String serviceClassName, viewClassName, contributionClassName, title;
	
	
	
	public void setServiceClassName(String serviceClassName) {
		this.serviceClassName = serviceClassName;
	}
	
	public void setViewClassName(String viewClassName) {
		this.viewClassName = viewClassName;
	}
	
	public void setContributionClassName(String contributionClassName) {
		this.contributionClassName = contributionClassName;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getServiceClassName() {
		if (serviceClassName != "") {
			return serviceClassName;
		}
		return "";
	}
	
	public String getViewClassName() {
		if (viewClassName != "") {
			return viewClassName;
		}
		return "";
	}
	
	public String getContributionClassName() {
		if (contributionClassName != "") {
			return contributionClassName;
		}
		return "";
	}
	
	public String getTitle() {
		if (title != "") {
			return title;
		}
		return "";
	}
}
