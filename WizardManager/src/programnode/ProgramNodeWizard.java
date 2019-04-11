package programnode;

import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Cursor;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import mavenGenerator.URCapProjectGenerator;
import modelClasses.NodeModel;
import modelClasses.ProgramNodeModel;
import modelClasses.ProgramNodeProjectModel;
import modelClasses.URCapProjectModel;

/**
 * Sets up the wizard for program node installation
 * Adds the pages to the wizard
 * @author jacob
 *
 */
public class ProgramNodeWizard extends Wizard{
	
	private SetClassesNamePage setClassesPage;
	private SetAttributesPage setAttributesPage;
	private NodeModel nodeModel;

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
		//this.setAttributesPage.setGeneratingLabel(); //does not work
		String serviceClassname = this.setClassesPage.getServiceClassname();
		String viewClassname = this.setClassesPage.getViewClassname();
		String contributionClassname = this.setClassesPage.getContributionClassname();
		String nodeId = this.setAttributesPage.getNodeId();
		String nodeTitle = this.setAttributesPage.getNodeTitle();
		
		
		this.nodeModel = new ProgramNodeModel(serviceClassname, viewClassname, 
				contributionClassname, nodeId, nodeTitle);
		
		//Generate the program node classes using the program node model.
		URCapProjectGenerator prgen = new URCapProjectGenerator();
		
		String projectPath = ResourcesPlugin.getWorkspace().getRoot().getLocation().toString() + "/" + "MyArtifactId";
		URCapProjectModel prmodel = new ProgramNodeProjectModel("MyGroupId", "MyArtifactId", "1.0", projectPath, nodeModel);
		
		Display display = Display.getDefault();
		Cursor waitCursor = new Cursor(display, SWT.CURSOR_WAIT);		
		Shell shell = getShell();
		shell.setCursor(waitCursor);
		
		prgen.generate(prmodel);	
		//Import project here!
		shell.setCursor(null);
		waitCursor.dispose();
		
		
		return true;
	}

}
