package deployModels;

import java.util.Properties;

public class DeployToRobot implements IDeploy{

	private String ipaddress, username, password, path;
	private Properties properties;
	private static final String GOAL = "install -P local";
	
	public DeployToRobot(String ipaddress, String username, String password, String path) {
		this.ipaddress = ipaddress;
		this.username = username;
		this.password = password;
		this.path = path;
		this.setProperties();
	}
	
	@Override
	public String getProjectPath() {
		// TODO Auto-generated method stub
		return this.path;
	}


	public void setProjectPath(String path) {
		this.path = path;
		
	}

	@Override
	public Properties getProperties() {
		// TODO Auto-generated method stub
		return this.properties;
	}

	private void setProperties() {
		this.properties = new Properties();
		this.properties.setProperty("urcap.install.host", this.ipaddress);
		this.properties.setProperty("urcap.install.username", this.username);
		this.properties.setProperty("urcap.install.password", this.password);
		
	}

	@Override
	public String getGoal() {
		// TODO Auto-generated method stub
		return GOAL;
	}
	
	@Override
	public void deploy() {
		
	}

}
