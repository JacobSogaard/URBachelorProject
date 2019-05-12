package emptyproject;

import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import modelClasses.MavenModel;
import modelClasses.URCapModel;
import wizardmanager.NodeWizard;

public class SetupURCapPage extends NodeWizard {
	private Text groupIdText, artifactIdText, directoryText, versionText;
	private Composite container;
	private String workspacePath, groupIdToolTip, artifactIdToolTip, apiVersionToolTip, versionToolTip;
	private final String[] API_VERSIONS = { "1.0.0", "1.1.0", "1.2.0", "1.3.0", "1.4.0", "1.5.0", "1.6.0"};
	private Combo comboDropDownApiVersion;
	private MavenModel mavenModel;
	private boolean isArtifactIdValid;
	
	private void createMavenModel() {
		this.mavenModel = new URCapModel();
		this.mavenModel.setArchetypeVersionAPI(this.comboDropDownApiVersion.getText());
		this.mavenModel.setArchetypeVersion(this.comboDropDownApiVersion.getText());
		this.mavenModel.setProjectGroupId(this.groupIdText.getText());
		this.mavenModel.setProjectArtifactId(this.artifactIdText.getText());
		this.mavenModel.setProjectPath(this.directoryText.getText());
		this.mavenModel.setProjectVersion(this.versionText.getText());
	}
	
	/**
	 * Returns the project model which the new project should be generated from
	 * 
	 * @return
	 */
	public MavenModel getProjectModel() {
		this.createMavenModel();
		return this.mavenModel;
	}

	/**
	 * Creates page to set groupid, artifactid, api and urcap version and the
	 * directory to have urcap project in. Constructor only sets wizard page title
	 * and description.
	 */
	public SetupURCapPage() {
		super("First Page");
		setTitle("New URCap project");
		setDescription("Define name and location of URCap project");
		this.setGroupIdToolTip();
		this.setArtifactIdToolTip();
		this.setApiVersionToolTip();
		this.setVersionToolTip();
	}

	@Override
	public void createControl(Composite parent) {
		this.workspacePath = ResourcesPlugin.getWorkspace().getRoot().getLocation().toString();
		container = new Composite(parent, SWT.NONE);
		GridLayout layout = new GridLayout();
		container.setLayout(layout);
		layout.numColumns = 3;

		// Set label and text field for group id
		Label groupIdLabel = new Label(container, SWT.NONE);
		groupIdLabel.setToolTipText(this.groupIdToolTip);
		this.groupIdText = new Text(container, SWT.BORDER | SWT.SINGLE);
		this.createFormWithMessage(groupIdLabel, "Group id", groupIdText, "com.example.mycompany");

		// Label and text field for artifact id
		Label artifactIdLabel = new Label(container, SWT.NONE);
		artifactIdLabel.setToolTipText(this.artifactIdToolTip);
		artifactIdText = new Text(container, SWT.BORDER | SWT.SINGLE);
		this.createFormWithMessage(artifactIdLabel, "Artifact Id", artifactIdText, "Artifact id");
		artifactIdText.addKeyListener(new KeyListener() {
			
			@Override
			public void keyReleased(KeyEvent e) {
				isArtifactIdValid = verifyInput(artifactIdText.getText());
				setPageComplete(isAllFieldsSet());
				
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
		});

		// Label and dropdown for selection of api version
		Label apiVersionLabel = new Label(container, SWT.NONE);
		apiVersionLabel.setText("API version");
		comboDropDownApiVersion = new Combo(container, SWT.DROP_DOWN | SWT.BORDER);
		for (String api : API_VERSIONS) {
			comboDropDownApiVersion.add(api);
		}
		comboDropDownApiVersion.select(comboDropDownApiVersion.getItemCount() - 2);
		apiVersionLabel.setToolTipText(this.apiVersionToolTip);

		this.emptyLabel();

		// Label and text field for setting the ur cap version number
		Label versionNumberLabel = new Label(container, SWT.NONE);
		versionNumberLabel.setToolTipText(this.versionToolTip);
		versionNumberLabel.setText("Version number");
		versionText = new Text(container, SWT.BORDER | SWT.SINGLE);
		versionText.setText("1.0");

		versionText.addKeyListener(new KeyListener() {

			@Override
			public void keyPressed(KeyEvent e) {
			}

			@Override
			public void keyReleased(KeyEvent e) {
				if (!isAllFieldsSet()) {
					setPageComplete(true);

				}
			}
		});

		this.emptyLabel();

		// Label, text field and button for browse to location of project directory
		Label locationLabel = new Label(container, SWT.BORDER | SWT.SINGLE);
		locationLabel.setText("Location");
		locationLabel.setEnabled(false);
		directoryText = new Text(container, SWT.BORDER | SWT.SINGLE);

		directoryText.setText(this.workspacePath);
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

			}
		});

		
		setGridData();
		// Required to avoid an error in the system
		setControl(container);
		setPageComplete(false);

	}

	/**
	 * Sets the grid properly so text fields fill the page.
	 */
	private void setGridData() {
		GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		groupIdText.setLayoutData(gd);
		artifactIdText.setLayoutData(gd);
		comboDropDownApiVersion.setLayoutData(gd);
		directoryText.setLayoutData(gd);
		versionText.setLayoutData(gd);
	}

	/**
	 * Method to create textbox with a suggestion message and and label positioned
	 * left of the text box Also adds an empty label at the end, to fit the 3
	 * collumn setup in this wizard. (Might be some better way to do this)
	 * 
	 * @param label
	 * @param labelText
	 * @param text
	 * @param message
	 */
	private void createFormWithMessage(Label label, String labelText, Text text, String message) {
		label.setText(labelText);
		text.setMessage(message);
		text.addKeyListener(new KeyListener() {

			@Override
			public void keyPressed(KeyEvent e) {
			}

			@Override
			public void keyReleased(KeyEvent e) {
				setPageComplete(isAllFieldsSet());

			}

		});

		this.emptyLabel();
	}

	/**
	 * Empty label used to counter the 3 columns needed for the browse location
	 * part of wizard, so the part with only 2 columns have their own line
	 */
	private void emptyLabel() {
		Label emptyLabel = new Label(container, SWT.NONE);
		emptyLabel.setText("");
		emptyLabel.setBounds(1, 1, 1, 1);
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
		String selected = fd.open();
		this.directoryText.setText(selected);

		return selected;
	}

	public String getArtifactId() {
		return this.artifactIdText.getText();
	}
	
	private boolean isAllFieldsSet() {
		return !this.groupIdText.getText().isEmpty() && isArtifactIdValid;
	}

	
	private void setGroupIdToolTip() {
		this.groupIdToolTip = "Uniquely identifies your project across all projects. A group ID should follow Java's package name rules. This means it starts with a reversed domain name you control. For example: com.mycompany.myurcap";
	}
	
	private void setArtifactIdToolTip() {
		this.artifactIdToolTip = "Unique name of this URCap.";
	}
	
	private void setApiVersionToolTip() {
		this.apiVersionToolTip = "UR API version number. Latest is recommended";
	}
	
	private void setVersionToolTip() {
		this.versionToolTip = "Version of URCap. The version is also defined in the POM file.";
	}
	

	public IWizardPage getNextPage() {
		return super.getNextPage();
	}

}
