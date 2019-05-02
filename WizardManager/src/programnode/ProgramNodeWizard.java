package programnode;

import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Cursor;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import mavenGenerator.MavenInvokerHandler;
import modelClasses.IURCapMaven;
import modelClasses.MavenModel;
import modelClasses.URCapProjectModel;
import modelClasses.programnode.ProgramNodeModel;
import modelClasses.programnode.ProgramNodeProjectModel;

/**
 * Sets up the wizard for program node installation
 * Adds the pages to the wizard
 * @author jacob
 *
 */
public class ProgramNodeWizard extends Wizard{
	
	private SetClassesNamePage setClassesPage;
	private SetAttributesPage setAttributesPage;
	private IURCapMaven nodeModel;
	private String artifactId, groupId, path;

	public ProgramNodeWizard(String artifactId, String path, String groupId) {
		super();
		setNeedsProgressMonitor(true);
		this.artifactId = artifactId;
		this.path = path;
		this.groupId = groupId;
		
	}
	
	/**
	 * Adds the pages to the wizard. 
	 */
	@Override
	public void addPages() {
		this.setClassesPage = new SetClassesNamePage(this.artifactId);
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
		String serviceClassName = this.setClassesPage.getServiceClassname();
		String viewClassName = this.setClassesPage.getViewClassname();
		String contributionClassName = this.setClassesPage.getContributionClassname();
		String nodeId = this.setAttributesPage.getNodeId();
		String nodeTitle = this.setAttributesPage.getNodeTitle();
		//boolean setChildrenAllowed = this.setAttributesPage.isChildrenAllowed(); //TODO create method is attributes page
		boolean setChildrenAllowed = true;
		
		MavenModel mavenModel = new ProgramNodeModel(nodeTitle, nodeId, setChildrenAllowed, serviceClassName, contributionClassName, viewClassName);
		mavenModel.setProjectPath(this.path);
		mavenModel.setProjectGroupId(this.groupId);
		mavenModel.setProjectArtifactId(this.artifactId);
		mavenModel.setProjectVersion("1.0");
		
		this.nodeModel = new ProgramNodeProjectModel(mavenModel);
		
		//Generate the program node classes using the program node model.
		MavenInvokerHandler prgen = new MavenInvokerHandler();
		
		
		
		
		Display display = Display.getDefault();
		Cursor waitCursor = new Cursor(display, SWT.CURSOR_WAIT);		
		Shell shell = getShell();
		shell.setCursor(waitCursor);
		
		prgen.invokeMavenExecution(this.nodeModel);	
		
		shell.setCursor(null);
		waitCursor.dispose();
		
		
		return true;
	}

}
