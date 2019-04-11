package mavenGenerator;

import java.io.File;
import java.util.Collections;
import org.apache.maven.shared.invoker.DefaultInvocationRequest;
import org.apache.maven.shared.invoker.DefaultInvoker;
import org.apache.maven.shared.invoker.InvocationRequest;
import org.apache.maven.shared.invoker.InvocationResult;
import org.apache.maven.shared.invoker.Invoker;
import org.apache.maven.shared.invoker.MavenInvocationException;
import modelClasses.URCapProjectModel;

/**
 * Generates maven project using maven invoker.
 * @author jacob
 *
 */
public class URCapProjectGenerator {

	private final String MAVEN1_ENVIRONMENT = System.getenv("MAVEN_HOME");
	private final String MAVEN2_ENVIRONMENT = System.getenv("M2_HOME");
	private InvocationRequest request;
	private Invoker invoker;

	public URCapProjectGenerator() {
		this.request = new DefaultInvocationRequest();
		this.invoker = new DefaultInvoker();
	}

	/**
	 * Generates the maven project using the maven invoker
	 * @param projectModel
	 */
	public void generate(URCapProjectModel projectModel) {
		this.setupRequest(projectModel);
		
		try {
			InvocationResult result = invoker.execute(request);
		} catch (MavenInvocationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * Sets up the request to be made by maven to generate the URCap project
	 * @param projectModel
	 */
	private void setupRequest(URCapProjectModel projectModel) {
		request.setBaseDirectory(new File(projectModel.getProjectPath()));
		request.setGoals(Collections.singletonList("archetype:generate"));
		request.setBatchMode(true);
		request.setProperties(projectModel.getProperties());

		//Check for Maven environment, might be some better try-catch solution....
		//Also test whether this will always work, might be some problem with maven3
		if (this.MAVEN1_ENVIRONMENT == null) {
			invoker.setMavenHome(new File(this.MAVEN2_ENVIRONMENT));
		} else if (this.MAVEN2_ENVIRONMENT == null) {
			invoker.setMavenHome(new File(this.MAVEN1_ENVIRONMENT));
		} else {
			System.err.println("No suitable maven environment");
		}
		
	}
	
}
