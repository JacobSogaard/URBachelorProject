package urcapplugin.toolbar.handler;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IAdapterManager;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.handlers.HandlerUtil;

import urcapplugin.projectnatures.URCapProjectNature;

/**
 * Handler for the URCap project nature. Credit:
 * https://www.vogella.com/tutorials/EclipseProjectNatures/article.html#project-natures-in-eclipse
 * 
 * @author jacob
 *
 */
public class URCapProjectNatureHandler extends AbstractHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		ISelection currentSelection = HandlerUtil.getCurrentSelection(event);
		System.out.println("before IStructuredSelection check" + currentSelection.toString());
		if (currentSelection instanceof IStructuredSelection) {

			Object firstElement = ((IStructuredSelection) currentSelection).getFirstElement();
			System.out.println("first element: " + firstElement.toString());
			// Get an IResource as an adapter from the current selection
			IAdapterManager adapterManager = Platform.getAdapterManager();
			IResource resourceAdapter = adapterManager.getAdapter(firstElement, IResource.class);

			if (resourceAdapter != null) {
				System.out.println("ressource adapte != null");
				IResource resource = resourceAdapter;
				IProject project = resource.getProject();
				try {
					IProjectDescription description = project.getDescription();
					System.out.println("Project description: " + description.toString());
					String[] natures = description.getNatureIds();
					String[] newNatures = new String[natures.length + 1];
					System.arraycopy(natures, 0, newNatures, 0, natures.length);
					System.out.println("new natures: " + newNatures);
					// add our new "com.ur.urcap.URCapProjectNature" id
					newNatures[natures.length] = URCapProjectNature.NATURE_ID;

					// validate the natures
					IWorkspace workspace = ResourcesPlugin.getWorkspace();
					IStatus status = workspace.validateNatureSet(newNatures);

					// only apply new nature, if the status is ok
					if (status.getCode() == IStatus.OK) {
						description.setNatureIds(newNatures);
						project.setDescription(description, null);
					}
					System.out.println("return status: " + status);
					return status;
				} catch (CoreException e) {
					throw new ExecutionException(e.getMessage(), e);
				}
			}
		}
		System.out.println("return status last");
		return Status.OK_STATUS;
	}
}
