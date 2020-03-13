package installationnode;

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
import modelClasses.installationnode.InstallationNodeMavenModel;
import modelClasses.installationnode.InstallationNodeModel;
import processControl.ProgressBarDialog;

/**
 * Sets up the wizard for program node installation Adds the pages to the wizard
 * 
 * @author jacob
 *
 */
public class InstallationNodeWizard extends Wizard {

	private SetClassesNamePage setClassesPage;
	private SetAttributesPage setAttributesPage;
	private IURCapMaven nodeModel;
	private String artifactId, projectPath, groupId;
	private IMavenHandler mavenHandler;

	public InstallationNodeWizard(String artifactId, String path, String groupId) {
		super();
		this.artifactId = artifactId;
		this.projectPath = path;
		this.groupId = groupId;
		this.mavenHandler = new MavenInvokerHandler();
		setNeedsProgressMonitor(true);
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

		String serviceClassName = this.setClassesPage.getServiceClassname();
		String viewClassName = this.setClassesPage.getViewClassname();
		String contributionClassName = this.setClassesPage.getContributionClassname();
		String nodeTitle = this.setAttributesPage.getNodeTitle();

		MavenModel mavenModel = new InstallationNodeModel(serviceClassName, contributionClassName, viewClassName,
				nodeTitle);

		mavenModel.setProjectPath(this.projectPath);
		mavenModel.setProjectGroupId(this.groupId); 
		mavenModel.setProjectArtifactId(this.artifactId);
		mavenModel.setProjectVersion("1.0");

		this.nodeModel = new InstallationNodeMavenModel(mavenModel);


		Shell shell = getShell();
		
		String invokeMessage = addInstallationNodeToProject(shell, mavenModel, nodeModel);

		if (invokeMessage != "") {
			MessageDialog.openWarning(shell, "Maven Execution Message", invokeMessage);
		} else {
			MessageDialog.openInformation(shell, "Installation node message",
					"The installation node has succesfully been added to the project!" + "\n" + "Please right-click the project and Refresh the project to see result.");
		}

		return true;
	}
	
	public String addInstallationNodeToProject(Shell parent, MavenModel mavenModel, IURCapMaven nodeModel) {
		InstallationNodeProgressBar dpb = new InstallationNodeProgressBar(parent, mavenModel, nodeModel);
		dpb.initGuage();
		dpb.open();

		return dpb.invokeMessage;
	}
	
	
	private class InstallationNodeProgressBar extends ProgressBarDialog {

		private String[] info = null;
		private IURCapMaven nodeModel;
		public String invokeMessage;


		public InstallationNodeProgressBar(Shell parent, MavenModel mavenModel,  IURCapMaven nodeModel) {
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
			this.setShellTitle("Adding program node to the project");
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
