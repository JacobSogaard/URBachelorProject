package mavenGenerator;

import modelClasses.IURCapMaven;

/**
 * Interface for handling maven commands. 
 * @author jacob
 *
 */
public interface IMavenHandler {

	String invokeGenerator(IURCapMaven projectModel);
	String invokeDeploy(IURCapMaven projectModel, String artifactID);
	String importMavenProject(String path, String name);
}
