package modelClasses;

import java.util.Properties;




/**
 * Model class for maven generation of a program node. Takes the groupid, artifactid which should be found be the wizard when from the selected project,
 * the program node should be generated in. Also takes a path (might not be useful in this case?) and a programNodeModel which the wizard 
 * also should create. 
 * The archetype is set to programnodearchetype. 
 * @author jacob
 *
 */
public class ProgramNodeProjectModel extends MavenProjectModel {

	private static final String ARCHYTYPE_GROUPID = "com.ur.urcap";
	private static final String ARCHYTYPE_ARTIFACTID = "programnodearchetype";
	private static final String ARCHYTYPE_VERSION = "1.0";

	private String version, packaging;
	private ProgramNodeModel programNode;

	private Properties properties;
	
	private String mavenGoal;
	/**
	 * Empty constructor
	 */
	public ProgramNodeProjectModel() {
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
	public ProgramNodeProjectModel(String groupId, String artifactID, String version, String projectPath, NodeModel programNode) {

		this.mavenGoal = "archetype:generate";
		
		this.groupId = groupId;
		this.packaging = groupId;
		this.artifactId = artifactID;
		this.projectPath = projectPath;
		this.version = version;
		
		//Some smarter way to do this!!!!
		this.programNode = (ProgramNodeModel) programNode;
		
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
		properties.setProperty("package", this.packaging);
		properties.setProperty("groupId", this.groupId);
		properties.setProperty("artifactId", this.artifactId);
		properties.setProperty("contributionClassName", this.programNode.getContributionClassName());
		properties.setProperty("serviceClassName", this.programNode.getServiceClassName());
		properties.setProperty("viewClassName", this.programNode.getViewClassName());
		properties.setProperty("isChildrenAllowed", Boolean.toString(this.programNode.isSetChildrenAllowed()));
		properties.setProperty("nodeId", this.programNode.getNodeId());
		properties.setProperty("nodeTitle", this.programNode.getTitle());
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
		return this.projectPath;
	}

	@Override
	public String getGoal() {
		// TODO Auto-generated method stub
		return null;
	}
	

}
