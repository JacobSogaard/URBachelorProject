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
import modelClasses.toolbarnode.ToolbarNodeProjectModel;
import processControl.ProgressBarDialog;

/**
 * Sets up the wizard for program node installation Adds the pages to the wizard
 * 
 * @author jacob
 * @param <InstallationNodeProgressBar>
 *
 */
public class ToolbarNodeWizard<InstallationNodeProgressBar> extends Wizard {

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

		this.nodeModel = new ToolbarNodeProjectModel(mavenModel);

		Shell shell = getShell();

		String invokeMessage = addInstallationNodeToProject(shell, mavenModel, nodeModel);
		
		if (invokeMessage != "") {
			MessageDialog.openWarning(shell, "Maven Execution Message", invokeMessage);
		} else {
			MessageDialog.openInformation(shell, "Toolbar node message",
					"The toolbar node has succesfully been added to the project!" + "\n"
							+ "Please right-click the project and Refresh the project to see result.");
		}

		return true;
	}
	
	
	public String addInstallationNodeToProject(Shell parent, MavenModel mavenModel, IURCapMaven nodeModel) {
		ToolbarNodeProgressBar dpb = new ToolbarNodeProgressBar(parent, mavenModel, nodeModel);
		dpb.initGuage();
		dpb.open();

		return dpb.invokeMessage;
	}

	
	
	private class ToolbarNodeProgressBar extends ProgressBarDialog {

		private String[] info = null;
		private IURCapMaven nodeModel;
		public String invokeMessage;


		public ToolbarNodeProgressBar(Shell parent, MavenModel mavenModel,  IURCapMaven nodeModel) {
			super(parent);
			this.nodeModel = nodeModel;
		}

		@Override
		public void initGuage() {
			info = new String[100];
			for (int i = 0; i < info.length; i++) {
				info[i] = "process task " + i + ".";
			}
			this.setExecuteTime(3);
			this.setMayCancel(true);
			this.setProcessMessage("please wait....");
			this.setShellTitle("Adding toolbar node to the project");
		}

		@Override
		protected String process(int times) {
			if (times == 1) {
				this.invokeMessage = mavenHandler.invokeGenerator(this.nodeModel);
				return info[times - 1];

			} else if (times == 2) {

				processSpaceTime();

			} else {

				return info[times - 1];
			}
			return info[times - 1];
					}
		
		private void processSpaceTime() {
			try {
				Thread.sleep(2000);

			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
}
