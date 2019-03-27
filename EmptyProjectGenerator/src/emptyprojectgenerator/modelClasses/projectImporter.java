package emptyprojectgenerator.modelClasses;

import org.eclipse.m2e.core.project.AbstractProjectScanner;
import org.eclipse.m2e.core.project.IMavenProjectImportResult;
import org.eclipse.m2e.core.project.IProjectConfigurationManager;
import org.eclipse.m2e.core.project.LocalProjectScanner;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.List;

import org.apache.maven.model.Model;
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
import org.eclipse.ui.wizards.datatransfer.FileSystemStructureProvider;
import org.eclipse.ui.wizards.datatransfer.ImportOperation;

public class projectImporter {

	private IProjectConfigurationManager configurationManager;
	private IMaven maven;
	private IProject project;
	private IWorkspaceRoot workspaceRoot;
	private Collection<MavenProjectInfo> infoList;
	private MavenProjectInfo projectInfo;
	private ProjectImportConfiguration config;


	public projectImporter() {

		this.configurationManager = MavenPlugin.getProjectConfigurationManager();
		this.maven = MavenPlugin.getMaven();
		this.workspaceRoot = ResourcesPlugin.getWorkspace().getRoot();
		

	}

	public void importProjectAsMavenProject() {
		

		try {
			this.importProject("C:\\Users\\Bruger\\EmptyMap\\test01", new NullProgressMonitor());
			//this.openCreatedProject();
	
		} catch (CoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 

	}

	public void importProject(String location, IProgressMonitor monitor) throws CoreException, InterruptedException {
	    MavenModelManager mavenModelManager = MavenPlugin.getMavenModelManager();
	    File root = ResourcesPlugin.getWorkspace().getRoot().getLocation().toFile();
	    AbstractProjectScanner<MavenProjectInfo> scanner = new LocalProjectScanner(root, location, false, mavenModelManager);
	    scanner.run(monitor);
	    List<MavenProjectInfo> projects = scanner.getProjects();
	    List<MavenProjectInfo> mavenProjects = new ArrayList<MavenProjectInfo>();
	    findChildMavenProjects(mavenProjects, projects);
	 //   mavenProjects = filterProjects(mavenProjects);
	    ProjectImportConfiguration importConfiguration = new ProjectImportConfiguration();
	    IProjectConfigurationManager projectConfigurationManager = MavenPlugin.getProjectConfigurationManager();
	    List<IMavenProjectImportResult> importedprojects =  projectConfigurationManager.importProjects(mavenProjects, importConfiguration, monitor);
	    
//		IProjectDescription description = ResourcesPlugin.getWorkspace().loadProjectDescription(new Path("C:\\Users\\Bruger\\EmptyMap\\test01\\.project"));
//		IProject projectToOpen = ResourcesPlugin.getWorkspace().getRoot().getProject(description.getName());
//		projectToOpen.create(description, null);
//		projectToOpen.open(null);
		
		IWorkspaceRoot rootworkspace = ResourcesPlugin.getWorkspace().getRoot();
		Collection<IProject> projectList = new LinkedHashSet<>();
		Collection<MavenProjectInfo> toImport = new LinkedHashSet<>();
		
		//Separate existing projects from new ones
		for (MavenProjectInfo projectInfo : mavenProjects) {
			File pom = projectInfo.getPomFile();
			IContainer container = rootworkspace.getContainerForLocation(new Path(pom.getAbsolutePath()));
			if (container == null) {
				toImport.add(projectInfo);
			} else {
				IProject project = container.getProject();
				project.open(null);
				
			}
		}
		
		

	}
	
	private static  void findChildMavenProjects( List<MavenProjectInfo> results, Collection<MavenProjectInfo> infos )
    {
        for( MavenProjectInfo info : infos )
        {
            results.add( info );

            Collection<MavenProjectInfo> children = info.getProjects();

            if( !children.isEmpty() )
            {
                findChildMavenProjects( results, children );
            }
        }
    }
    
	private void openCreatedProject() throws CoreException, InvocationTargetException, InterruptedException {
		
		IProjectDescription description = ResourcesPlugin.getWorkspace().loadProjectDescription(new Path("C:\\Users\\Bruger\\EmptyMap\\test01\\.project"));
		
		IProject projectToOpen = ResourcesPlugin.getWorkspace().getRoot().getProject(description.getName());
		projectToOpen.create(description, null);
		projectToOpen.open(null);
		
		IOverwriteQuery overwriteQue= new IOverwriteQuery() {
			
			@Override
			public String queryOverwrite(String pathString) {
				// TODO Auto-generated method stub
				return ALL;
			}
			
			
		};
		
		ImportOperation importOperation = new ImportOperation(projectToOpen.getFullPath(), new File("C:\\Users\\Bruger\\EmptyMap\\test01"),FileSystemStructureProvider.INSTANCE, overwriteQue);
	
		importOperation.setCreateContainerStructure(false);
		importOperation.run(new NullProgressMonitor());
	}
	
}
