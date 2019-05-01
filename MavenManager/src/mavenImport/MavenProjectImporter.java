package mavenImport;

import org.eclipse.m2e.core.project.AbstractProjectScanner;
import org.eclipse.m2e.core.project.IMavenProjectImportResult;
import org.eclipse.m2e.core.project.IProjectConfigurationManager;
import org.eclipse.m2e.core.project.LocalProjectScanner;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import java.util.List;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.m2e.core.MavenPlugin;
import org.eclipse.m2e.core.embedder.IMaven;
import org.eclipse.m2e.core.embedder.MavenModelManager;
import org.eclipse.m2e.core.project.MavenProjectInfo;
import org.eclipse.m2e.core.project.ProjectImportConfiguration;
import org.eclipse.ui.dialogs.IOverwriteQuery;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import projectnature.ProjectNatureHandler;

/**
 * Class to import existing maven project to eclipse workspace and shows the
 * project in project explorer Code used from:
 * https://www.javatips.net/api/liferay-ide-master/maven/plugins/com.liferay.ide.maven.core/src/com/liferay/ide/maven/core/MavenUtil.java
 * 
 * @author Thanya
 *
 */
public class MavenProjectImporter {

	private IProjectConfigurationManager configurationManager;
	private IMaven maven;
	private IProject project;
	private IWorkspaceRoot workspaceRoot;
	private Collection<MavenProjectInfo> infoList;
	private MavenProjectInfo projectInfo;
	private ProjectImportConfiguration config;

	public MavenProjectImporter() {

		this.configurationManager = MavenPlugin.getProjectConfigurationManager();
		this.maven = MavenPlugin.getMaven();
		this.workspaceRoot = ResourcesPlugin.getWorkspace().getRoot();

	}

	/**
	 * Imports existing maven project from path to eclipse workspace
	 * 
	 * @param path Path of the maven project as String
	 */
	public void importProjectAsMavenProject(String path) {

		try {
			this.importProject(path, new NullProgressMonitor());

		} catch (CoreException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

//		ProjectNatureHandler natureHandler = new ProjectNatureHandler();
//		natureHandler.setNature(this.project);

	}

	/**
	 * Imports project from a path. Uses the workspace root and path of project
	 * 
	 * @param location
	 * @param monitor
	 * @throws CoreException
	 * @throws InterruptedException
	 */
	private void importProject(String location, IProgressMonitor monitor) throws CoreException, InterruptedException {
		MavenModelManager mavenModelManager = MavenPlugin.getMavenModelManager();
		File root = ResourcesPlugin.getWorkspace().getRoot().getLocation().toFile();
		AbstractProjectScanner<MavenProjectInfo> scanner = new LocalProjectScanner(root, location, false,
				mavenModelManager);
		scanner.run(monitor);
		List<MavenProjectInfo> projects = scanner.getProjects();
		List<MavenProjectInfo> mavenProjects = new ArrayList<MavenProjectInfo>();
		findChildMavenProjects(mavenProjects, projects);
		ProjectImportConfiguration importConfiguration = new ProjectImportConfiguration();
		IProjectConfigurationManager projectConfigurationManager = MavenPlugin.getProjectConfigurationManager();
		List<IMavenProjectImportResult> importedprojects = projectConfigurationManager.importProjects(mavenProjects,
				importConfiguration, monitor);

		IWorkspaceRoot rootworkspace = ResourcesPlugin.getWorkspace().getRoot();
		Collection<IProject> projectList = new LinkedHashSet<>();
		Collection<MavenProjectInfo> toImport = new LinkedHashSet<>();
		// Separate existing projects from new ones
		for (MavenProjectInfo projectInfo : mavenProjects) {
			File pom = projectInfo.getPomFile();
			IContainer container = rootworkspace.getContainerForLocation(new Path(pom.getAbsolutePath()));
			if (container == null) {
				toImport.add(projectInfo);
			} else {
				this.project = container.getProject();
				this.project.open(null);
				if (this.project.hasNature("org.eclipse.jdt.core.javanature")
						|| this.project.hasNature("org.eclipse.m2e.core.maven2Nature")) {
					ProjectNatureHandler handler = new ProjectNatureHandler();
					handler.setNature(this.project);
				}
			}
		}

	}

	private static void findChildMavenProjects(List<MavenProjectInfo> results, Collection<MavenProjectInfo> infos) {
		for (MavenProjectInfo info : infos) {
			results.add(info);

			Collection<MavenProjectInfo> children = info.getProjects();

			if (!children.isEmpty()) {
				findChildMavenProjects(results, children);
			}
		}
	}

}
