package mavenGenerator;

import java.io.File;
import java.util.Collections;
import org.apache.maven.shared.invoker.DefaultInvocationRequest;
import org.apache.maven.shared.invoker.DefaultInvoker;
import org.apache.maven.shared.invoker.InvocationRequest;
import org.apache.maven.shared.invoker.InvocationResult;
import org.apache.maven.shared.invoker.Invoker;
import org.apache.maven.shared.invoker.MavenInvocationException;
import modelClasses.IURCapMaven;

/**
 * Maven invoker class handles the execution of a maven command.
 * 
 * @author jacob
 *
 */
public class MavenInvokerHandler {

	private final String MAVEN1_ENVIRONMENT = System.getenv("MAVEN_HOME");
	private final String MAVEN2_ENVIRONMENT = System.getenv("M2_HOME");
	private InvocationRequest request;
	private Invoker invoker;

	public MavenInvokerHandler() {
		this.request = new DefaultInvocationRequest();
		this.invoker = new DefaultInvoker();
	}

	/**
	 * Executes a maven command.
	 * 
	 * @param projectModel
	 */
	public void invokeMavenExecution(IURCapMaven projectModel) {

		this.setupMavenRequest(projectModel);

		try {
			InvocationResult result = invoker.execute(request);
		} catch (MavenInvocationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * Sets up the request to be made by maven.
	 * 
	 * @param projectModel
	 */
	private void setupMavenRequest(IURCapMaven projectModel) {
		request.setBaseDirectory(new File(projectModel.getProjectPath()));
		request.setGoals(Collections.singletonList(projectModel.getGoal()));
		request.setBatchMode(true);
		request.setProperties(projectModel.getProperties());

		this.checkMavenEnvironmentVariable();

	}

	/**
	 * Check for Maven environment variable on host computer.
	 */
	private void checkMavenEnvironmentVariable() {
		if (this.MAVEN1_ENVIRONMENT == null) {
			invoker.setMavenHome(new File(this.MAVEN2_ENVIRONMENT));
		} else if (this.MAVEN2_ENVIRONMENT == null) {
			invoker.setMavenHome(new File(this.MAVEN1_ENVIRONMENT));
		} else {
			System.err.println("No suitable maven environment");
		}
	}

}
