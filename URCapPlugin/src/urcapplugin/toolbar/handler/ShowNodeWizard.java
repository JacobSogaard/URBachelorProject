package urcapplugin.toolbar.handler;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.common.NotDefinedException;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.ISelectionService;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.handlers.HandlerUtil;

import projectnature.ProjectNatureHandler;
import verifier.ProjectSelectionVerifier;
import wizardmanager.WizardFactory;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;

/**
 * Class to open the program node wizard.
 * 
 * @author jacob
 *
 */
public class ShowNodeWizard extends AbstractHandler {

	private ProjectSelectionVerifier projectVerifier = new ProjectSelectionVerifier();

	public ShowNodeWizard() {

	}

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		if (this.projectVerifier.selectedProject()) {
			WizardFactory factory = new WizardFactory();
			Wizard wizard;
			PomFileReader pomReader = new PomFileReader();

			String groupId = pomReader.getGroupId(this.projectVerifier.getPath());

			try {
				wizard = factory.getWizard(event.getCommand().getDescription(),
						this.projectVerifier.getProjectArtifactId(), this.projectVerifier.getPath().toString(),
						groupId);
				WizardDialog dialog = new WizardDialog(HandlerUtil.getActiveShell(event), wizard);
				dialog.setHelpAvailable(true);
				dialog.open();

			} catch (NullPointerException | NotDefinedException ex) {
				System.err.println("No sutible wizard class found");
			}

		} else {
			MessageDialog.openError(HandlerUtil.getActiveShell(event), "WARNING!",
					"This is not an URCap project. Please convert it to a URCap project by right-click -> configure -> convert to URCap");
		}

		return null;
	}

}
