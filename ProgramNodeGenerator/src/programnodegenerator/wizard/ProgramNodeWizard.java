package programnodegenerator.wizard;

import org.eclipse.jface.wizard.Wizard;

import generator.ProgramNodeGenerator;
import programnodegenerator.modelClasses.ProgramNodeModel;

/**
 * Sets up the wizard for program node installation
 * Adds the pages to the wizard
 * @author jacob
 *
 */
public class ProgramNodeWizard extends Wizard{
	
	private SetClassesNamePage setClassesPage;
	private SetAttributesPage setAttributesPage;
	private ProgramNodeModel nodeModel;

	public ProgramNodeWizard() {
		super();
		setNeedsProgressMonitor(true);
	}
	
	/**
	 * Adds the pages to the wizard. 
	 */
	@Override
	public void addPages() {
		this.setClassesPage = new SetClassesNamePage();
		this.setAttributesPage = new SetAttributesPage();
		addPage(this.setClassesPage);
		addPage(this.setAttributesPage);
	}
	
	/**
	 * Takes the program node information filled out in the wizard and creates a 
	 * programNodeModel class from them 
	 */
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
		
		//Generate the program node classes using the program node model.
		ProgramNodeGenerator png = new ProgramNodeGenerator(this.nodeModel);
		
		return true;
	}

}
