package deploy.local;

import java.util.Date;

import org.eclipse.osgi.service.datalocation.Location;

import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.dialogs.DialogSettings;
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
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

public class DeployToLocalWizardPage1 extends WizardPage {

	private Label browseLabel;
	private Combo browseText;
	private Button browseBTN;
	private Composite container;
	private GridLayout layout;
	private String browseLabelText = "Path to URSim";
	private DialogSettings dialogSettings;

	protected DeployToLocalWizardPage1() {
		super("Deploy to local wizardpage");
		setTitle("Deploy to local URSim");
		setDescription("Set path for local URSim");
		setPageComplete(false);
		this.dialogSettings = new DialogSettings("vmlocations");
		
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
		this.browseBTN.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				browseFile();

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
	 * @return
	 */
	private void browseFile() {
		DirectoryDialog fd = new DirectoryDialog(container.getShell(), SWT.OPEN);
		fd.setText("Open");
		fd.setFilterPath("");
		// String[] filterExt = { "*.*" };
		// fd.setFilterExtensions(filterExt);
		
		try {
			this.browseText.setText(fd.open());
		} catch (IllegalArgumentException ex) {
			return;
		}
	}
	
	public String getURSimPath() {
		return this.browseText.getText();
	}
	
	public void storeVMLocation() {
		
		//dialogSettings.put("vmlocation", this.dialog);
	}

	private boolean isAllFieldsSet() {
		return true;
	}

}
