package emptyproject;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Cursor;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;

import mavenGenerator.IMavenHandler;
import mavenGenerator.MavenInvokerHandler;
import mavenImport.MavenProjectImporter;
import modelClasses.IURCapMaven;
import modelClasses.URCapProjectModel;
import nature.URCapNature;
import processControl.ProgressBarDialog;

/**
 * Class which handles pages in wizard. Each page should have it's own class and
 * instances of the should be created here.
 * 
 * @author jacob
 *
 */
public class URCapWizard extends Wizard {

	protected SetupURCapPage urcapSetupPage;
	private IURCapMaven projectModel;
	private IMavenHandler mavenHandler;

	public URCapWizard() {
		super();
		setNeedsProgressMonitor(true);
		this.mavenHandler = new MavenInvokerHandler();
	}

	@Override
	public void addPages() {
		this.urcapSetupPage = new SetupURCapPage();
		addPage(urcapSetupPage);
	}

	@Override
	public boolean performFinish() {
		Shell shell = getShell();

//		ProcessExecutionManager executionManager = new ProcessExecutionManager();
//
//		this.projectModel = new URCapProjectModel(urcapSetupPage.getProjectModel());
//		String path = urcapSetupPage.getProjectModel().getProjectPath();
//		String id = urcapSetupPage.getProjectModel().getProjectArtifactId();
//
//		executionManager.executeTask(shell, path, id);
		
		executeGenerateImportProjectDefault(shell);

		return true;
	}

	public void setNewProjectNature(IPath path, IProject project) {

		PomFileReader pomreader = new PomFileReader();
		if (pomreader.validateProjectAsURCap(path)) {
			try {

				IProjectDescription description = project.getDescription();
				String[] natures = description.getNatureIds();

				String[] newNatures = new String[natures.length + 1];
				System.arraycopy(natures, 0, newNatures, 0, natures.length);

				// Trying to set URCap nature as the first nature.
				String copyNature = newNatures[0];
				newNatures[0] = URCapNature.NATURE_ID;
				newNatures[natures.length] = copyNature;

				// validate the natures
				IWorkspace workspace = ResourcesPlugin.getWorkspace();
				IStatus status = workspace.validateNatureSet(newNatures);

				// only apply new nature, if the status is ok
				if (status.getCode() == IStatus.OK) {
					description.setNatureIds(newNatures);
					project.setDescription(description, null);

				}

			} catch (CoreException e) {
				e.printStackTrace();
			}
		}
	}

	public String executeGenerateImportProject(Shell shell, String path, String id) {

		String invokeMessage = mavenHandler.invokeGenerator(this.projectModel);
		String message = "";

		if (invokeMessage != "") {
			message = invokeMessage;

		} else {
			// Imports the newly created project to the package explorer.
			message = mavenHandler.importMavenProject(path, id);
			try {
				Thread.sleep(15000);

			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			// Sets the nature one the project
			MavenProjectImporter importer = new MavenProjectImporter();
			IProject project = importer.getProject();
			IPath projectpath = project.getLocation();
			setNewProjectNature(projectpath, project);

		}

		return message;

	}

	public String executeGenerateImportProjectDefault(Shell shell) {

		DemoProgressBar dpb = new DemoProgressBar(shell);
		dpb.initGuage();
		dpb.open();

		return "";
	}

	private class DemoProgressBar extends ProgressBarDialog {

		private String[] info = null;

		public DemoProgressBar(Shell parent) {
			super(parent);
		}

		@Override
		public void initGuage() {
			info = new String[100];
			for (int i = 0; i < info.length; i++) {
				info[i] = "process task " + i + ".";
			}
			this.setExecuteTime(15);
			this.setMayCancel(true);
			this.setProcessMessage("please waiting....");
			this.setShellTitle("Demo");

		}

		@Override
		protected String process(int arg0) {
			try {
				Thread.sleep(1000);
			} catch (Exception e) {
				e.printStackTrace();
			}

			return info[arg0 - 1];
		}

	}

	public class ProcessExecutionManager {
		
		public ProcessExecutionManager() {
			// TODO Auto-generated constructor stub
		}

		public void executeTask(Shell shell, String path, String id) {
			Job job = new Job("First Job") {
				@Override
				protected IStatus run(IProgressMonitor monitor) {
					
					String executeMessage = executeGenerateImportProject(shell, path, id);
					// doLongThing();

					syncWithUi(shell, "");
					// use this to open a Shell in the UI thread
					
					return Status.OK_STATUS;
				}

			};
			job.setUser(true);
			job.schedule();
		}

		private void syncWithUi(Shell shell, String messag) {
			Display.getDefault().asyncExec(new Runnable() {
				public void run() {
					//MessageDialog.openInformation(shell, "Import Project Message", message);

				}
			});

		}
	}

	// __________________________________________________NOT USED
	// CODE____________________________________________________________________________//

	private void Tempholder() {
		this.projectModel = new URCapProjectModel(urcapSetupPage.getProjectModel());
		// Display display = Display.getDefault();
		// Cursor waitCursor = new Cursor(display, SWT.CURSOR_WAIT);
		Shell shell = getShell();
		// shell.setCursor(waitCursor);

		// Executes the maven command.
		String invokeMessage = mavenHandler.invokeGenerator(this.projectModel);

		if (invokeMessage != "") {
			MessageDialog.openWarning(shell, "Maven Execution Message", invokeMessage);

		} else {
			// Imports the newly created project to the package explorer.
			String message = mavenHandler.importMavenProject(urcapSetupPage.getProjectModel().getProjectPath(),
					urcapSetupPage.getProjectModel().getProjectArtifactId());

			try {
				Thread.sleep(15000);

			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			// Sets the nature one the project
			MavenProjectImporter importer = new MavenProjectImporter();
			IProject project = importer.getProject();
			IPath path = project.getLocation();
			setNewProjectNature(path, project);

			MessageDialog.openInformation(shell, "Import project message", message);
		}
		// shell.setCursor(null);
		// waitCursor.dispose();
	}

}