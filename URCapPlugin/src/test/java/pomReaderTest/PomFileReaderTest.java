package pomReaderTest;

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
		String location = "/home/jacob/Desktop/testing/PomReaderTest/MyUrCap";
		boolean isURProject = pfr.validateProjectAsURCap(location);
		assertTrue(isURProject); 
	}
	
	@Test
	public void testValidateNoPomFile() {
		String location = "/home/jacob/Desktop/testing/PomReaderTest/NoPomFile";
		boolean isURProject = pfr.validateProjectAsURCap(location);
		assertTrue(!isURProject);
	}
	
	@Test
	public void testNotUrProject() {
		String location = "/home/jacob/Desktop/testing/PomReaderTest/NotUrProject";
		boolean isURProject = pfr.validateProjectAsURCap(location);
		assertTrue(!isURProject);
	}

	@Test
	public void testGetGroupId() {
		//Test with different projects with different group ids. 
		String location = "/home/jacob/Desktop/testing/PomReaderTest/MyUrCap";
		String groupId = pfr.getGroupId(location);
		assertEquals("com.ur.myurcompany", groupId);
	}
	
	@Test
	public void hasEmptyGroupidTag() {
		String location = "/home/jacob/Desktop/testing/PomReaderTest/EmptyGroupId";
		String groupId = pfr.getGroupId(location);
		assertEquals("", groupId);
	}
	
	@Test
	public void hasNoGroupidTag() {
		String location = "/home/jacob/Desktop/testing/PomReaderTest/NoGroupId";
		String groupId = pfr.getGroupId(location);
		assertEquals("", groupId);
	}
	


}
