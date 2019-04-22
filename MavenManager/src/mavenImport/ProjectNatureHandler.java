package mavenImport;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;

import projectnatures.URCapProjectNature;

public class ProjectNatureHandler {

	private IProject project;
	private IProjectDescription description;
	
	public ProjectNatureHandler(IProject project) {
		this.project = project;
		try {
			this.description = project.getDescription();
			String[] natures = this.description.getNatureIds();
			String[] newNatures = new String[natures.length + 1];
			System.arraycopy(natures, 0, newNatures, 0, natures.length);
			newNatures[natures.length] = URCapProjectNature.NATURE_ID;

			// validate the natures
			IWorkspace workspace = ResourcesPlugin.getWorkspace();
			IStatus status = workspace.validateNatureSet(newNatures);

			// only apply new nature, if the status is ok
			if (status.getCode() == IStatus.OK) {
			    description.setNatureIds(newNatures);
			    project.setDescription(description, null);
			}
		} catch (CoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
	}
	
	public IProject getProject() {
		return this.project;
	}
}
