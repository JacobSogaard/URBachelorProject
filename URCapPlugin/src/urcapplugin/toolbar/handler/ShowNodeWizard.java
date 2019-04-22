package urcapplugin.toolbar.handler;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.common.NotDefinedException;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.ui.handlers.HandlerUtil;
import wizardmanager.WizardFactory;

/**
 * Class to open the program node wizard.
 * 
 * @author jacob
 *
 */
public class ShowNodeWizard extends AbstractHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		System.out.println(ResourcesPlugin.getWorkspace().getRoot().getLocation().toString() + "/" + "MyArtifactId");
		
		WizardFactory factory = new WizardFactory();
		Wizard wizard;

		try {
			wizard = factory.getWizard(event.getCommand().getDescription());
			WizardDialog dialog = new WizardDialog(HandlerUtil.getActiveShell(event), wizard);
			dialog.open();

		} catch (NullPointerException | NotDefinedException ex) {
			System.err.println("No sutible wizard class found");
		}

	
		return null;
	}

}
