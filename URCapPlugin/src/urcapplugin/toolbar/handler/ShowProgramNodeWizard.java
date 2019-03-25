package urcapplugin.toolbar.handler;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.ui.handlers.HandlerUtil;

import programnodegenerator.modelClasses.SelectedProject;
import programnodegenerator.wizard.ProgramNodeWizard;

public class ShowProgramNodeWizard extends AbstractHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		SelectedProject selected = new SelectedProject();
		ProgramNodeWizard wizard = new ProgramNodeWizard(); 
		WizardDialog dialog = new WizardDialog(HandlerUtil.getActiveShell(event), wizard);
		dialog.open();
		return null;
	}
	
}
