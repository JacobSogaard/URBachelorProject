package toolbarnode;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Cursor;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import mavenGenerator.IMavenHandler;
import mavenGenerator.MavenInvokerHandler;
import modelClasses.IURCapMaven;
import modelClasses.MavenModel;
import modelClasses.toolbarnode.ToolbarNodeModel;
import modelClasses.toolbarnode.ToolbarNodeMavenModel;

/**
 * Sets up the wizard for program node installation Adds the pages to the wizard
 * 
 * @author jacob
 *
 */
public class ToolbarNodeWizard extends Wizard {

	private SetClassesNamePage setClassesPage;
	private SetAttributesPage setAttributesPage;

	private IURCapMaven nodeModel;
	private String artifactId, groupId, projectPath;
	private IMavenHandler mavenHandler;

	public ToolbarNodeWizard(String artifactId, String path, String groupId) {
		super();
		setNeedsProgressMonitor(true);
		this.artifactId = artifactId;
		this.projectPath = path;
		this.groupId = groupId;
		this.mavenHandler = new MavenInvokerHandler();

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
		iconPath = iconPath.replace("\\", "\\\\");

		MavenModel mavenModel = new ToolbarNodeModel(iconPath, serviceClassName, contributionClassName);
		mavenModel.setProjectPath(this.projectPath);
		mavenModel.setProjectGroupId(this.groupId);
		mavenModel.setProjectArtifactId(this.artifactId);
		mavenModel.setProjectVersion("1.0");

		this.nodeModel = new ToolbarNodeMavenModel(mavenModel);

		Display display = Display.getDefault();
		Cursor waitCursor = new Cursor(display, SWT.CURSOR_WAIT);
		Shell shell = getShell();
		shell.setCursor(waitCursor);

		// Executes the maven command and checks whether it has been a succes or not.
		String invokeMessage = mavenHandler.invokeGenerator(this.nodeModel);
		if (invokeMessage != "") {
			MessageDialog.openWarning(shell, "Maven Execution Message", "The toolbar node has succesfully been added to the project!" + "\n" + "Please right-click the project and Refresh the project to see result.");
		} else {
			MessageDialog.openInformation(shell, "Toolbar node message",
					"The toolbar node has succesfully been added to the project!" + "\n"
							+ "Please right-click the project and Refresh the project to see result.");
		}

		shell.setCursor(null);
		waitCursor.dispose();

		return true;
	}

}
