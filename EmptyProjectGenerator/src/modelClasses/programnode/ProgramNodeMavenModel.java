package modelClasses.programnode;

import java.util.Properties;

import modelClasses.NodeModel;
import modelClasses.URCapProjectModel;




/**
 * Model class for maven generation of a program node. Takes the groupid, artifactid which should be found be the wizard when from the selected project,
 * the program node should be generated in. Also takes a path (might not be useful in this case?) and a programNodeModel which the wizard 
 * also should create. 
 * The archetype is set to programnodearchetype. 
 * @author jacob
 *
 */
public class ProgramNodeMavenModel extends URCapProjectModel {
	private static final String ARCHYTYPE_ARTIFACTID = "programnodearchetype";
	private static final String ARCHYTYPE_VERSION = "1.0";
	private ProgramNodeModel programNode;

	private Properties properties;
	
	/**
	 * Empty constructor
	 */
	public ProgramNodeMavenModel() {
		super();
	}

	/**
	 * Constructor the set all class fields.
	 * @param groupID
	 * @param artifactID
	 * @param version
	 * @param apiVersion
	 * @param projectPath
	 */
	public ProgramNodeMavenModel(NodeModel programNode) {
		super(programNode);
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
		properties.setProperty("package", this.programNode.getGroupId());
		properties.setProperty("groupId", this.programNode.getGroupId());
		properties.setProperty("artifactId", this.programNode.getArtifactId());
		properties.setProperty("contributionClassName", this.programNode.getContributionClassName());
		properties.setProperty("serviceClassName", this.programNode.getServiceClassName());
		properties.setProperty("viewClassName", this.programNode.getViewClassName());
		properties.setProperty("isChildrenAllowed", Boolean.toString(this.programNode.isSetChildrenAllowed()));
		properties.setProperty("nodeId", this.programNode.getNodeId());
		properties.setProperty("nodeTitle", this.programNode.getTitle());
		properties.setProperty("version", this.programNode.getVersion());
	}

	
	/**
	 * Get all properties set for maven project generation
	 * @return Properties of type Properties
	 */
	@Override
	public Properties getProperties() {
		return this.properties;
	}
	
	

}
