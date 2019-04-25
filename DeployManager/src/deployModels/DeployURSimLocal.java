package deployModels;

import java.util.Properties;

public class DeployURSimLocal implements IDeploy{

	private String URSimpath, projectPath, goal;
	private Properties properties;
	private static final String GOAL = "install -P ursim";
	
	public DeployURSimLocal(String URSimPath, String projectPath) {
		this.URSimpath = URSimPath;
		this.projectPath = projectPath;
		this.setProperties();
	}
	
	@Override
	public String getProjectPath() {
		// TODO Auto-generated method stub
		return this.projectPath;
	}


	public void setProjectPath(String path) {
		this.projectPath = path;
		
	}

	@Override
	public Properties getProperties() {
		// TODO Auto-generated method stub
		return this.properties;
	}

	private void setProperties() {
		this.properties = new Properties();
		this.properties.setProperty("ursim.home", this.URSimpath);
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
