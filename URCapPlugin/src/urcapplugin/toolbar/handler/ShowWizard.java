package urcapplugin.toolbar.handler;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.ui.handlers.HandlerUtil;

import urcaplugin.wizard.URCapWizard;

/**
 * Class to start up the wizard when toolbar icon is clicked.
 * @author jacob
 *
 */
public class ShowWizard extends AbstractHandler{
	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		URCapWizard wizard = new URCapWizard();
		WizardDialog dialog = new WizardDialog(HandlerUtil
				.getActiveShell(event), wizard);
		dialog.open();
		this.runInstallScript();
		return null;
	}
	
	
	//https://stackoverflow.com/questions/525212/how-to-run-unix-shell-script-from-java-code
	private void runInstallScript() {
		
	}

}
