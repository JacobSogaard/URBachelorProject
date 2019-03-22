package programnodegenerator.wizard;

import org.eclipse.jface.wizard.Wizard;

public class ProgramNodeWizard extends Wizard{
	
	protected SetClassesNamePage setClassesPage;

	public ProgramNodeWizard() {
		super();
		setNeedsProgressMonitor(true);
	}
	
	@Override
	public void addPages() {
		this.setClassesPage = new SetClassesNamePage();
		addPage(this.setClassesPage);
	}
	
	@Override
	public boolean performFinish() {
		// TODO Auto-generated method stub
		return true;
	}

}
