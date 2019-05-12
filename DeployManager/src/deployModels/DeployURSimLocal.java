package deployModels;

import java.util.Properties;

import mavenGenerator.IMavenHandler;
import mavenGenerator.MavenInvokerHandler;


/**
 * Class for deploying to local URSim. Needs a path for the URSim and path and artifact id for project
 * which needs to be deployed. 
 * @author jacob
 *
 */
public class DeployURSimLocal implements IDeploy{

	private String URSimpath, projectPath, artifactID;
	private Properties properties;
	private static final String GOAL = "install -P ursim";
	private IMavenHandler mavenHandler;
	
	public DeployURSimLocal(String URSimPath, String projectPath, String artifactID) {
		this.URSimpath = URSimPath;
		this.projectPath = projectPath;
		this.artifactID = artifactID;
		this.setProperties();
		this.mavenHandler = new MavenInvokerHandler();
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
		String message = mavenHandler.invokeDeploy(this,this.artifactID);
		
		return message;
	}

}
