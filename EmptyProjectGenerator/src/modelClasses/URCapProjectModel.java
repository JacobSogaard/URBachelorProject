package modelClasses;

import java.util.Properties;

public abstract class URCapProjectModel {
	protected String groupId, artifactId, projectPath;
	protected final String ARCHYTYPE_GROUPID = "com.ur.urcap";
	
	protected URCapProjectModel() {
		
	}
	
	protected URCapProjectModel(NodeModel nodeModel) {
		this.projectPath = nodeModel.getProjectPath();
	}
	public abstract Properties getProperties(); 
	
	public String getProjectPath() {
		return this.projectPath;
	};
}



