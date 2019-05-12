package mavenImport;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.m2e.core.MavenPlugin;
import org.eclipse.m2e.core.embedder.MavenModelManager;
import org.eclipse.m2e.core.project.AbstractProjectScanner;
import org.eclipse.m2e.core.project.IMavenProjectImportResult;
import org.eclipse.m2e.core.project.IProjectConfigurationManager;
import org.eclipse.m2e.core.project.LocalProjectScanner;
import org.eclipse.m2e.core.project.MavenProjectInfo;
import org.eclipse.m2e.core.project.ProjectImportConfiguration;


/**
 * Class to import existing maven project to eclipse workspace and shows the
 * project in project explorer Code used from:
 * https://www.javatips.net/api/liferay-ide-master/maven/plugins/com.liferay.ide.maven.core/src/com/liferay/ide/maven/core/MavenUtil.java
 * 
 * @author Thanya
 *
 */
public class MavenProjectImporter {

	private static IProject project;

	public MavenProjectImporter() {
	}

	/**
	 * Imports existing maven project from path to eclipse workspace
	 * 
	 * @param path Path of the maven project as String
	 */
	public String importProjectAsMavenProject(String path, String name) {

		String resultMessage = "";

		try {
			resultMessage = this.importProject(path, name, new NullProgressMonitor());

		} catch (CoreException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		return resultMessage;

	}

	/**
	 * Imports project from a path. Uses the workspace root and path of project
	 * 
	 * @param location
	 * @param monitors
	 * @throws CoreException
	 * @throws InterruptedException
	 */
	private String importProject(String location, String name, IProgressMonitor monitor)
			throws CoreException, InterruptedException {

		String resultMessage = "";

		MavenModelManager mavenModelManager = MavenPlugin.getMavenModelManager();
		File root = ResourcesPlugin.getWorkspace().getRoot().getLocation().toFile();
		AbstractProjectScanner<MavenProjectInfo> scanner = new LocalProjectScanner(root, location, false,
				mavenModelManager);
		scanner.run(monitor);
		List<MavenProjectInfo> projects = scanner.getProjects();
		List<MavenProjectInfo> mavenProjects = new ArrayList<MavenProjectInfo>();

		resultMessage = findChildMavenProjects(mavenProjects, projects, name);

		ProjectImportConfiguration importConfiguration = new ProjectImportConfiguration();
		IProjectConfigurationManager projectConfigurationManager = MavenPlugin.getProjectConfigurationManager();
		List<IMavenProjectImportResult> importedprojects = projectConfigurationManager.importProjects(mavenProjects,
				importConfiguration, monitor);

		IWorkspaceRoot rootworkspace = ResourcesPlugin.getWorkspace().getRoot();
		Collection<IProject> projectList = new LinkedHashSet<>();

		for (MavenProjectInfo projectInfo : mavenProjects) {
			File pom = projectInfo.getPomFile();
			IContainer container = rootworkspace.getContainerForLocation(new Path(pom.getAbsolutePath()));
			if (container != null) {
				project = container.getProject();
				project.open(null);
			} else {
				resultMessage = "Found no project to import";
			}

		}

		return resultMessage;

	}

	public IProject getProject() {
		return project;
	}

	
	/**
	 * Scans for maven projects using pom file in
	 * the defined path.
	 * @param results
	 * @param infos
	 * @param projectName
	 * @return
	 */
	private String findChildMavenProjects(List<MavenProjectInfo> results, Collection<MavenProjectInfo> infos,
			String projectName) {

		String message = "";

		for (MavenProjectInfo info : infos) {
			if (info.getPomFile().getAbsolutePath().contains(projectName)) {
				results.add(info);
				message = "The project: " + projectName + " has been added to your package explorer";
				Collection<MavenProjectInfo> children = info.getProjects();

				if (!children.isEmpty()) {
					findChildMavenProjects(results, children, projectName);
				}
			} 
		}

		return message;
	}
}
