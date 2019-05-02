package Verifier;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.ISelectionService;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;

public class ProjectSelectionVerifier {
	
	private String projectArtifactId = "", projectPath;

	public ProjectSelectionVerifier() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Method to get the selection from package explorer TODO: Add null and type
	 * checks!
	 */
	public boolean selectedProject() {

		IWorkbenchWindow window = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
		ISelectionService selection = window.getSelectionService();
		IStructuredSelection structured = (IStructuredSelection) selection
				.getSelection("org.eclipse.jdt.ui.PackageExplorer");
		IJavaProject javaProject = (IJavaProject) structured.getFirstElement();
		IProject project = javaProject.getProject();

		try {
			this.setProjectArtifactId(project.getDescription().getName());
			String[] natures = project.getDescription().getNatureIds();
			for (String nature : natures) {
				if (nature.equals("URCapProjectNature.urcapprojectnature")) {
					return true;
				}

			}
		} catch (CoreException e) {
			e.printStackTrace();
		} // gives null

		this.setProjectPath(project.getProject().getFullPath().toPortableString()); // works
		return false;
	}

	public String getProjectArtifactId() {
		return projectArtifactId;
	}

	private void setProjectArtifactId(String projectArtifactId) {
		this.projectArtifactId = projectArtifactId;
	}

	public String getProjectPath() {
		return projectPath;
	}

	private void setProjectPath(String projectPath) {
		this.projectPath = projectPath;
	}
	
	
	
	
}
