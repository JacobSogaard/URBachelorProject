package installationnode;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IPath;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.ISelectionService;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;

import wizardmanager.NodeWizard;

/**
 * Wizard page to set the class names for service, view and contribution class,
 * on a program node.
 * 
 * @author jacob
 *
 */
public class SetClassesNamePage extends NodeWizard {

	private Text viewClassName, serviceClassName, contributionClassName;
	private Label viewInputLabel, serviceInputLabel, contributionInputLabel;
	private Composite container;
	private GridLayout layout;
	private final String VIEW_INPUT_LABEL = "Installation node view";
	private final String SERVICE_INPUT_LABEL = "Installation node service";
	private final String CONTRIBUTION_INPUT_LABEL = "Installation node contribution";
	private String viewInputText, serviceInputText, contributionInputText;
	
	
	private String testpath = "";

	private void init() {
		IWorkbenchWindow window = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
		ISelectionService selection = window.getSelectionService();
		IStructuredSelection structured = (IStructuredSelection) selection.getSelection("org.eclipse.jdt.ui.PackageExplorer");

		IFile file = (IFile) structured.getFirstElement();
		IPath path = file.getLocation();
		this.testpath = path.toPortableString();
		

	}

	protected SetClassesNamePage() {
		super("Set Installation Node Classes Page");
		//this.init();
		setTitle("Setup Installation Node Classes");
		//setDescription("Set names of classes in installation node (Standard names recommended)");
		setDescription(testpath);
		setPageComplete(true);
	}

	/**
	 * Creates the input fields of the wizard page.
	 */
	@Override
	public void createControl(Composite parent) {
		this.container = new Composite(parent, SWT.NONE);
		this.layout = new GridLayout();
		this.container.setLayout(this.layout);
		this.layout.numColumns = 1;

		this.setInputFieldsText();

		// Set view input and label
		this.viewInputLabel = new Label(container, SWT.NONE);
		this.viewClassName = new Text(container, SWT.BORDER | SWT.SINGLE);

		// IWorkbenchWindow window =
		// PlatformUI.getWorkbench().getActiveWorkbenchWindow();
		// ISelection selection =
		// window.getSelectionService().getSelection("org.eclipse.jdt.ui.PackageExplorer");

		createInputForm(this.viewClassName, this.viewInputText, this.viewInputLabel, VIEW_INPUT_LABEL, false);

		// Set service input and label
		this.serviceInputLabel = new Label(container, SWT.NONE);
		this.serviceClassName = new Text(container, SWT.BORDER | SWT.SINGLE);
		createInputForm(this.serviceClassName, this.serviceInputText, this.serviceInputLabel, SERVICE_INPUT_LABEL,
				false);

		// Set contribution input and label
		this.contributionInputLabel = new Label(container, SWT.NONE);
		this.contributionClassName = new Text(container, SWT.BORDER | SWT.SINGLE);
		createInputForm(this.contributionClassName, this.contributionInputText, this.contributionInputLabel,
				CONTRIBUTION_INPUT_LABEL, false);

		GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		this.setAllLayout(gd);
		setControl(container);

	}

	/**
	 * Sets the layout grid for input fields
	 * 
	 * @param gd
	 */
	private void setAllLayout(GridData gd) {
		this.viewClassName.setLayoutData(gd);
		this.serviceClassName.setLayoutData(gd);
		this.contributionClassName.setLayoutData(gd);
	}

	/**
	 * Method to set the text of the input fields. Should be refactored to get
	 * artifact id from project selected i project explorer.
	 */
	private void setInputFieldsText() {
		this.viewInputText = "InstallationNodeView";
		this.serviceInputText = "InstallationNodeService";
		this.contributionInputText = "InstallationNodeContribution";
	}

	/**
	 * Checks to see if the next button should be active. Check if the input fields
	 * are empty.
	 */
	@Override
	public boolean canFlipToNextPage() {
		return (!this.viewClassName.getText().isEmpty() && !this.serviceClassName.getText().isEmpty()
				&& !this.contributionClassName.getText().isEmpty());
	}

	/**
	 * Get the input from view class name field
	 * 
	 * @return view class name as String
	 */
	public String getViewClassname() {
		return this.viewClassName.getText();
	}

	/**
	 * Get the input from service class name field
	 * 
	 * @return view class name as String
	 */
	public String getServiceClassname() {
		return this.serviceClassName.getText();
	}

	/**
	 * Get the input from contribution class name field
	 * 
	 * @return view class name as String
	 */
	public String getContributionClassname() {
		return this.contributionClassName.getText();
	}

}
