package deployModels;

import java.util.Properties;

import modelClasses.IURCapMaven;

public class DeployURSimVM implements IURCapMaven {

	private String ipaddress, username, password, path, goal;
	private Properties properties;
	private static final String GOAL = "install -P ursimvm";
	
	public DeployURSimVM(String ipaddress, String username, String password) {
		this.ipaddress = ipaddress;
		this.username = username;
		this.password = password;
		this.setProperties();
	}
	
//	@Override
//	public String getProjectPath() {
//		// TODO Auto-generated method stub
//		return this.path;
//	}

	public void setProjectPath(String path) {
		this.path = path;
		
	}

//	@Override
//	public Properties getProperties() {
//		// TODO Auto-generated method stub
//		return this.properties;
//	}

	private void setProperties() {
		this.properties = new Properties();
		this.properties.setProperty("ursimvm.install.host", this.ipaddress);
		this.properties.setProperty("ursimvm.install.username", this.username);
		this.properties.setProperty("ursimvm.install.password", this.password);
		
	}

//	@Override
//	public String getGoal() {
//		// TODO Auto-generated method stub
//		return GOAL;
//	}


	@Override
	public Properties getProperties() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getProjectPath() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getGoal() {
		// TODO Auto-generated method stub
		return null;
	}
	

}
