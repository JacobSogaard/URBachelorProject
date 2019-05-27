package urcapplugin.toolbar.handler;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.common.NotDefinedException;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.ui.handlers.HandlerUtil;

import verifier.ProjectSelectionVerifier;
import wizardmanager.IWizardFactory;
import wizardmanager.WizardFactory;

/**
 * Class to open wizard for a specific node. 
 * Calls the WizardFactory from WizardManager module, with the description taken from the button
 * pressed in the toolbar dropdown menu. 
 * @author jacob
 *
 */
public class ShowNodeWizard extends AbstractHandler {

	private ProjectSelectionVerifier projectVerifier = new ProjectSelectionVerifier();
	private String artifactId = "", path = "";

	public ShowNodeWizard() {

	}

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		if (this.projectVerifier.selectedProject()) {
			IWizardFactory factory = new WizardFactory();
			Wizard wizard;
			PomFileReader pomReader = new PomFileReader();
			String groupId = pomReader.getGroupId(this.projectVerifier.getPath().toOSString());
			this.setWizardParameters();
			
			try {
				wizard = factory.getWizard(event.getCommand().getName(), this.artifactId,
						this.path, groupId);
				
				WizardDialog dialog = new WizardDialog(HandlerUtil.getActiveShell(event), wizard);
				dialog.setHelpAvailable(true);
				dialog.open();

			} catch (NullPointerException | NotDefinedException ex) {
				System.err.println("No sutible wizard class found: " + ex);
			}

		} else {
			MessageDialog.openError(HandlerUtil.getActiveShell(event), "WARNING!",
					"This is not an URCap project. Please convert it to a URCap project by right-click -> configure -> convert to URCap");
		}

		return null;
	}
	
	/**
	 * Checks that the artifact and path from the selected project is not null. 
	 */
	private void setWizardParameters() {
		if (this.projectVerifier.getProjectArtifactId() != null) {
			this.artifactId = this.projectVerifier.getProjectArtifactId();
		}
		
		if (this.projectVerifier.getPath() != null) {
			this.path = this.projectVerifier.getPath().toString();
		}
	}

}
