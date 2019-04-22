package installationnode;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import wizardmanager.NodeWizard;

/**
 * Wizard page to set the class names for service, view and contribution class,
 * on a program node.
 * 
 * @author jacob
 *
 */
public class SetClassesNamePage extends NodeWizard {

	private Text viewClassName, serviceClassName, contributionClassName, directoryText;
	private Label viewInputLabel, serviceInputLabel, contributionInputLabel;
	private Composite container;
	private GridLayout layout;
	private final String VIEW_INPUT_LABEL = "Installation node view";
	private final String SERVICE_INPUT_LABEL = "Installation node service";
	private final String CONTRIBUTION_INPUT_LABEL = "Installation node contribution";
	private String viewInputText, serviceInputText, contributionInputText;

	protected SetClassesNamePage() {
		super("Set Installation Node Classes Page");
		setTitle("Setup Installation Node Classes");
		setDescription("Set names of classes in installation node (Standard names recommended)");
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

		// Label, text field and button for browse to location of project directory
		Label locationLabel = new Label(container, SWT.BORDER | SWT.SINGLE);
		locationLabel.setText("Location");
		locationLabel.setEnabled(false);
		directoryText = new Text(container, SWT.BORDER | SWT.SINGLE);

		directoryText.setText("");
		Button browseBTN = new Button(container, SWT.PUSH);
		browseBTN.setBounds(40, 50, 50, 20);
		browseBTN.setText("Browse");
		browseBTN.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				browseFile();

			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub

			}
		});

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
		this.directoryText.setLayoutData(gd);
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
	 * Method to browse for a file in the user OS
	 * 
	 * @return
	 */
	private String browseFile() {
		DirectoryDialog fd = new DirectoryDialog(container.getShell(), SWT.OPEN);
		fd.setText("Open");
		fd.setFilterPath("");
		// String[] filterExt = { "*.*" };
		// fd.setFilterExtensions(filterExt);
		String selected = fd.open();
		this.directoryText.setText(selected);

		return selected;
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
