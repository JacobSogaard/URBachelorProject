package deployModels;

import java.util.Properties;

import mavenGenerator.MavenInvokerHandler;

public class DeployURSimVM implements IDeploy {

	
	private String ipaddress, username, password, path;
	private Properties properties;
	private static final String GOAL = "install -P ursimvm";
	private MavenInvokerHandler invoker;
	
	public DeployURSimVM(String ipaddress, String username, String password, String path) {
		this.ipaddress = ipaddress;
		this.username = username;
		this.password = password;
		this.path = path;
		this.setProperties();
		this.invoker = new MavenInvokerHandler();
	}

	private void setProperties() {
		this.properties = new Properties();
		this.properties.setProperty("ursimvm.install.host", this.ipaddress);
		this.properties.setProperty("ursimvm.install.username", this.username);
		this.properties.setProperty("ursimvm.install.password", this.password);
		
	}


	@Override
	public Properties getProperties() {
		// TODO Auto-generated method stub
		return this.properties;
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
	public String getGoal() {
		// TODO Auto-generated method stub
		return GOAL;
	}


	@Override
	public void deploy() {
		invoker.invokeMavenExecution(this);
		
	}
	

}
