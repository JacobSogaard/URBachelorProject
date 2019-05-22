package mavenGenerator;

import java.io.File;
import java.util.Collections;

import org.apache.maven.shared.invoker.DefaultInvocationRequest;
import org.apache.maven.shared.invoker.DefaultInvoker;
import org.apache.maven.shared.invoker.InvocationRequest;
import org.apache.maven.shared.invoker.InvocationResult;
import org.apache.maven.shared.invoker.Invoker;
import org.apache.maven.shared.invoker.MavenInvocationException;

import mavenImport.MavenProjectImporter;
import modelClasses.IURCapMaven;

/**
 * Maven invoker class handles the execution of a maven command.
 * 
 * @author jacob
 *
 */
public class MavenInvokerHandler implements IMavenHandler{

	private final String MAVEN1_ENVIRONMENT = System.getenv("MAVEN_HOME");
	private final String MAVEN2_ENVIRONMENT = System.getenv("M2_HOME");
	private final String MAVEN3_ENVIRONMENT = System.getenv("M3_HOME");
	private InvocationRequest request;
	private Invoker invoker;

	public MavenInvokerHandler() {
		this.request = new DefaultInvocationRequest();
		this.invoker = new DefaultInvoker();
	}

	/**
	 * Executes a maven command.
	 * 
	 * @param projectModel - Object of type IURCapMaven. This holds the properties of the maven command
	 * and the goal of the maven command.
	 */
	@Override
	public String invokeGenerator(IURCapMaven projectModel) {

		String invokeMavenMessage = "";

		invokeMavenMessage = this.setupMavenRequest(projectModel);

		if (invokeMavenMessage == "") {

			try {
				InvocationResult result = invoker.execute(request);
				invokeMavenMessage = Integer.toString(result.getExitCode());
			} catch (MavenInvocationException e) {
				e.printStackTrace();
			}
		}

		return invokeMavenMessage;

	}

	/**
	 * Executes a maven command for deployment of the URCap.
	 * @param projectModel - Object of type IURCapMaven, which holds the properties and the goal of the maven command.
	 * @param artifactId - String value with the artifact id of the project to be deployed. 
	 */
	@Override
	public String invokeDeploy(IURCapMaven projectModel, String artifactID) {

		String messageResult = "";

		messageResult = this.setupMavenRequestDeploy(projectModel, artifactID);

		if (messageResult == "") {
			try {
				InvocationResult result = invoker.execute(request);
				if (result.getExitCode() == 0 && result.getExecutionException() == null) {
					messageResult = "The project has been succesfully deployed!";
				} else {
					messageResult = "Something went wrong during deployment of the URCap!";
				}
			} catch (MavenInvocationException e) {
				e.printStackTrace();
			}
		}

		return messageResult;

	}

	/**
	 * Deploy execution. Sets up the request to be made by maven.
	 * @param projectModel
	 * @param artifactID
	 */
	private String setupMavenRequestDeploy(IURCapMaven projectModel, String artifactID) {
		String mavenExecutionMessage = "";

		String pomPath = projectModel.getProjectPath() + "/" + artifactID;

		request.setBaseDirectory(new File(pomPath));
		request.setGoals(Collections.singletonList(projectModel.getGoal()));
		request.setBatchMode(true);
		request.setProperties(projectModel.getProperties());

		if (!this.checkMavenEnvironmentVariable()) {
			mavenExecutionMessage = "The execution is not possible." + "\n"
					+ "Please add a maven enviroment variable to your system with the name: MAVEN_HOME";
		}

		return mavenExecutionMessage;

	}


	/**
	 * Sets up the request to be made by maven.
	 * 
	 * @param projectModel
	 */
	private String setupMavenRequest(IURCapMaven projectModel) {
		String mavenExecutionMessage = "";

		request.setBaseDirectory(new File(projectModel.getProjectPath()));
		request.setGoals(Collections.singletonList(projectModel.getGoal()));
		request.setBatchMode(true);
		request.setProperties(projectModel.getProperties());

		if (!this.checkMavenEnvironmentVariable()) {
			mavenExecutionMessage = "The execution is not possible." + "\n"
					+ "Please add a maven environment variable to your system with the name: MAVEN_HOME, M2_HOME, M3_HOME, ";
		}

		return mavenExecutionMessage;

	}

	/**
	 * Check for Maven environment variable on host computer.
	 */
	private boolean checkMavenEnvironmentVariable() {

		boolean mavenExists = true;

		if (this.MAVEN1_ENVIRONMENT != null) {
			invoker.setMavenHome(new File(this.MAVEN1_ENVIRONMENT));
		} else if (this.MAVEN2_ENVIRONMENT != null) {
			invoker.setMavenHome(new File(this.MAVEN2_ENVIRONMENT));
		} else if (this.MAVEN3_ENVIRONMENT != null) {
			invoker.setMavenHome(new File(this.MAVEN3_ENVIRONMENT));
		} else {
			System.err.println("No suitable maven environment");

			mavenExists = false;
		}

		return mavenExists;
	}

	/**
	 * Imports the created project as a maven project.
	 */
	@Override
	public String importMavenProject(String path, String name) {
		MavenProjectImporter importer = new MavenProjectImporter();
		String message = importer.importProjectAsMavenProject(path, name);
		
		return message;
	}

}
