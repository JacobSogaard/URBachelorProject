package java.convertToUrCapTester;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import org.apache.maven.shared.utils.io.FileUtils;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import mavenGenerator.MavenInvokerHandler;
import mavenImport.MavenProjectImporter;
import modelClasses.IURCapMaven;
import modelClasses.MavenModel;
import modelClasses.URCapModel;
import modelClasses.URCapProjectModel;
import nature.URCapNature;
import urcapplugin.toolbar.handler.ConvertToURCapHandler;

public class ConvertToURCapHandlerTest {
	
	private static ConvertToURCapHandler converter;
	private String nature;


	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		converter = new ConvertToURCapHandler();
		MavenInvokerHandler invoker = new MavenInvokerHandler();
		MavenModel mavenModel = new URCapModel();
		invoker = new MavenInvokerHandler();
		mavenModel = new URCapModel();
		mavenModel.setArchetypeVersionAPI("1.5.0");
		mavenModel.setArchetypeVersion("1.5.0");
		mavenModel.setProjectGroupId("com.company.testcompany");
		mavenModel.setProjectArtifactId("MyURCap");
		mavenModel.setProjectPath("/home/jacob/Desktop/testing/");
		mavenModel.setProjectVersion("1.0");
		IURCapMaven emptyProject = new URCapProjectModel(mavenModel);
		
		String invokeMessage = invoker.invokeGenerator(emptyProject);	
		
		MavenProjectImporter importer = new MavenProjectImporter();
		importer.importProjectAsMavenProject("/home/jacob/Desktop/testing", "MyURCap");
		
		
	}

	@Before
	public void setUp() throws Exception {
	}

	/**
	 * TEST:
	 *  No natures
	 *  No project with that name
	 *  No .project
	 *  URCap nature already exists
	 *  Many natures already
	 *  Has natures which are invalid
	 *  
	 *  
	 * @throws ExecutionException
	 * @throws CoreException 
	 */

	
	/**
	 * Test setNature on project with no natures before. 
	 * Should be succesful
	 * @throws CoreException
	 * @throws ExecutionException
	 */
	@Test
	public void testNoNature() throws CoreException, ExecutionException {
		
		
		IProject project = getProject("URCapNoNatures"); //This project HAS to be in workspace
		String[] natures = getNatures(project);
		assertEquals(natures.length, 0);
		
		converter.setURProjectNature(null, project);
		project = getProject("URCapNoNatures");
		natures = getNatures(project);
		assertEquals(natures.length, 1);
		
		
	}
	
//	@Test
//	public void testNatureExists() throws CoreException, ExecutionException {
//		IProject project = getProject("URCapNatureExists");
//		String[] natures = getNatures(project);
//		IStatus status = (IStatus) converter.setURProjectNature(null, project);
//		project = getProject("URCapNatureExists");
//		natures = getNatures(project);
//		
//		assertEquals(status.getCode(), IStatus.OK);
//		assertTrue(nature.contains(URCapNature.NATURE_ID)); //insert urcap nature
//		
//		
//	}
//	
//	@Test
//	public void testMultipleNaturesExists() throws CoreException, ExecutionException {
//		
//	
//		IProject project = getProject("MyURCap");
//		String[] natures = getNatures(project);
//		IStatus status = (IStatus) converter.setURProjectNature(null, project);
//		project = getProject("MyURCap");
//		natures = getNatures(project);
//		
//		assertEquals(status.getCode(), IStatus.OK);
//		assertTrue(nature.contains(URCapNature.NATURE_ID)); //insert urcap nature
//		
//	}
//	
//	@Test
//	public void testInvalidNaturesExists() throws CoreException, ExecutionException {
//		IProject project = getProject("URCapInvalidNatureExists");
//		String[] natures = getNatures(project);
//		IStatus status = (IStatus) converter.setURProjectNature(null, project);
//		project = getProject("URCapInvalidNatureExists");
//		natures = getNatures(project);
//		
//		assertTrue(status.getCode() == IStatus.ERROR || status.getCode() == IStatus.WARNING);  //Not sure if error or warning
//		assertTrue(nature.contains(URCapNature.NATURE_ID)); //insert urcap nature
//	
//		
//	}
//	
	
	
	private IProject getProject(String projectName) {
		return ResourcesPlugin.getWorkspace().getRoot().getProject(projectName);
	}
	
	private String[] getNatures(IProject project) throws CoreException {
		return project.getDescription().getNatureIds();
		
	}
}
