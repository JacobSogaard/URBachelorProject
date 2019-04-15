package modelClasses;

import java.util.Properties;

public abstract class MavenProjectModel {
	protected String groupId, artifactId, projectPath;
	
	public abstract Properties getProperties(); 
	public abstract String getProjectPath();
	public abstract String getGoal();
}



