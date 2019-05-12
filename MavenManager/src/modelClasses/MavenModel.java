package modelClasses;

public abstract class MavenModel {

	private String archetypeGroupId, archetypeArtifactId, archetypeVersionAPI, archetypeVersion, projectGroupId, projectArtifactId,
			projectVersion, projectPath, mavenGoal;

	public MavenModel() {
	}

	public String getArchetypeGroupId() {
		return archetypeGroupId;
	}

	public void setArchetypeGroupId(String archetypeGroupId) {
		this.archetypeGroupId = archetypeGroupId;
	}

	public String getArchetypeArtifactId() {
		return archetypeArtifactId;
	}

	public void setArchetypeArtifactId(String archetypeArtifactId) {
		this.archetypeArtifactId = archetypeArtifactId;
	}

	public String getArchetypeVersionAPI() {
		return archetypeVersionAPI;
	}

	public void setArchetypeVersionAPI(String archetypeVersionAPI) {
		this.archetypeVersionAPI = archetypeVersionAPI;
	}
	
	public String getArchetypeVersion() {
		return archetypeVersion;
	}

	public void setArchetypeVersion(String archetypeVersion) {
		this.archetypeVersion = archetypeVersion;
	}

	public String getProjectGroupId() {
		return projectGroupId;
	}

	public void setProjectGroupId(String projectGroupId) {
		this.projectGroupId = projectGroupId;
	}

	public String getProjectArtifactId() {
		return projectArtifactId;
	}

	public void setProjectArtifactId(String projectArtifactId) {
		this.projectArtifactId = projectArtifactId;
	}

	public String getProjectVersion() {
		return projectVersion;
	}

	public void setProjectVersion(String projectVersion) {
		this.projectVersion = projectVersion;
	}

	public String getProjectPath() {
		projectPath.replace("\\", "\\\\");
		return projectPath;
	}

	public void setProjectPath(String projectPath) {
		this.projectPath = projectPath;
	}

	public String getMavenGoal() {
		return mavenGoal;
	}

	public void setMavenGoal(String mavenGoal) {
		this.mavenGoal = mavenGoal;
	}
	
	

}
