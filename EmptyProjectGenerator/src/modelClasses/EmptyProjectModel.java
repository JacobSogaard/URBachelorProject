package modelClasses;

import java.util.Properties;

/**
 * Model class for the project to be generated. Has the groupId, artifactId, apiVersion, project path,
 * UR api version and the project version number. 
 * @author jacob
 *
 */
public class EmptyProjectModel extends URCapProjectModel {

	private static final String ARCHYTYPE_GROUPID = "com.ur.urcap";
	private static final String ARCHYTYPE_ARTIFACTID = "archetype";
	private static final String ARCHYTYPE_VERSION = "1.5.0";

	private String apiVersion, packaging, apiVersionRelease, version;

	private Properties properties;
	
	/**
	 * Empty constructor
	 */
	public EmptyProjectModel() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Constructor the set all class fields.
	 * @param groupID
	 * @param artifactID
	 * @param version
	 * @param apiVersion
	 * @param projectPath
	 */
	public EmptyProjectModel(String groupID, String artifactID, String version, String apiVersion, String projectPath) {

		this.groupId = groupID;
		this.artifactId = artifactID;
		this.apiVersion = apiVersion;
		this.projectPath = projectPath;
		this.packaging = groupID + "." + artifactID;
		this.apiVersionRelease = apiVersion;
		this.version = version;
		
		this.setProperties();

	}

	/**
	 * Sets properties for maven project to be generated. 
	 */
	private void setProperties() {
		this.properties = new Properties();
		properties.setProperty("interactiveMode", "false");
		properties.setProperty("archetypeGroupId", ARCHYTYPE_GROUPID);
		properties.setProperty("archetypeArtifactId", ARCHYTYPE_ARTIFACTID);
		properties.setProperty("archetypeVersion", ARCHYTYPE_VERSION);
		properties.setProperty("package", this.packaging+".impl");
		properties.setProperty("groupId", this.groupId);
		properties.setProperty("artifactId", this.artifactId);
		properties.setProperty("apiversion", this.apiVersion);
		properties.setProperty("apiversionRelease", this.apiVersionRelease);
		properties.setProperty("version", this.version);
	}
	
	/**
	 * Sets the default values for generation of UR Cap project
	 * (Should only be used for testing)
	 * TODO Delete this method
	 * @return
	 */
	public Properties setDefaultProperties() {
		Properties propertiesTest = new Properties();
		
		propertiesTest.setProperty("archetypeGroupId", ARCHYTYPE_GROUPID);
		propertiesTest.setProperty("archetypeArtifactId", ARCHYTYPE_ARTIFACTID);
		propertiesTest.setProperty("archetypeVersion", ARCHYTYPE_VERSION);
		propertiesTest.setProperty("package", "com.ur.test02.impl");
		propertiesTest.setProperty("groupId", "com.ur");
		propertiesTest.setProperty("artifactId", "test02");
		propertiesTest.setProperty("apiversion", ARCHYTYPE_VERSION);
		propertiesTest.setProperty("apiversionRelease", ARCHYTYPE_VERSION);
		propertiesTest.setProperty("version", "1.0.0");
		
		return propertiesTest;
	}
	
	/**
	 * Get all properties set for maven project generation
	 * @return Properties of type Properties
	 */
	public Properties getProperties() {
		return this.properties;
	}
	
	/**
	 * Get the path of the generated project
	 * @return path as String
	 */
	public String getProjectPath() {
		return this.projectPath;
	}
	

}
