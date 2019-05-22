package mavenGenerator;

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



//	/**
//	 * Test method for {@link mavenGenerator.MavenInvokerHandler#invokeGenerator(modelClasses.IURCapMaven)}.
//	 * 
//	 * setup URCapProjectModel object (IURCapMavenModel. Set path as workspace path to test the location of the new project.
//	 * This test does more than just test the invokeGenerator method. It also test the structure of the mavenmodel classes and the calls that are made in the wizard through them
//	 * will actually work and when they will not work. 
//	 * 
//	 * TEST:
//	 * Different UR api versions
//	 * Path: 
//	 *  Path that does not exist y
//	 *  Path that already has a project
//	 *  Group and artifact id, different characters. 
//	 *  Artifact with underscore
//	 *  Artifact with slash
//	 *  
//	 * Nodes:
//	 *  Stuff with title.
//	 *  Group or artifact not the same as project (should not happen)
//	 * 
//	 */
//	@Test
//	public void testInvokeGenerator() {
//		
//		//Test empty project
//		mavenModel.setArchetypeVersionAPI("1.5.0");
//		mavenModel.setArchetypeVersion("1.5.0");
//		mavenModel.setProjectGroupId("com.company.testcompany");
//		mavenModel.setProjectArtifactId("MyURCap");
//		mavenModel.setProjectPath("/home/jacob/Desktop/testing");
//		mavenModel.setProjectVersion("1.0");
//		IURCapMaven emptyProject = new URCapProjectModel(mavenModel);
//		
//		String invokeMessage = invoker.invokeGenerator(emptyProject);
//		assertEquals("0", invokeMessage);
//		
//		
//	}
//	
//	@Test
//	public void testPathNotExisting() {
//		mavenModel.setArchetypeVersionAPI("1.5.0");
//		mavenModel.setArchetypeVersion("1.5.0");
//		mavenModel.setProjectGroupId("Some group Id");
//		mavenModel.setProjectArtifactId("SomeArtifactId");
//		mavenModel.setProjectPath("/home/jacob/Desktop/not_a_directory");
//		mavenModel.setProjectVersion("1.0");
//		IURCapMaven emptyProject = new URCapProjectModel(mavenModel);
//		
//		//If invokeGenerator returned the invoke exit code, this would be easier to test!
//		String invokeMessage = invoker.invokeGenerator(emptyProject);
//		assertNotEquals("0", invokeMessage); //We do not expect this to pass and we expect to get an non-zero exit code from the invocation, meaning we should handle invalid filepaths
//	}
//	
//	/**
//	 * Run testInvokeGenerator() first, this creates a project on the projectpath
//	 */
//	@Test
//	public void testOnProjectAlreadyExists() {
//		mavenModel.setArchetypeVersionAPI("1.5.0");
//		mavenModel.setArchetypeVersion("1.5.0");
//		mavenModel.setProjectGroupId("com.existing.projectpath");
//		mavenModel.setProjectArtifactId("ProjectExistsOnPath");
//		mavenModel.setProjectPath("/home/jacob/Desktop/testing"); 
//		mavenModel.setProjectVersion("1.0");
//		IURCapMaven emptyProject = new URCapProjectModel(mavenModel);
//		
//		
//		String invokeMessage = invoker.invokeGenerator(emptyProject);
//		assertEquals("0", invokeMessage); //Another project on path should not matter
//	}
//	
//	/**
//	 * Run after testOnProjectAlreadyExits(). Should not create same project. 
//	 */
//	@Test
//	public void testOnSameArtifact() {
//		mavenModel.setArchetypeVersionAPI("1.5.0");
//		mavenModel.setArchetypeVersion("1.5.0");
//		mavenModel.setProjectGroupId("some.new.groupId");
//		mavenModel.setProjectArtifactId("ExistingProject");
//		mavenModel.setProjectPath("/home/jacob/Desktop/testing"); 
//		mavenModel.setProjectVersion("1.0");
//		IURCapMaven emptyProject = new URCapProjectModel(mavenModel);
//		
//		
//		String invokeMessage = invoker.invokeGenerator(emptyProject);
//		assertNotEquals("0", invokeMessage); 
//	}
//	
//
//	
//	/**
//	 * Test different input fpr group or artifact id
//	 * slashes
//	 * dot
//	 * parenthesis
//	 * spaces
//	 */
//	@Test
//	public void testInvalidId() {
//		String invokeMessage;
//		mavenModel.setArchetypeVersionAPI("1.5.0");
//		mavenModel.setArchetypeVersion("1.5.0");
//		mavenModel.setProjectGroupId("groupid");
//		mavenModel.setProjectArtifactId("//MyartifactWithSlash");
//		mavenModel.setProjectPath("/home/jacob/Desktop/testing"); 
//		mavenModel.setProjectVersion("1.0");
//		IURCapMaven projectSlash = new URCapProjectModel(mavenModel);
//		
//		//If invokeGenerator returned the invoke exit code, this would be easier to test!
//		invokeMessage = invoker.invokeGenerator(projectSlash);
//		assertEquals("0", invokeMessage);  
//		
//		//Test underscore
//		mavenModel.setArchetypeVersionAPI("1.5.0");
//		mavenModel.setArchetypeVersion("1.5.0");
//		mavenModel.setProjectGroupId("groupid");
//		mavenModel.setProjectArtifactId("_MyartifactWithUnderscore");
//		mavenModel.setProjectPath("/home/jacob/Desktop/testing"); 
//		mavenModel.setProjectVersion("1.0");
//		IURCapMaven projectUnderscore = new URCapProjectModel(mavenModel);
//		
//		//If invokeGenerator returned the invoke exit code, this would be easier to test!
//		invokeMessage = invoker.invokeGenerator(projectUnderscore);
//		assertEquals("0", invokeMessage);  
//		
//		//Test underscore
//		mavenModel.setArchetypeVersionAPI("1.5.0");
//		mavenModel.setArchetypeVersion("1.5.0");
//		mavenModel.setProjectGroupId("groupid");
//		mavenModel.setProjectArtifactId("MyartifactWithDots..");
//		mavenModel.setProjectPath("/home/jacob/Desktop/testing"); 
//		mavenModel.setProjectVersion("1.0");
//		IURCapMaven projectDots = new URCapProjectModel(mavenModel);
//		
//		//If invokeGenerator returned the invoke exit code, this would be easier to test!
//		invokeMessage = invoker.invokeGenerator(projectDots);
//		assertEquals("0", invokeMessage);  
//		
//		String[] chars = {"!", "\"", "#", "¤", "$", "%", "&", "(", "{"};
//		
//		for (int i = 0; i < chars.length; i++) {
//			String artifact = "MyArtifactWithChar" + chars[i];
//			mavenModel.setArchetypeVersionAPI("1.5.0");
//			mavenModel.setArchetypeVersion("1.5.0");
//			mavenModel.setProjectGroupId("groupid");
//			mavenModel.setProjectArtifactId(artifact);
//			mavenModel.setProjectPath("/home/jacob/Desktop/testing"); 
//			mavenModel.setProjectVersion("1.0");
//			IURCapMaven projectCharacters = new URCapProjectModel(mavenModel);
//			
//			//If invokeGenerator returned the invoke exit code, this would be easier to test!
//			invokeMessage = invoker.invokeGenerator(projectCharacters);
//			assertEquals("0", invokeMessage);  
//		}
//		
//	}
//	
//	@Test
//	public void testNodeGenerator() {
//		//Test installation node
//	    //Correct node
//		
//	
//		
//		
//		
//		
//	}
	
	@Test
	public void testCorrectNode() {
		testNode("service", "contribution", "view", "title", "com.company.testcompany", "MyURCap", "/home/jacob/Desktop/testing"); //Test correct node
	}
	
	@Test
	public void testNodeSameClassnames() {

		testNode("serviceSame", "contributionSame", "viewSame", "title", "com.company.testcompany", "MyURCap", "/home/jacob/Desktop/testing"); //Test correct node
		testNode("serviceSame", "contributionSame", "viewSame", "title", "com.company.testcompany", "MyURCap", "/home/jacob/Desktop/testing"); //Test correct node
	}
	
	@Test
	public void testNodeTitle() {
		testNode("servicetitle", "contributiontitle", "viewtitle", " \" title", "com.company.testcompany", "MyURCap", "/home/jacob/Desktop/testing"); 
		testNode("servicetitle2", "contributiontitle2", "viewtitle2", "-3¤title", "com.company.testcompany", "MyURCap", "/home/jacob/Desktop/testing"); 
	}
	
	@Test
	public void testNodeClassnameChars() {
		testNode("..service", "..contribution", "..view", "title", "com.company.testcompany", "MyURCap", "/home/jacob/Desktop/testing"); 
		testNode(" \"service", " \"contribution", " \"view", "title", "com.company.testcompany", "MyURCap", "/home/jacob/Desktop/testing"); 
		testNode("_service", "_contribution", "_view", "title", "com.company.testcompany", "MyURCap", "/home/jacob/Desktop/testing"); 
	}
	
	@Test
	public void testNodeNoPath() {
		testNode("servicePath", "contributionPath", "viewPath", "title", "com.company.testcompany", "MyURCapNotExisting", "/home/jacob/Desktop/testing/non-existing");
	}
	
	
	
	
	private void testNode(String service, String contribution, String view, String title, String groupId, String artifactId, String path) {
		MavenModel installationNodeMavenModel = new InstallationNodeModel(service, contribution, view, title); //changes these parameters to weird stuff. Should not break
		installationNodeMavenModel.setProjectPath("/home/jacob/Desktop/testing");
		installationNodeMavenModel.setProjectGroupId(groupId); 
		installationNodeMavenModel.setProjectArtifactId(artifactId); //Change to existing
		installationNodeMavenModel.setProjectVersion("1.0");
		IURCapMaven installationNode = new InstallationNodeMavenModel(installationNodeMavenModel);
		String invokeInstallationNode = invoker.invokeGenerator(installationNode);
		assertEquals("0", invokeInstallationNode);
	}
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
