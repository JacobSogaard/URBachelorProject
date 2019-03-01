package urcaplugin.wizard;


import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.jface.wizard.WizardPage;
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
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

public class NewURCapPage extends WizardPage{
	private Text groupIdText, artifactIdText, directoryText, versionText;
	private Composite container, browseContainer;
	private int number = 0;
	private String workspacePath;
	private final String[] API_VERSIONS = {"1.0", "1.1", "1.2", "1.3", "1.4", "1.5"};

	public NewURCapPage() {
		super("First Page");
		setTitle("New URCap project");
		setDescription("Define name and location of URCap project");
	}

	@Override
	public void createControl(Composite parent) {
		this.workspacePath = ResourcesPlugin.getWorkspace().getRoot().getLocation().toString();
		container = new Composite(parent, SWT.NONE);
		browseContainer = new Composite(parent, SWT.NONE);
		GridLayout layout = new GridLayout();
		container.setLayout(layout);
		layout.numColumns = 3;
		
		//Set label and text field for group id
		Label groupIdLabel = new Label(container, SWT.NONE);
		this.groupIdText = new Text(container, SWT.BORDER | SWT.SINGLE);
		this.createFormWithMessage(groupIdLabel, "Group id", groupIdText, "com.example.mycompany");

		//Label and text field for artifact id
		Label artifactIdLabel = new Label(container, SWT.NONE);
		artifactIdText = new Text(container, SWT.BORDER | SWT.SINGLE);
		this.createFormWithMessage(artifactIdLabel, "Artifact Id", artifactIdText, "Artifact id");
		

		//Label and dropdown for selection of api version
		Label apiVersionLabel = new Label(container, SWT.NONE);
		apiVersionLabel.setText("API version");
		Combo comboDropDownApiVersion = new Combo(container, SWT.DROP_DOWN | SWT.BORDER);
		for (String api : API_VERSIONS) {
			comboDropDownApiVersion.add(api);
		}
		
		this.emptyLabel();
		
		//Label and text field for setting the ur cap version number
		Label versionNumberLabel = new Label(container, SWT.NONE);
		versionNumberLabel.setText("Version number");
		versionText = new Text(container, SWT.BORDER | SWT.SINGLE);
		versionText.setText("1.0.0");
		versionText.addKeyListener(new KeyListener() {

			@Override
			public void keyPressed(KeyEvent e) {
			}

			@Override
			public void keyReleased(KeyEvent e) {
				if (!allFieldsSet()) {
					setPageComplete(true);

				}
			}
		});
		
		this.emptyLabel();
		
		
		//Label, text field and button for browse to location of project directory
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
				// TODO Auto-generated method stub
				
			}
		});
	    
	    //Sets the grid properly so textfilds fill the page
		GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		groupIdText.setLayoutData(gd);
		artifactIdText.setLayoutData(gd);
		comboDropDownApiVersion.setLayoutData(gd);
		directoryText.setLayoutData(gd);
		versionText.setLayoutData(gd);
		// Required to avoid an error in the system
		setControl(container);
		setPageComplete(false);

	}
	
	/**
	 * Method to create textbox with a suggestion message and and label positioned left of the text box
	 * Also adds an empty label at the end, to fit the 3 collumn setup in this wizard. (Might be some better way to do this)
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
				if (allFieldsSet()) {
					setPageComplete(true);

				}
			}

		});
		
		this.emptyLabel();
	}
	
	/**
	 * Empty label used to counter the 3 collumns needed for the browse location part of wizard,
	 * so the part with only 2 columns have their own line
	 */
	private void emptyLabel() {
		Label emptyLabel = new Label(container, SWT.NONE);
		emptyLabel.setText("");
		emptyLabel.setBounds(1,1,1,1);
	}
	
	/**
	 * Method to browse for a file in the user OS
	 * @return
	 */
	private String browseFile() {
		DirectoryDialog fd = new DirectoryDialog(container.getShell(), SWT.OPEN);
        fd.setText("Open");
        fd.setFilterPath("");
        //String[] filterExt = { "*.*" };
        //fd.setFilterExtensions(filterExt);
        String selected = fd.open();
        this.directoryText.setText(selected);
        return selected;
	}
	
	

	public String getText1() {
		return groupIdText.getText();
	}
	
	private boolean allFieldsSet() {
		return this.groupIdText.getText().isEmpty() && this.artifactIdText.getText().isEmpty();
	}

	public IWizardPage getNextPage() {
		Display.getDefault().asyncExec(new Runnable() {
			public void run() {
				// ... do any work that updates the screen ...
			}
		});

		return super.getNextPage();
	}
}
