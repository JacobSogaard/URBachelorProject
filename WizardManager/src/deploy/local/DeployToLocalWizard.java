package deploy.local;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.swt.widgets.Shell;

import deployModels.DeployURSimLocal;
import deployModels.IDeploy;
import modelClasses.IURCapMaven;
import modelClasses.MavenModel;
import processControl.ProgressBarDialog;

/**
 * Sets up the wizard for deploy to robot wizard page Adds the pages to the
 * wizard
 * 
 * @author jacob
 *
 */
public class DeployToLocalWizard extends Wizard {
	private DeployToLocalWizardPage deployLocalWizard;
	private String projectPath, artifactID;

	public DeployToLocalWizard(String path, String projectArtifactId) {
		super();
		setNeedsProgressMonitor(true);
		this.projectPath = path;
		this.artifactID = projectArtifactId;
		
	}
	


	/**
	 * Adds the pages to the wizard.
	 */
	@Override
	public void addPages() {
		deployLocalWizard = new DeployToLocalWizardPage();
		addPage(deployLocalWizard);
		
		deployLocalWizard.setProjectPathDeploy(this.projectPath);
	}

	/**
	 * Takes the program node information filled out in the wizard and creates a
	 * programNodeModel class from them
	 */
	@Override
	public boolean performFinish() {
		
		Shell shell = getShell();
		IDeploy model = new DeployURSimLocal(this.deployLocalWizard.getURSimPath(), this.projectPath, this.artifactID);
		
		String resultMessage = deployProject(shell, model);
		
		MessageDialog.openInformation(shell, "Deploy Message", resultMessage);

		return true;
	}
	public String deployProject(Shell parent, IDeploy model) {
		DeployProgressBar dpb = new DeployProgressBar(parent, model);
		dpb.initGuage();
		dpb.open();

		return dpb.invokeMessage;
	}

	
	
	private class DeployProgressBar extends ProgressBarDialog {

		private String[] info = null;
		public String invokeMessage;
		private IDeploy model;


		public DeployProgressBar(Shell parent, IDeploy model) {
			super(parent);
			this.model = model;
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
			this.setShellTitle("Deploying the project");
		}

		@Override
		protected String process(int times) {
			if (times == 1) {
				this.invokeMessage = model.deploy();
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
