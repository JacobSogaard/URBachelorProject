package urcapproject.Generator;

import java.util.Collections;
import java.util.Properties;

import org.apache.maven.shared.invoker.DefaultInvocationRequest;
import org.apache.maven.shared.invoker.DefaultInvoker;
import org.apache.maven.shared.invoker.InvocationRequest;
import org.apache.maven.shared.invoker.Invoker;
import org.apache.maven.shared.invoker.MavenInvocationException;

public class urcapGenerator {

	
	private InvocationRequest request;
	private Invoker invoker;
	private Properties properties;
	
	public urcapGenerator() {
		
		this.request = new DefaultInvocationRequest();
		this.invoker = new DefaultInvoker(); 
		this.properties = new Properties();
	}

	public void genreateMavenProject() {
		
		//request.setProperties(this.properties);
		request.setGoals(Collections.singletonList("mvn archetype:generate -DgroupId=com.urcap -DartifactId= -DarchetypeArtifactId=archetype -DinteractiveMode=false"));
		//request.setBatchMode(true);
		
		try {
			
			this.invoker.execute(request);
			System.out.println("Creation of UR project succeeded");
			
		} catch (MavenInvocationException e) {
			System.out.println("Creation of UR project failed...");
			e.printStackTrace();
		}
	
	}
	
	public void setProperties(String groupId, String artifactId, String version, String apiVersion) {
		
		properties.put("groupID", groupId);
		properties.put("articfactId", artifactId);
		properties.put("version", version);
		properties.put("apiVersion", apiVersion);
	}
}
