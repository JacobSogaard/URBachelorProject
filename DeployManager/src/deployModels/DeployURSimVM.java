package deployModels;

import java.util.Properties;

import mavenGenerator.MavenInvokerHandler;
import modelClasses.IURCapMaven;
import modelClasses.URCapProjectModel;

public class DeployURSimVM implements IDeploy {

	private String ipaddress, username, password, path, goal, projectPath;
	private Properties properties;
	private static final String GOAL = "install -P ursimvm";
	
	public DeployURSimVM(String ipaddress, String username, String password, String projectPath) {
		this.ipaddress = ipaddress;
		this.username = username;
		this.password = password;
		this.projectPath = projectPath;
		this.setProperties();
	}
	

	public void setProjectPath(String path) {
		this.path = path;
		
	}

	private void setProperties() {
		this.properties = new Properties();
		this.properties.setProperty("ursimvm.install.host", this.ipaddress);
		this.properties.setProperty("ursimvm.install.username", this.username);
		this.properties.setProperty("ursimvm.install.password", this.password);
		
	}


	@Override
	public Properties getProperties() {
		return this.properties;
	}

	@Override
	public String getProjectPath() {
		return this.projectPath;
	}

	@Override
	public String getGoal() {
		return GOAL;
	}

	@Override
	public void deploy() {
		MavenInvokerHandler invoker = new MavenInvokerHandler();
		invoker.invokeMavenExecution(this);
		
	}
	

}
