package emptyProjectModel;

import java.io.File;
import java.util.Collections;
import java.util.Properties;
import org.apache.maven.shared.invoker.DefaultInvocationRequest;
import org.apache.maven.shared.invoker.DefaultInvoker;
import org.apache.maven.shared.invoker.InvocationRequest;
import org.apache.maven.shared.invoker.InvocationResult;
import org.apache.maven.shared.invoker.Invoker;
import org.apache.maven.shared.invoker.MavenInvocationException;

public class urcapGenerator {

	private final String MAVEN1_ENVIRONMENT = System.getenv("MAVEN_HOME");
	private final String MAVEN2_ENVIRONMENT = System.getenv("M2_HOME");

	public urcapGenerator() {
	}

	public void executeMavenCommand(NewProjectModel projectModel) {

		InvocationRequest request = new DefaultInvocationRequest();
		//request.setPomFile(new File("C:\\Users\\Bruger\\Documents\\URBachelorProject\\URCapPlugin\\pom.xml"));
		request.setBaseDirectory(new File(projectModel.getProjectPAth()));
		request.setGoals(Collections.singletonList("archetype:generate"));
		request.setBatchMode(true);
		request.setProperties(projectModel.getProperties());

		Invoker invoker = new DefaultInvoker();
		
		
		//Check for Maven environment, might be some better try-catch solution....
		//Also test whether this will always work, might be some problem with maven3
		if (this.MAVEN1_ENVIRONMENT == null) {
			invoker.setMavenHome(new File(this.MAVEN2_ENVIRONMENT));
		} else if (this.MAVEN2_ENVIRONMENT == null) {
			invoker.setMavenHome(new File(this.MAVEN1_ENVIRONMENT));
		} else {
			System.err.println("No suitable maven environment");
		}
		
		

		try {
			InvocationResult result = invoker.execute(request);
		} catch (MavenInvocationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
