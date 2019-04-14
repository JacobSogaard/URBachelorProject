package installationnode;

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
import modelClasses.URCapProjectModel;
import modelClasses.installationnode.InstallationNodeMavenModel;
import modelClasses.installationnode.InstallationNodeModel;
import modelClasses.programnode.ProgramNodeModel;
import modelClasses.programnode.ProgramNodeMavenModel;

/**
 * Sets up the wizard for program node installation
 * Adds the pages to the wizard
 * @author jacob
 *
 */
public class InstallationNodeWizard extends Wizard{
	
	private SetClassesNamePage setClassesPage;
	private SetAttributesPage setAttributesPage;
	private NodeModel nodeModel;

	public InstallationNodeWizard() {
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
		String serviceClassName = this.setClassesPage.getServiceClassname();
		String viewClassName = this.setClassesPage.getViewClassname();
		String contributionClassName = this.setClassesPage.getContributionClassname();
		String nodeTitle = this.setAttributesPage.getNodeTitle();
		String projectPath = ResourcesPlugin.getWorkspace().getRoot().getLocation().toString() + "/" + "MyArtifactId";
		
		this.nodeModel = new InstallationNodeModel(serviceClassName, viewClassName, contributionClassName, nodeTitle, "MyGroupId", "MyArtifactId", "1.0", projectPath);
		
		//Generate the program node classes using the program node model.
		URCapProjectGenerator prgen = new URCapProjectGenerator();
		
		
		URCapProjectModel prmodel = new InstallationNodeMavenModel(nodeModel);
		
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
