package modelClasses.installationnode;

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
public class InstallationNodeMavenModel extends URCapProjectModel {

	private static final String ARCHYTYPE_ARTIFACTID = "installationnodearchetype";
	private static final String ARCHYTYPE_VERSION = "1.0";
	private InstallationNodeModel installationNode;

	private Properties properties;
	
	/**
	 * Empty constructor
	 */
	public InstallationNodeMavenModel() {
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
	public InstallationNodeMavenModel(NodeModel installationNode) {
		super(installationNode);
		this.installationNode = (InstallationNodeModel) installationNode;
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
		properties.setProperty("package", this.installationNode.getGroupId());
		properties.setProperty("groupId", this.installationNode.getGroupId());
		properties.setProperty("artifactId", this.installationNode.getArtifactId());
		properties.setProperty("contributionClassName", this.installationNode.getContributionClassName());
		properties.setProperty("serviceClassName", this.installationNode.getServiceClassName());
		properties.setProperty("viewClassName", this.installationNode.getViewClassName());
		properties.setProperty("nodeTitle", this.installationNode.getTitle());
		properties.setProperty("version", this.installationNode.getVersion());
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
