package urcaplugin.wizard;

import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.FocusListener;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class NewURCapPage extends WizardPage{
	private Text groupIdText, artifactIdText, directoryText;
	private Composite container, browseContainer;
	private int number = 0;
	private String workspacePath;

	public NewURCapPage() {
		super("First Page");
		setTitle("New URCap project");
		setDescription("Define name and location of URCap project");
	}

	@Override
	public void createControl(Composite parent) {
		
		container = new Composite(parent, SWT.NONE);
		browseContainer = new Composite(parent, SWT.NONE);
		GridLayout layout = new GridLayout();
		container.setLayout(layout);
		layout.numColumns = 3;
		
		//Set label and text field for group id
		Label label1 = new Label(container, SWT.NONE);
		label1.setText("Group id");
		groupIdText = new Text(container, SWT.BORDER | SWT.SINGLE);
		groupIdText.setMessage("com.example.yourcompany");
		groupIdText.addKeyListener(new KeyListener() {

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
		

		//Label and text field for artifact id
		Label label2 = new Label(container, SWT.NONE);
		label2.setText("Artifact Id");
		artifactIdText = new Text(container, SWT.BORDER | SWT.SINGLE);
		artifactIdText.setMessage("ArtifactId");
		artifactIdText.addKeyListener(new KeyListener() {

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
		

		//Label and dropdown for selection of api version
		Label label3 = new Label(container, SWT.NONE);
		label3.setText("API version");
		Combo comboDropDownApiVersion = new Combo(container, SWT.DROP_DOWN | SWT.BORDER);
		comboDropDownApiVersion.add("1.1");
		comboDropDownApiVersion.add("1.2");
		comboDropDownApiVersion.add("1.3");
		comboDropDownApiVersion.add("1.4");
		comboDropDownApiVersion.add("1.5");
		
		this.emptyLabel();
		
		
		//Label, text field and button for browse to location of project directory
		Label label4 = new Label(container, SWT.BORDER | SWT.SINGLE);
		label4.setText("Location");
		label4.setEnabled(false);
		directoryText = new Text(container, SWT.BORDER | SWT.SINGLE);
		
		directoryText.setMessage("Choose directory to add project in");
		Button button = new Button(container, SWT.PUSH);
	    button.setBounds(40, 50, 50, 20);
	    button.setText("Browse");
	    button.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent e) {
				browseFile();
				
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
	    
		GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		groupIdText.setLayoutData(gd);
		artifactIdText.setLayoutData(gd);
		comboDropDownApiVersion.setLayoutData(gd);
		directoryText.setLayoutData(gd);
		// Required to avoid an error in the system
		setControl(container);
		setPageComplete(false);

	}
	
	private void emptyLabel() {
		Label emptyLabel = new Label(container, SWT.NONE);
		emptyLabel.setText("");
		emptyLabel.setBounds(1,1,1,1);
	}
	
	private String browseFile() {
		DirectoryDialog fd = new DirectoryDialog(container.getShell(), SWT.OPEN);
        fd.setText("Open");
        fd.setFilterPath("/home");
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
