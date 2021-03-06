package urcapplugin.toolbar.handler.deploy;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.common.NotDefinedException;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.ui.handlers.HandlerUtil;

import verifier.ProjectSelectionVerifier;
import wizardmanager.WizardFactory;

/**
 * 
 * @author jacob
 *
 */
public class ShowDeployWizard extends AbstractHandler {

	private ProjectSelectionVerifier projectVerifier = new ProjectSelectionVerifier();

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {

		if (this.projectVerifier.selectedProject()) {
			WizardFactory wizardFactory = new WizardFactory();
			Wizard wizard;
			try {
				System.out.println(event.getCommand().getName());
				wizard = wizardFactory.getWizard(event.getCommand().getName(),
						this.projectVerifier.getProjectArtifactId(), this.projectVerifier.getProjectPath(), "");
				openWizard(wizard, event);
			} catch (NotDefinedException e) {
				e.printStackTrace();
			}
		} else {
			MessageDialog.openError(HandlerUtil.getActiveShell(event), "WARNING!",
					"This is not an URCap project. Please convert it to a URCap project by right-click -> configure -> convert to URCap");
		}

		return null;
	}

	private void openWizard(Wizard wizard, ExecutionEvent event) {
		WizardDialog dialog = new WizardDialog(HandlerUtil.getActiveShell(event), wizard);
		dialog.open();
	}

}
