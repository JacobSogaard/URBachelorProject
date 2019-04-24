package projectnature;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.ui.statushandlers.StatusManager;

import nature.URCapNature;

public class ProjectNatureHandler {
	
	private IProject project;
    private static JavaCore jn;
	public ProjectNatureHandler() {
		// TODO Auto-generated constructor stub
	}

	public void setNature(IProject project) {
		this.project = project;// get project...
		IProjectDescription description;
		try {
			description = this.project.getDescription();
			String[] natures = description.getNatureIds();
			String[] newNatures = new String[natures.length + 1];
			System.arraycopy(natures, 0, newNatures, 0, natures.length);
			newNatures[natures.length] = URCapNature.NATURE_ID;
			//jn = new JavaCore();
			//newNatures[natures.length+1] = jn.NATURE_ID;
			
			// validate the natures
			IWorkspace workspace = ResourcesPlugin.getWorkspace();
			IStatus status = workspace.validateNatureSet(newNatures);

			// only apply new nature, if the status is ok
			System.out.println("status: " + status.getMessage());
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
