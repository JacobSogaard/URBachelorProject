package modelClasses.toolbarnode;

import java.util.Properties;

import modelClasses.IURCapMaven;
import modelClasses.MavenModel;
import modelClasses.URCapProjectModel;




/**
 * Model class for maven generation of a toolbar node. Takes a MavenModel which holds all the information for 
 * generating toolbarnode through Maven. 
 * The archetype is set to toolbarnodearchetype. 
 * @author jacob
 *
 */
public class ToolbarNodeProjectModel implements IURCapMaven {

	private ToolbarNodeModel toolbarNodeModel;
	Properties properties;
	private static final String ARCHETYPE_GROUPID = "com.ur.urcap";
	private static final String ARCHETYPE_ARTIFACTID = "toolbarnodearchetype";
	private static final String ARCHETYPE_VERSION = "1.0";
	/**
	 * Empty constructor
	 */
	public ToolbarNodeProjectModel() {
	}

	/**
	 * Constructor the set all class fields.
	 * @param groupID
	 * @param artifactID
	 * @param version
	 * @param apiVersion
	 * @param projectPath
	 */
	public ToolbarNodeProjectModel(MavenModel toolbarNode) {
		this.toolbarNodeModel = (ToolbarNodeModel) toolbarNode;
		this.toolbarNodeModel.setArchetypeGroupId(ARCHETYPE_GROUPID);
		this.toolbarNodeModel.setArchetypeArtifactId(ARCHETYPE_ARTIFACTID);
		this.toolbarNodeModel.setArchetypeVersion(ARCHETYPE_VERSION);
		this.toolbarNodeModel.setMavenGoal("archetype:generate");
		this.setProperties();

	}

	/**
	 * Sets properties for maven project to be generated.
	 * TODO change this to get some attributes from super rather than this 
	 */
	private void setProperties() {
		this.properties = new Properties();
		properties.setProperty("interactiveMode", "false");
		properties.setProperty("archetypeGroupId", this.toolbarNodeModel.getArchetypeGroupId());
		properties.setProperty("archetypeArtifactId", this.toolbarNodeModel.getArchetypeArtifactId());
		properties.setProperty("archetypeVersion", ARCHETYPE_VERSION);
		properties.setProperty("package", this.toolbarNodeModel.getProjectGroupId());
		properties.setProperty("groupId", this.toolbarNodeModel.getProjectGroupId());
		properties.setProperty("artifactId", this.toolbarNodeModel.getProjectArtifactId());
		properties.setProperty("contributionClassName", this.toolbarNodeModel.getContributionClassName());
		properties.setProperty("serviceClassName", this.toolbarNodeModel.getServiceClassName());
		properties.setProperty("icon", this.toolbarNodeModel.getIconPath());
		properties.setProperty("version", this.toolbarNodeModel.getProjectVersion());
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
		return this.toolbarNodeModel.getProjectPath();
	}

	@Override
	public String getGoal() {
		return this.toolbarNodeModel.getMavenGoal();
	}
	

}
