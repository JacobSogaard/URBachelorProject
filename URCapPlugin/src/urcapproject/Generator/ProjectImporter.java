package urcapproject.Generator;

import java.io.File;

import org.apache.maven.model.Model;
import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.m2e.core.MavenPlugin;
import org.eclipse.m2e.core.project.MavenProjectInfo; 

public class ProjectImporter {
	
	private MavenProjectInfo info;
	private File file;
	private Model model;
	
	
	public ProjectImporter() {
	 
	 this.file = new File("C:\\Users\\Bruger\\eclipse-workspace\\test01\\pom.xml");
	 info = new MavenProjectInfo("project", this.file, model, info);
		
	}
	
	public void mavenProjectImporter() {
		
		IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
		
		File pom = info.getPomFile();
		IContainer container = root.getContainerForLocation(new Path(pom.getAbsolutePath()));
		
		if(container != null) {
			IProject project = container.getProject();
			try {
				project.open(null);
			} catch (CoreException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	

}
