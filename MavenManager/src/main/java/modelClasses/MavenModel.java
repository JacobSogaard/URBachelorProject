package modelClasses;

/**
 * Abstract class which has the model for maven request.
 * @author jacob
 *
 */
public abstract class MavenModel {

	private String archetypeGroupId, archetypeArtifactId, archetypeVersionAPI, archetypeVersion, projectGroupId, projectArtifactId,
			projectVersion, projectPath, mavenGoal;

	public MavenModel() {
	}

	/**
	 * Get archetype group id.
	 * @return - Archetype group id as String
	 */
	public String getArchetypeGroupId() {
		return archetypeGroupId;
	}

	/**
	 * Set archetype group id
	 * @param archetypeGroupId - String
	 */
	public void setArchetypeGroupId(String archetypeGroupId) {
		this.archetypeGroupId = archetypeGroupId;
	}

	/**
	 * Get archetype artifact id
	 * @return - String
	 */
	public String getArchetypeArtifactId() {
		return archetypeArtifactId;
	}

	/**
	 * Set archetype artifact id
	 * @param archetypeArtifactId - String
	 */
	public void setArchetypeArtifactId(String archetypeArtifactId) {
		this.archetypeArtifactId = archetypeArtifactId;
	}

	/**
	 * Get version of ur API. This value should return one of the version numbers from the UR API. 
	 * As of may 2019, this is 1.0.0, 1.1.0, 1.2.0, 1.3.0, 1.4.0, 1.5.0 and 1.6.0
	 * @return - String
	 */
	public String getArchetypeVersionAPI() {
		return archetypeVersionAPI;
	}

	/**
	 * Set version of UR API. As of may 2019, this should 1.0.0, 1.1.0, 1.2.0, 1.3.0, 1.4.0, 1.5.0 and 1.6.0
	 * @param archetypeVersionAPI - String
	 */
	public void setArchetypeVersionAPI(String archetypeVersionAPI) {
		this.archetypeVersionAPI = archetypeVersionAPI;
	}
	
	/**
	 * Get archetype version. 
	 * @return - String
	 */
	public String getArchetypeVersion() {
		return archetypeVersion;
	}

	/**
	 * Set archetype version
	 * @param archetypeVersion - String
	 */
	public void setArchetypeVersion(String archetypeVersion) {
		this.archetypeVersion = archetypeVersion;
	}

	/**
	 * Get group id of project.
	 * @return - String
	 */
	public String getProjectGroupId() {
		return projectGroupId;
	}

	/**
	 * Set group id for project
	 * @param projectGroupId - String
	 */
	public void setProjectGroupId(String projectGroupId) {
		this.projectGroupId = projectGroupId;
	}

	/**
	 * Get artifact id of project
	 * @return - String
	 */
	public String getProjectArtifactId() {
		return projectArtifactId;
	}

	/**
	 * Set artifact id of project
	 * @param projectArtifactId - String
	 */
	public void setProjectArtifactId(String projectArtifactId) {
		this.projectArtifactId = projectArtifactId;
	}

	/**
	 * Get version of project
	 * @return - String
	 */
	public String getProjectVersion() {
		return projectVersion;
	}

	/**
	 * Set version of project
	 * @param projectVersion - String
	 */
	public void setProjectVersion(String projectVersion) {
		this.projectVersion = projectVersion;
	}

	/**
	 * Get project path. If path uses backslash as directory seperatory, the backslashes are replaces
	 * by 2 backslashes (Windows machines). This is not done when using forward slash (MacOS and Linux) 
	 * @return - String
	 */
	public String getProjectPath() {
		projectPath.replace("\\", "\\\\");
		return projectPath;
	}

	/**
	 * Set project path
	 * @param projectPath - String
	 */
	public void setProjectPath(String projectPath) {
		this.projectPath = projectPath;
	}

	/**
	 * Get goal for maven command.
	 * @return - String
	 */
	public String getMavenGoal() {
		return mavenGoal;
	}

	/**
	 * Set maven goal
	 * @param mavenGoal - String
	 */
	public void setMavenGoal(String mavenGoal) {
		this.mavenGoal = mavenGoal;
	}
	
	

}
