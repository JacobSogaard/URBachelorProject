package test;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import org.apache.maven.shared.utils.io.FileUtils;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import urcapplugin.toolbar.handler.ConvertToURCapHandler;

public class ConvertToURCapHandlerTest {
	
	private static ConvertToURCapHandler converter;
	private String projectName;
	private String nature;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		converter = new ConvertToURCapHandler();
		//copyProjects();
		
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
	@Test
	public void testSetURProjectNature() throws ExecutionException, CoreException {
		//IProject project;
		

		
		
		
		fail("not yet implemented");
		
	}
	
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
	
	@Test
	public void testNatureExists() throws CoreException, ExecutionException {
		IProject project = getProject("URCapNatureExists");
		String[] natures = getNatures(project);
		IStatus status = (IStatus) converter.setURProjectNature(null, project);
		project = getProject("URCapNatureExists");
		natures = getNatures(project);
		
		assertEquals(status.getCode(), IStatus.OK);
		assertTrue(nature.contains("")); //insert urcap nature
		
		
	}
	
	@Test
	public void testMultipleNaturesExists() throws CoreException, ExecutionException {
		IProject project = getProject("URCapMultipleNatureExists");
		String[] natures = getNatures(project);
		IStatus status = (IStatus) converter.setURProjectNature(null, project);
		project = getProject("URCapMultipleNatureExists");
		natures = getNatures(project);
		
		assertEquals(status.getCode(), IStatus.OK);
		assertTrue(nature.contains("")); //insert urcap nature
		
	}
	
	@Test
	public void testInvalidNaturesExists() throws CoreException, ExecutionException {
		IProject project = getProject("URCapInvalidNatureExists");
		String[] natures = getNatures(project);
		IStatus status = (IStatus) converter.setURProjectNature(null, project);
		project = getProject("URCapInvalidNatureExists");
		natures = getNatures(project);
		
		assertTrue(status.getCode() == IStatus.ERROR || status.getCode() == IStatus.WARNING);  //Not sure if error or warning
		assertTrue(nature.contains("")); //insert urcap nature
	
		
	}
	
	
	
	private IProject getProject(String projectName) {
		return ResourcesPlugin.getWorkspace().getRoot().getProject(projectName);
	}
	
	private String[] getNatures(IProject project) throws CoreException {
		return project.getDescription().getNatureIds();
		
	}
	
	//Does not work yet
	private static void copyProjects(String project) {
		 File sourceLocation= new File("/home/jacob/junit-workspace-init/" + project);
	     File targetLocation = new File("/home/jacob/junit-workspace");

	     try {
			FileUtils.copyDirectory(sourceLocation, targetLocation);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


}
