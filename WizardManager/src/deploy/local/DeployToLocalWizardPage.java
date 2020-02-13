package deploy.local;

import java.io.File;
import java.nio.file.InvalidPathException;
import java.nio.file.Paths;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.DirectoryDialog;

import org.eclipse.swt.widgets.Label;

import emptyproject.PomFileReader;

public class DeployToLocalWizardPage extends WizardPage {

	private Label browseLabel;
	private Combo browseText;
	private Button browseBTN;
	private Composite container;
	private GridLayout layout;
	private String browseLabelText = "Path to URSim";
	private String projectPath;

	private PomFileReader pomReader = new PomFileReader();

	protected DeployToLocalWizardPage() {
		super("Deploy to local wizardpage");
		setTitle("Deploy to local URSim");
		setDescription("Set path for local URSim");
		setPageComplete(false);

	}

	@Override
	public void createControl(Composite parent) {
		this.container = new Composite(parent, SWT.NONE);
		this.layout = new GridLayout();
		this.container.setLayout(this.layout);
		this.layout.numColumns = 3;

		// Label, text field and button for browse to location of URSim location
		this.browseLabel = new Label(container, SWT.BORDER | SWT.SINGLE);
		this.browseLabel.setText(this.browseLabelText);
		this.browseLabel.setEnabled(false);

		this.browseText = new Combo(container, SWT.DROP_DOWN | SWT.BORDER);
		this.browseText.setText("");

		this.browseBTN = new Button(container, SWT.PUSH);
		this.browseBTN.setBounds(40, 50, 50, 20);
		

		this.browseBTN.setText("Browse");
		
		if (getUrsimPathFromPom()) {
			setPageComplete(isAllFieldsSet());
		}
		
		this.browseBTN.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				if (browseFile()) {
					setPageComplete(isAllFieldsSet());
				}
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {

			}
		});

		GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		this.browseText.setLayoutData(gd);
		setControl(container);

	}

	/**
	 * Method to browse for a file in the user OS
	 * 
	 * @return a boolean whether the path exists or not.
	 */
	private boolean browseFile() {
		DirectoryDialog fd = new DirectoryDialog(container.getShell(), SWT.OPEN);
		fd.setText("Open");
		fd.setFilterPath("");

		Boolean pathExists = false;

		try {
			this.browseText.setText("");
			
			String ursimPath = fd.open();
			
			if (isPathValid(ursimPath)) {
				this.browseText.setText(ursimPath);
				pathExists = true;
			} else {
				this.browseText.setText("Invalid Path. Please Check!");
				pathExists = false;
			}

		} catch (IllegalArgumentException ex) {
			ex.printStackTrace();
		}

		return pathExists;

	}

	protected String getURSimPath() {
		String windowPath = this.browseText.getText();
		windowPath.replace("\\", "\\\\");
		return windowPath;
	}

	private boolean isAllFieldsSet() {
		return true;
	}

	public void setProjectPathDeploy(String path) {
		this.projectPath = path;
	}

	/**
	 * Validates if the URSIM path set by the user is a valid path.
	 * 
	 * @param path of the ursim
	 * @return boolean
	 */
	private boolean isPathValid(String path) {
		File f = new File(path);
		if (!f.exists()) {
			return false;
		} else {
			return true;
		}

	}
	
	private boolean getUrsimPathFromPom() {
		Boolean pathExists = false;
		try {

			if (isPathValid(pomReader.getURsimPath(this.projectPath))) {
				this.browseText.setText(pomReader.getURsimPath(this.projectPath));
				pathExists = true;
			} else {
				this.browseText.setText("Invalid Path. Please Check!");
				pathExists = false;
			}

		} catch (IllegalArgumentException ex) {
			ex.printStackTrace();
		}

		return pathExists;
	}
}
