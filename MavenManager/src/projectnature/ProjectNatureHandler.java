package projectnature;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IAdapterManager;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.handlers.HandlerUtil;
import org.eclipse.ui.statushandlers.StatusManager;

import nature.URCapNature;

public class ProjectNatureHandler{

	//private IProject project;
	private static JavaCore jn;

	public ProjectNatureHandler() {
	}

	public void setNature(IProject project) {
		IProject project1  = project;// get project...
		IProjectDescription description;
		try {
			description = project1.getDescription();
			String[] natures = description.getNatureIds();
			String[] newNatures = new String[natures.length + 1];
			System.arraycopy(natures, 0, newNatures, 0, natures.length);
			newNatures[natures.length] = URCapNature.NATURE_ID;

			// validate the natures
			IWorkspace workspace = ResourcesPlugin.getWorkspace();
			IStatus status = workspace.validateNatureSet(newNatures);

			// only apply new nature, if the status is ok
			System.out.println("status: " + status.getMessage());
			if (status.getCode() == IStatus.OK) {
				description.setNatureIds(newNatures);
			
				project1.setDescription(description, null);
			}
		} catch (CoreException e) {
			e.printStackTrace();
		}

	}

//	@Override
//	public Object execute(ExecutionEvent event) throws ExecutionException {
//		ISelection currentSelection = HandlerUtil.getCurrentSelection(event);
//        if (currentSelection instanceof IStructuredSelection) {
//
//            Object firstElement = ((IStructuredSelection) currentSelection).getFirstElement();
//
//            // Get an IResource as an adapter from the current selection
//            IAdapterManager adapterManager = Platform.getAdapterManager();
//            IResource resourceAdapter = adapterManager.getAdapter(firstElement, IResource.class);
//
//            if (resourceAdapter != null) {
//                IResource resource = resourceAdapter;
//                IProject project = resource.getProject();
//                try {
//                    IProjectDescription description = project.getDescription();
//                    String[] natures = description.getNatureIds();
//                    String[] newNatures = new String[natures.length + 1];
//                    System.arraycopy(natures, 0, newNatures, 0, natures.length);
//
//                    // add our new "com.example.project.examplenature" id
//                    newNatures[natures.length] = URCapNature.NATURE_ID;
//
//                    // validate the natures
//                    IWorkspace workspace = ResourcesPlugin.getWorkspace();
//                    IStatus status = workspace.validateNatureSet(newNatures);
//
//                    // only apply new nature, if the status is ok
//                    if (status.getCode() == IStatus.OK) {
//                        description.setNatureIds(newNatures);
//                        project.setDescription(description, null);
//                    }
//
//                    return status;
//                } catch (CoreException e) {
//                    throw new ExecutionException(e.getMessage(), e);
//                }
//            }
//        }
//
//        return Status.OK_STATUS;
//	}

}
