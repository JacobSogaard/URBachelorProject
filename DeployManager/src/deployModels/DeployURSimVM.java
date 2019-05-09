package deployModels;

import java.util.Properties;

import mavenGenerator.IMavenHandler;
import mavenGenerator.MavenInvokerHandler;
import modelClasses.IURCapMaven;
import modelClasses.URCapProjectModel;

public class DeployURSimVM implements IDeploy {

	private String ipaddress, username, password, path, goal, projectPath, artifactID;
	private Properties properties;
	private static final String GOAL = "install -P ursimvm";
	private IMavenHandler mavenHandler;

	public DeployURSimVM(String ipaddress, String username, String password, String projectPath, String artifactID) {
		this.ipaddress = ipaddress;
		this.username = username;
		this.password = password;
		this.projectPath = projectPath;
		this.artifactID = artifactID;
		this.setProperties();
		this.mavenHandler = new MavenInvokerHandler();
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
	public String deploy() {
		String message = mavenHandler.invokeDeploy(this, this.artifactID);

		return message;
	}

}
