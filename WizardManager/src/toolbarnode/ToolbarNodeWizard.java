package toolbarnode;

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
import modelClasses.toolbarnode.ToolbarNodeModel;
import modelClasses.toolbarnode.ToolbarNodeProjectModel;

/**
 * Sets up the wizard for program node installation
 * Adds the pages to the wizard
 * @author jacob
 *
 */
public class ToolbarNodeWizard extends Wizard{
	
	private SetClassesNamePage setClassesPage;
	private SetAttributesPage setAttributesPage;
	
	private IURCapMaven nodeModel;
	private String artifactId, groupId, path;

	public ToolbarNodeWizard(String artifactId, String path, String groupId) {
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
		this.setClassesPage = new SetClassesNamePage(artifactId);
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
		String serviceClassName = this.setClassesPage.getServiceClassname();
		String contributionClassName = this.setClassesPage.getContributionClassname();
		String iconPath = this.setAttributesPage.getIconPath();
		//boolean setChildrenAllowed = this.setAttributesPage.isChildrenAllowed(); //TODO create method is attributes page
		
		
		MavenModel mavenModel = new ToolbarNodeModel(iconPath, serviceClassName, contributionClassName);
		mavenModel.setProjectPath(this.path);
		mavenModel.setProjectGroupId(this.groupId);
		mavenModel.setProjectArtifactId(this.artifactId);
		mavenModel.setProjectVersion("1.0");
		
		this.nodeModel = new ToolbarNodeProjectModel(mavenModel);
		
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