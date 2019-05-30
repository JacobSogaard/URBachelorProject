package modelClasses;

import java.util.Properties;

/**
 * Interface for object which should be used execution of maven commands. 
 * @author jacob
 *
 */
public interface IURCapMaven {
	
	public Properties getProperties(); 
	public String getProjectPath();
	public String getGoal();
}



