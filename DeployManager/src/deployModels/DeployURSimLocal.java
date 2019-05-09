package deployModels;

import java.util.Properties;

import mavenGenerator.MavenInvokerHandler;

public class DeployURSimLocal implements IDeploy{

	private String URSimpath, projectPath, goal, artifactID;
	private Properties properties;
	private static final String GOAL = "install -P ursim";
	
	public DeployURSimLocal(String URSimPath, String projectPath, String artifactID) {
		this.URSimpath = URSimPath;
		this.projectPath = projectPath;
		this.artifactID = artifactID;
		this.setProperties();
	}
	
	@Override
	public String getProjectPath() {
		return this.projectPath;
	}


	public void setProjectPath(String path) {
		this.projectPath = path;
		
	}

	@Override
	public Properties getProperties() {
		return this.properties;
	}

	private void setProperties() {
		this.properties = new Properties();
		this.properties.setProperty("ursim.home", this.URSimpath);
	}

	@Override
	public String getGoal() {
		return GOAL;
	}
	
	@Override
	public String deploy() {
		MavenInvokerHandler invoker = new MavenInvokerHandler();
		String message = invoker.invokeMavenExecutionDeploy(this,this.artifactID);
		
		return message;
	}

}
