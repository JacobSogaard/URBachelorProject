package urcapplugin.toolbar.handler;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.common.NotDefinedException;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.ui.ISelectionService;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.handlers.HandlerUtil;

import projectnature.ProjectNatureHandler;
import wizardmanager.WizardFactory;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;

/**
 * Class to open the program node wizard.
 * 
 * @author jacob
 *
 */
public class ShowNodeWizard extends AbstractHandler {

	private String projectArtifactId = "No changed", projectPath;

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		System.out.println(ResourcesPlugin.getWorkspace().getRoot().getLocation().toString() + "/" + "MyArtifactId");
		//if (this.selectedProject()) {
			WizardFactory factory = new WizardFactory();
			Wizard wizard;

			try {
				wizard = factory.getWizard(event.getCommand().getDescription(), this.projectArtifactId,
						this.projectPath);
				WizardDialog dialog = new WizardDialog(HandlerUtil.getActiveShell(event), wizard);
				dialog.open();

			} catch (NullPointerException | NotDefinedException ex) {
				System.err.println("No sutible wizard class found");
			}
		//}

		return null;
	}

	/**
	 * Method to get the selection from package explorer TODO: Add multiple null and
	 * type checks!
	 */
	private boolean selectedProject() {
		
		IWorkbenchWindow window = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
		ISelectionService selection = window.getSelectionService();
		IStructuredSelection structured = (IStructuredSelection) selection
				.getSelection("org.eclipse.jdt.ui.PackageExplorer");
		IJavaProject javaProject = (IJavaProject) structured.getFirstElement();
		IProject project = javaProject.getProject();
		
		try {
			this.projectArtifactId = project.getDescription().getName();
			String[] natures = project.getDescription().getNatureIds();
			for (String nature : natures) {
				if (nature.equals("URCapProjectNature.urcapprojectnature")) {
					return true;
				}

			}
		} catch (CoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // gives null

		this.projectPath = project.getProject().getFullPath().toPortableString(); // works
		return false;
	}

}
