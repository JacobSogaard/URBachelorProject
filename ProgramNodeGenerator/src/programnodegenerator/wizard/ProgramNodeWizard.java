package programnodegenerator.wizard;

import org.eclipse.jface.wizard.Wizard;

import generator.ProgramNodeGenerator;
import programnodegenerator.modelClasses.ProgramNodeModel;

public class ProgramNodeWizard extends Wizard{
	
	private SetClassesNamePage setClassesPage;
	private SetAttributesPage setAttributesPage;
	private ProgramNodeModel nodeModel;

	public ProgramNodeWizard() {
		super();
		setNeedsProgressMonitor(true);
	}
	
	@Override
	public void addPages() {
		this.setClassesPage = new SetClassesNamePage();
		this.setAttributesPage = new SetAttributesPage();
		addPage(this.setClassesPage);
		addPage(this.setAttributesPage);
	}
	
	@Override
	public boolean performFinish() {
		// TODO Auto-generated method stub
		String serviceClassname = this.setClassesPage.getServiceClassname();
		String viewClassname = this.setClassesPage.getViewClassname();
		String contributionClassname = this.setClassesPage.getContributionClassname();
		String nodeId = this.setAttributesPage.getNodeId();
		String nodeTitle = this.setAttributesPage.getNodeTitle();
		
		
		this.nodeModel = new ProgramNodeModel(serviceClassname, viewClassname, 
				contributionClassname, nodeId, nodeTitle);
		
		System.out.println(this.nodeModel.toString());
		
		ProgramNodeGenerator png = new ProgramNodeGenerator(this.nodeModel);
		
		return true;
	}

}
