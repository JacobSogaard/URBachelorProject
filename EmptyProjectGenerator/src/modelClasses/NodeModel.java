package modelClasses;

public abstract class NodeModel {

	private String serviceClassName, viewClassName, contributionClassName, title, 
		groupId, artifactId, version, projectPath;
	
	
	
	public NodeModel(String serviceClassName, String viewClassName, String contributionClassName, String title,
			String groupId, String artifactId, String version, String projectPath) {
		this.serviceClassName = serviceClassName;
		this.viewClassName = viewClassName;
		this.contributionClassName = contributionClassName;
		this.title = title;
		this.groupId = groupId;
		this.artifactId = artifactId;
		this.version = version;
		this.projectPath = projectPath;
	}

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public String getArtifactId() {
		return artifactId;
	}

	public void setArtifactId(String artifactId) {
		this.artifactId = artifactId;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getProjectPath() {
		return projectPath;
	}

	public void setProjectPath(String projectPath) {
		this.projectPath = projectPath;
	}

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
