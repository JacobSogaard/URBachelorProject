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

		this.projectModel = new URCapProjectModel(urcapSetupPage.getProjectModel());
		String path = urcapSetupPage.getProjectModel().getProjectPath();
		String id = urcapSetupPage.getProjectModel().getProjectArtifactId();

		String executeMessage = executeGenerateImportProjectDefault(shell, path, id);

		MessageDialog.openInformation(shell, "Message",executeMessage);

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

	public String process1GenerateImportProject(Shell shell, String path, String id) {

		String invokeMessage = mavenHandler.invokeGenerator(this.projectModel);
		String message = "";

		if (invokeMessage != "") {
			message = invokeMessage;

		} else {
			// Imports the newly created project to the package explorer.
			message = mavenHandler.importMavenProject(path, id);

		}

		return message;

	}

	public void process2SpaceTime() {
		try {
			Thread.sleep(2000);

		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void process3SetMavenNature() {
		// Sets the nature one the project
		MavenProjectImporter importer = new MavenProjectImporter();
		IProject project = importer.getProject();
		IPath projectpath = project.getLocation();
		setNewProjectNature(projectpath, project);
	}

	public String executeGenerateImportProjectDefault(Shell shell, String path, String id) {

		emptyProjectProgressBar dpb = new emptyProjectProgressBar(shell, path, id);
		dpb.initGuage();
		dpb.open();

		return dpb.invokeMessage;
	}

	private class emptyProjectProgressBar extends ProgressBarDialog {

		private String[] info = null;
		private Shell parent;
		private String path, id;
		public String invokeMessage;

		public emptyProjectProgressBar(Shell parent, String path, String id) {
			super(parent);
			this.parent = parent;
			this.path = path;
			this.id = id;
		}

		@Override
		public void initGuage() {
			info = new String[100];
			for (int i = 0; i < info.length; i++) {
				info[i] = "process task " + i + ".";
			}
			this.setExecuteTime(4);
			this.setMayCancel(true);
			this.setProcessMessage("please wait....");
			this.setShellTitle("Creating new URCap project");
		}

		@Override
		protected String process(int arg0) {
			if (arg0 == 1) {
				this.invokeMessage = process1GenerateImportProject(parent, path, id);
				return info[arg0 - 1];
			} else if (arg0 == 2) {
				process2SpaceTime();
				return info[arg0 - 1];
			} else if (arg0 == 3) {
				process3SetMavenNature();
				return info[arg0 - 1];
			} else {
				return info[arg0 - 1];
			}

		}

	}

}