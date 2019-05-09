package urcapplugin.toolbar.handler;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.common.NotDefinedException;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.Plugin;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.handlers.HandlerUtil;
import org.osgi.framework.BundleContext;

import deployModels.*;
import emptyproject.URCapWizard;
import wizardmanager.IWizardFactory;
import wizardmanager.WizardFactory;

/**
 * Class to start up the wizard when toolbar icon is clicked.
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
			wizard = factory.getWizard(event.getCommand().getDescription(), "", "", "");
			WizardDialog dialog = new WizardDialog(HandlerUtil.getActiveShell(event), wizard);
			dialog.setHelpAvailable(true);
			dialog.open();

		} catch (NullPointerException | NotDefinedException ex) {
			System.err.println("No sutible wizard class found: " + ex);
		}

		return null;
	}

}
