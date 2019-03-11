package urcapproject.Generator;


import java.io.File;
import java.util.Collections;

import org.apache.maven.shared.invoker.DefaultInvocationRequest;
import org.apache.maven.shared.invoker.DefaultInvoker;
import org.apache.maven.shared.invoker.InvocationRequest;
import org.apache.maven.shared.invoker.InvocationResult;
import org.apache.maven.shared.invoker.Invoker;
import org.apache.maven.shared.invoker.MavenInvocationException;

public class urcapGenerator {



	public urcapGenerator() {
	}

	
	public void executeMavenCommand() {
		InvocationRequest request = new DefaultInvocationRequest();
		request.setPomFile(new File("C:\\Users\\Bruger\\Documents\\URBachelorProject\\URCapPlugin\\pom.xml"));
		request.setGoals(Collections.singletonList("install"));

		Invoker invoker = new DefaultInvoker();
		invoker.setMavenHome(new File(System.getenv("MAVEN_HOME")));
		try {
			InvocationResult result = invoker.execute(request);
		} catch (MavenInvocationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void setProperties() {
		
	}
}
