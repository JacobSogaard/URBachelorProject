package modelClasses;

import java.util.Properties;

public abstract class URCapProjectModel {
	protected String groupId, artifactId, projectPath;
	
	public abstract Properties getProperties(); 
	public abstract String getProjectPath();
}



