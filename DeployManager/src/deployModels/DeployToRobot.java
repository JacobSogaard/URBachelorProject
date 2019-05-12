package deployModels;

import java.util.Properties;

import mavenGenerator.IMavenHandler;
import mavenGenerator.MavenInvokerHandler;
/**
 * Class for deploying urcap to robot.
 * @author Thananya
 *
 */
public class DeployToRobot implements IDeploy{

	private String ipAddress, username, password, projectPath, artifactID;
	private Properties properties;
	private static final String GOAL = "install -P remote";
	private IMavenHandler mavenHandler;
	
	public DeployToRobot(String ipAddress, String username, String password, String path, String artifactID) {
		this.ipAddress = ipAddress;
		this.username = username;
		this.password = password;
		this.projectPath = path;
		this.artifactID = artifactID;
		this.setProperties();
		this.mavenHandler = new MavenInvokerHandler();
	}
	
	@Override
	public String getProjectPath() {
		return this.projectPath;
	}


	/**
	 * Set project path
	 * @param projectPath - String
	 */
	public void setProjectPath(String projectPath) {
		this.projectPath = projectPath;
		
	}

	@Override
	public Properties getProperties() {
		return this.properties;
	}

	private void setProperties() {
		this.properties = new Properties();
		this.properties.setProperty("urcap.install.host", this.ipAddress);
		this.properties.setProperty("urcap.install.username", this.username);
		this.properties.setProperty("urcap.install.password", this.password);
		
	}

	@Override
	public String getGoal() {
		return GOAL;
	}
	
	@Override
	public String deploy() {
		String message = mavenHandler.invokeDeploy(this,this.artifactID);
		return message;
	}


}
