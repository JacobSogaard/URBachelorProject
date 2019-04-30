package modelClasses.programnode;

import java.util.Properties;

import modelClasses.IURCapMaven;
import modelClasses.MavenModel;
import modelClasses.URCapProjectModel;




/**
 * Model class for maven generation of a program node. Takes the groupid, artifactid which should be found be the wizard when from the selected project,
 * the program node should be generated in. Also takes a path (might not be useful in this case?) and a programNodeModel which the wizard 
 * also should create. 
 * The archetype is set to programnodearchetype. 
 * @author jacob
 *
 */
public class ProgramNodeProjectModel implements IURCapMaven {

	private ProgramNodeModel programNodeModel;
	Properties properties;
	private static final String ARCHETYPE_GROUPID = "com.ur.urcap";
	private static final String ARCHETYPE_ARTIFACTID = "programnodearchetype";
	private static final String ARCHETYPE_VERSION = "1.0";
	/**
	 * Empty constructor
	 */
	public ProgramNodeProjectModel() {
	}

	/**
	 * Constructor the set all class fields.
	 * @param groupID
	 * @param artifactID
	 * @param version
	 * @param apiVersion
	 * @param projectPath
	 */
	public ProgramNodeProjectModel(MavenModel programNode) {
		this.programNodeModel = (ProgramNodeModel) programNode;
		this.programNodeModel.setArchetypeGroupId(ARCHETYPE_GROUPID);
		this.programNodeModel.setArchetypeArtifactId(ARCHETYPE_ARTIFACTID);
		this.programNodeModel.setArchetypeVersion(ARCHETYPE_VERSION);
		this.programNodeModel.setMavenGoal("archetype:generate");
		this.setProperties();

	}

	/**
	 * Sets properties for maven project to be generated.
	 * TODO change this to get some attributes from super rather than this 
	 */
	private void setProperties() {
		this.properties = new Properties();
		properties.setProperty("interactiveMode", "false");
		properties.setProperty("archetypeGroupId", this.programNodeModel.getArchetypeGroupId());
		properties.setProperty("archetypeArtifactId", this.programNodeModel.getArchetypeArtifactId());
		properties.setProperty("archetypeVersion", ARCHETYPE_VERSION);
		properties.setProperty("package", this.programNodeModel.getProjectGroupId());
		properties.setProperty("groupId", this.programNodeModel.getProjectGroupId());
		properties.setProperty("artifactId", this.programNodeModel.getProjectArtifactId());
		properties.setProperty("contributionClassName", this.programNodeModel.getContributionClassName());
		properties.setProperty("serviceClassName", this.programNodeModel.getServiceClassName());
		properties.setProperty("viewClassName", this.programNodeModel.getViewClassName());
		properties.setProperty("isChildrenAllowed", Boolean.toString(this.programNodeModel.isSetChildrenAllowed()));
		properties.setProperty("nodeId", this.programNodeModel.getNodeId());
		properties.setProperty("nodeTitle", this.programNodeModel.getNodeTitle());
		properties.setProperty("version", this.programNodeModel.getProjectVersion());
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
		return this.programNodeModel.getProjectPath();
	}

	@Override
	public String getGoal() {
		return this.programNodeModel.getMavenGoal();
	}
	

}
