package test;

import static org.junit.Assert.*;

import org.eclipse.core.runtime.IStatus;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import mavenGenerator.MavenInvokerHandler;
import modelClasses.IURCapMaven;
import modelClasses.MavenModel;
import modelClasses.URCapModel;
import modelClasses.URCapProjectModel;
import modelClasses.installationnode.InstallationNodeMavenModel;
import modelClasses.installationnode.InstallationNodeModel;

/**
 * Test for MavenInvokerHandler. Tests the generation of project and nodes through maven
 * @author jacob
 *
 */
public class MavenInvokerHandlerTest {

	private static MavenInvokerHandler invoker;
	private static MavenModel mavenModel;
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
		invoker = new MavenInvokerHandler();
		mavenModel = new URCapModel();
		
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * Test method for {@link mavenGenerator.MavenInvokerHandler#MavenInvokerHandler()}.
	 */
	@Test
	public void testMavenInvokerHandler() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link mavenGenerator.MavenInvokerHandler#invokeGenerator(modelClasses.IURCapMaven)}.
	 * 
	 * setup URCapProjectModel object (IURCapMavenModel. Set path as workspace path to test the location of the new project.
	 * This test does more than just test the invokeGenerator method. It also test the structure of the mavenmodel classes and the calls that are made in the wizard through them
	 * will actually work and when they will not work. 
	 * 
	 * TEST:
	 * Different UR api versions
	 * Path: 
	 *  Path that does not exist y
	 *  Path that already has a project
	 *  Group and artifact id, different characters. 
	 *  Artifact with underscore
	 *  Artifact with slash
	 *  
	 * Nodes:
	 *  Stuff with title.
	 *  Group or artifact not the same as project (should not happen)
	 * 
	 */
	@Test
	public void testInvokeGenerator() {
		
		//Test empty project
		mavenModel.setArchetypeVersionAPI("1.5.0");
		mavenModel.setArchetypeVersion("1.5.0");
		mavenModel.setProjectGroupId("com.company.testcompany");
		mavenModel.setProjectArtifactId("MyTestURCap");
		mavenModel.setProjectPath("/home/jacob/Desktop/testing");
		mavenModel.setProjectVersion("1.0");
		IURCapMaven emptyProject = new URCapProjectModel(mavenModel);
		
		//If invokeGenerator returned the invoke exit code, this would be easier to test!
		String invokeMessage = invoker.invokeGenerator(emptyProject);
		assertEquals("0", invokeMessage);
		
		
	}
	
	@Test
	public void testPathNotExisting() {
		mavenModel.setArchetypeVersionAPI("1.5.0");
		mavenModel.setArchetypeVersion("1.5.0");
		mavenModel.setProjectGroupId("Some group Id");
		mavenModel.setProjectArtifactId("Some Artifact Id");
		mavenModel.setProjectPath("/home/jacob/Desktop/not_a_directory");
		mavenModel.setProjectVersion("1.0");
		IURCapMaven emptyProject = new URCapProjectModel(mavenModel);
		
		//If invokeGenerator returned the invoke exit code, this would be easier to test!
		String invokeMessage = invoker.invokeGenerator(emptyProject);
		assertNotEquals("0", invokeMessage); //We do not expect this to pass and we expect to get an non-zero exit code from the invocation, meaning we should handle invalid filepaths
	}
	
	/**
	 * Run testInvokeGenerator() first, this creates a project on the projectpath
	 */
	@Test
	public void testOnProjectAlreadyExists() {
		mavenModel.setArchetypeVersionAPI("1.5.0");
		mavenModel.setArchetypeVersion("1.5.0");
		mavenModel.setProjectGroupId("com.existing.projectpath");
		mavenModel.setProjectArtifactId("Project exists on path");
		mavenModel.setProjectPath("/home/jacob/Desktop/testing"); 
		mavenModel.setProjectVersion("1.0");
		IURCapMaven emptyProject = new URCapProjectModel(mavenModel);
		
		
		String invokeMessage = invoker.invokeGenerator(emptyProject);
		assertEquals("0", invokeMessage); //Another project on path should not matter
	}
	
	/**
	 * Run after testOnProjectAlreadyExits(). Should not create same project. 
	 */
	@Test
	public void testOnSameArtifact() {
		mavenModel.setArchetypeVersionAPI("1.5.0");
		mavenModel.setArchetypeVersion("1.5.0");
		mavenModel.setProjectGroupId("some.new.groupId");
		mavenModel.setProjectArtifactId("Project exists on path");
		mavenModel.setProjectPath("/home/jacob/Desktop/testing"); 
		mavenModel.setProjectVersion("1.0");
		IURCapMaven emptyProject = new URCapProjectModel(mavenModel);
		
		
		String invokeMessage = invoker.invokeGenerator(emptyProject);
		assertNotEquals("0", invokeMessage); 
	}
	

	
	/**
	 * Test different input fpr group or artifact id
	 * slashes
	 * dot
	 * parenthesis
	 * spaces
	 */
	@Test
	public void testInvalidId() {
		mavenModel.setArchetypeVersionAPI("1.5.0");
		mavenModel.setArchetypeVersion("1.5.0");
		mavenModel.setProjectGroupId("groupid");
		mavenModel.setProjectArtifactId("//Myartifact");
		mavenModel.setProjectPath("/home/jacob/Desktop/testing"); 
		mavenModel.setProjectVersion("1.0");
		IURCapMaven emptyProject = new URCapProjectModel(mavenModel);
		
		//If invokeGenerator returned the invoke exit code, this would be easier to test!
		String invokeMessage = invoker.invokeGenerator(emptyProject);
		assertEquals("0", invokeMessage);  
		
		//Test underscore
		mavenModel.setArchetypeVersionAPI("1.5.0");
		mavenModel.setArchetypeVersion("1.5.0");
		mavenModel.setProjectGroupId("groupid");
		mavenModel.setProjectArtifactId("_Myartifact");
		mavenModel.setProjectPath("/home/jacob/Desktop/testing"); 
		mavenModel.setProjectVersion("1.0");
		IURCapMaven emptyProject = new URCapProjectModel(mavenModel);
		
		//If invokeGenerator returned the invoke exit code, this would be easier to test!
		String invokeMessage = invoker.invokeGenerator(emptyProject);
		assertEquals("0", invokeMessage);  
		
		//Test underscore
		mavenModel.setArchetypeVersionAPI("1.5.0");
		mavenModel.setArchetypeVersion("1.5.0");
		mavenModel.setProjectGroupId("groupid");
		mavenModel.setProjectArtifactId("Myartifact..");
		mavenModel.setProjectPath("/home/jacob/Desktop/testing"); 
		mavenModel.setProjectVersion("1.0");
		IURCapMaven emptyProject = new URCapProjectModel(mavenModel);
		
		//If invokeGenerator returned the invoke exit code, this would be easier to test!
		String invokeMessage = invoker.invokeGenerator(emptyProject);
		assertEquals("0", invokeMessage);  
	}
	
//	@Test
//	public void testNodeGenerator() {
//		//Test installation node
//		MavenModel installationNodeMavenModel = new InstallationNodeModel("service", "contribution", "view", "title"); //changes these parameters to weird stuff. Should not break
//		installationNodeMavenModel.setProjectPath("home/jacob/Desktop/testing");
//		installationNodeMavenModel.setProjectGroupId("groupId"); 
//		installationNodeMavenModel.setProjectArtifactId("artifact"); //Change to existing
//		installationNodeMavenModel.setProjectVersion("1.0");
//		IURCapMaven installationNode = new InstallationNodeMavenModel(installationNodeMavenModel);
//		String invokeInstallationNode = invoker.invokeGenerator(installationNode);
//		assertNotEquals("0", invokeInstallationNode);
//	}
//	
//	
//
//	/**
//	 * Test method for {@link mavenGenerator.MavenInvokerHandler#invokeDeploy(modelClasses.IURCapMaven, java.lang.String)}.
//	 */
//	@Test
//	public void testInvokeDeploy() {
//		fail("Not yet implemented");
//	}
//
//	/**
//	 * Test method for {@link mavenGenerator.MavenInvokerHandler#importMavenProject(java.lang.String, java.lang.String)}.
//	 */
//	@Test
//	public void testImportMavenProject() {
//		fail("Not yet implemented");
//	}

}
