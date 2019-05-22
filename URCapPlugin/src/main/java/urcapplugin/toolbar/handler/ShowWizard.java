package urcapplugin.toolbar.handler;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.common.NotDefinedException;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.ui.handlers.HandlerUtil;

import wizardmanager.IWizardFactory;
import wizardmanager.WizardFactory;

/**
 * Class to start up the wizard when toolbar icon is clicked. Opens the wizard for setting up a new URCap project. 
 * 
 * @author jacob
 *
 */
public class ShowWizard extends AbstractHandler {
	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		IWizardFactory factory = new WizardFactory();
		Wizard wizard;
		try {
			wizard = factory.getWizard(event.getCommand().getName(), "", "", "");
			WizardDialog dialog = new WizardDialog(HandlerUtil.getActiveShell(event), wizard);
			dialog.setHelpAvailable(true);
			dialog.open();

		} catch (NullPointerException | NotDefinedException ex) {
			System.err.println("No sutible wizard class found: " + ex);
		}

		return null;
	}

}
