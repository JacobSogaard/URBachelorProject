package modelClasses;

import java.util.Properties;

/**
 * Model class for the project to be generated. Has the groupId, artifactId, apiVersion, project path,
 * UR api version and the project version number. 
 * @author jacob
 *
 */
public class URCapProjectModel implements IURCapMaven {

	private Properties properties;
	
	private static final String ARCHETYPE_GROUPID = "com.ur.urcap";
	private static final String ARCHETYPE_ARTIFACTID = "archetype";
	private String packaging;
	private URCapModel mavenModel;
	
	/**
	 * Empty constructor
	 */
	public URCapProjectModel() {
	}

	/**
	 * Constructor the set all class fields.
	 * @param groupID
	 * @param artifactID
	 * @param version
	 * @param apiVersion
	 * @param projectPath
	 */
	public URCapProjectModel(MavenModel mavenModel) {
		this.mavenModel = (URCapModel) mavenModel;
		this.mavenModel.setArchetypeGroupId(ARCHETYPE_GROUPID);
		this.mavenModel.setArchetypeArtifactId(ARCHETYPE_ARTIFACTID);
		this.mavenModel.setMavenGoal("archetype:generate");
		this.packaging = this.mavenModel.getProjectGroupId() + "." + this.mavenModel.getProjectArtifactId() + ".impl";
		
		this.setProperties();

	}

	/**
	 * Sets properties for maven project to be generated. 
	 */
	private void setProperties() {
		this.properties = new Properties();
		properties.setProperty("interactiveMode", "false");
		properties.setProperty("archetypeGroupId", this.mavenModel.getArchetypeGroupId());
		properties.setProperty("archetypeArtifactId", this.mavenModel.getArchetypeArtifactId());
		properties.setProperty("archetypeVersion", this.mavenModel.getArchetypeVersion());
		properties.setProperty("package", this.packaging);
		properties.setProperty("groupId", this.mavenModel.getProjectGroupId());
		properties.setProperty("artifactId", this.mavenModel.getProjectArtifactId());
		properties.setProperty("apiversion", this.mavenModel.getArchetypeVersionAPI());
		properties.setProperty("apiversionRelease", this.mavenModel.getArchetypeVersionAPI());
		properties.setProperty("version", this.mavenModel.getProjectVersion());
	}
	
	/**
	 * Get all properties set for maven project generation
	 * @return Properties of type Properties
	 */
	@Override
	public Properties getProperties() {
		return this.properties;
	}
	
	
	
	/**
	 * Get the path of the generated project
	 * @return path as String
	 */
	@Override
	public String getProjectPath() {
		return this.mavenModel.getProjectPath();
	}

	@Override
	public String getGoal() {
		return this.mavenModel.getMavenGoal();
	}
	

}
