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
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.handlers.HandlerUtil;

import nature.URCapNature;

public class ConvertToURCapHandler extends AbstractHandler {
	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {

		ISelection currentSelection = HandlerUtil.getCurrentSelection(event);

		if (currentSelection instanceof IStructuredSelection) {

			Object firstElement = ((IStructuredSelection) currentSelection).getFirstElement();

			// Get an IResource as an adapter from the current selection
			IAdapterManager adapterManager = Platform.getAdapterManager();
			IResource resourceAdapter = adapterManager.getAdapter(firstElement, IResource.class);

			if (resourceAdapter != null) {
				IResource resource = resourceAdapter;
				IProject project = resource.getProject();

				// Validating if the project is an URCap.
				IPath path = project.getLocation();
				PomFileReader pomreader = new PomFileReader();
				if (pomreader.validateProjectAsURCap(path)) {
					try {
						IProjectDescription description = project.getDescription();
						String[] natures = description.getNatureIds();

						String[] newNatures = new String[natures.length + 1];
						System.arraycopy(natures, 0, newNatures, 0, natures.length);

						// Trying to set URCap nature as the first nature.
						String copyNature = newNatures[0];
						newNatures[0] = URCapNature.NATURE_ID;
						newNatures[natures.length] = copyNature;

						// validate the natures
						IWorkspace workspace = ResourcesPlugin.getWorkspace();
						IStatus status = workspace.validateNatureSet(newNatures);

						// only apply new nature, if the status is ok
						if (status.getCode() == IStatus.OK) {
							description.setNatureIds(newNatures);
							project.setDescription(description, null);
							MessageDialog.openInformation(HandlerUtil.getActiveShell(event), "Message",
									"Project was successfully converted to a URCap project." + "\n" + "Please right-click the project and Refresh the project to see result.");

						}
						return status;

					} catch (CoreException e) {
						throw new ExecutionException(e.getMessage(), e);
					}
				} else {
					MessageDialog.openWarning(HandlerUtil.getActiveShell(event), "WARNING",
							"The Project is not an URCap project and therefore it cannot be converted.");
				}
			}
		}

		return Status.OK_STATUS;
	}
	
	

}
