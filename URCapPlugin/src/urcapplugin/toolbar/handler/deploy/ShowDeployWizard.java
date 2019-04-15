package urcapplugin.toolbar.handler.deploy;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.common.NotDefinedException;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.ui.handlers.HandlerUtil;

import wizardmanager.WizardFactory;

public class ShowDeployWizard extends AbstractHandler {
	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		WizardFactory wizardFactory = new WizardFactory();
		Wizard wizard;
		try {
			System.out.println(event.getCommand().getName());
			wizard = wizardFactory.getWizard(event.getCommand().getName());
			openWizard(wizard, event);
		} catch (NotDefinedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
    }
	
	
	private void openWizard(Wizard wizard, ExecutionEvent event) {
		WizardDialog dialog = new WizardDialog(HandlerUtil.getActiveShell(event), wizard);
		dialog.open();
	}

}
