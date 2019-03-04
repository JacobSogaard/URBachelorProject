package urcapplugin.toolbar.handler;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.common.NotDefinedException;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.ui.handlers.HandlerUtil;
import org.osgi.framework.BundleContext;
import testplugin.*;

import urcaplugin.wizard.URCapWizard;

/**
 * Class to start up the wizard when toolbar icon is clicked.
 * 
 * @author jacob
 *
 */
public class ShowWizard extends AbstractHandler {
	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		TestPluginClass sss = new TestPluginClass();
		sss.helloworld();
		URCapWizard wizard = new URCapWizard();
		WizardDialog dialog = new WizardDialog(HandlerUtil.getActiveShell(event), wizard);	
		dialog.open();
		return null;
	}

}
