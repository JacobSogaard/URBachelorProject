package urcapproject.Generator;

import java.util.Properties;

public class NewProjectModel {

	private static final String ARCHYTYPE_GROUPID = "com.ur.urcap";
	private static final String ARCHYTYPE_ARTIFACTID = "archetype";
	private static final String ARCHYTYPE_VERSION = "1.5.0";
	private static final String interativeMood = "false";

	private String groupID;
	private String artifactID;
	private String apiVersion;
	private String projectPAth;
	private String packaging;
	private String apiVersionRelease;

	private Properties properties;
	
	public NewProjectModel() {
		// TODO Auto-generated constructor stub
	}

	public NewProjectModel(String groupID, String artifactID, String apiVersion, String projectPath) {

		this.groupID = groupID;
		this.artifactID = artifactID;
		this.apiVersion = apiVersion;
		this.setProjectPAth(projectPath);
		this.packaging = groupID + "." + artifactID;
		this.apiVersionRelease = apiVersion;
		
		this.setProperties();

	}

	private void setProperties() {
		properties.setProperty("interactiveMode", "false");
		properties.setProperty("archetypeGroupId", this.ARCHYTYPE_GROUPID);
		properties.setProperty("archetypeArtifactId", this.ARCHYTYPE_ARTIFACTID);
		properties.setProperty("archetypeVersion", this.ARCHYTYPE_VERSION);
		properties.setProperty("package", this.packaging+".impl");
		properties.setProperty("groupId", this.groupID);
		properties.setProperty("artifactId", this.artifactID);
		properties.setProperty("apiversion", this.apiVersion);
		properties.setProperty("apiversionRelease", this.apiVersionRelease);
	}
	
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
		
		return propertiesTest;
	}
	
	
	public Properties getProperties() {
		
		return properties;
	}

	public String getProjectPAth() {
		return projectPAth;
	}

	public void setProjectPAth(String projectPAth) {
		this.projectPAth = projectPAth;
	}

}
