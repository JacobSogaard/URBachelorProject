package test;

import static org.junit.Assert.*;

import java.io.File;
import java.net.URI;
import java.util.Map;

import org.eclipse.core.resources.FileInfoMatcherDescription;
import org.eclipse.core.resources.IBuildConfiguration;
import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IPathVariableManager;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.IProjectNature;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceFilterDescription;
import org.eclipse.core.resources.IResourceProxy;
import org.eclipse.core.resources.IResourceProxyVisitor;
import org.eclipse.core.resources.IResourceVisitor;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourceAttributes;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IPluginDescriptor;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.QualifiedName;
import org.eclipse.core.runtime.content.IContentTypeMatcher;
import org.eclipse.core.runtime.jobs.ISchedulingRule;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import urcapplugin.toolbar.handler.PomFileReader;

public class PomFileReaderTest {
	
	private static PomFileReader pfr;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		pfr = new PomFileReader();
	}

	@Before
	public void setUp() throws Exception {
	}

	/**
	 * TEST:
	 *  no pom.xml
	 *  invalid path
	 *  no urcap.install.host
	 */
	@Test
	public void testValidateProjectAsURCap() {
		
		//Test with different projects. 
		//Test with invalid project
		IProject project = getProject("some project"); //Has to be in workspace
		IPath path = project.getLocation();
		boolean isURProject = pfr.validateProjectAsURCap(path);
		assertTrue(isURProject); 
		
		
	}

	@Test
	public void testGetGroupId() {
		
		//Test with different projects with different group ids. 
		IProject project = getProject("some project"); //Has to be in workspace
		IPath path = project.getLocation();
		String groupId = pfr.getGroupId(path);
		assertEquals("GroupId", groupId);
	}
	
	private IProject getProject(String projectName) {
		return ResourcesPlugin.getWorkspace().getRoot().getProject(projectName);
	}
}
