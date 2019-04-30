package deploy.vm;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

public class DeployToVMWizardPage1 extends WizardPage {

	private Label hostLabel, usernameLabel, passwordLabel;
	private Text hostText, usernameText, passwordText;
	private Button browseBTN;
	private Composite container;
	private GridLayout layout;
	private final String HOST_TEXT = "", USERNAME_TEXT = "root", PASSWORD_TEXT = "easybot";
	private String hostLabelText = "IP address of virtual machine";
	private String usernameLabelText = "Username";
	private String passwordLabelText = "Password";

	protected DeployToVMWizardPage1() {
		super("Deploy to URSim on VM");
		setTitle("Deploy to VM");
		setDescription("Set information for deploying to URSim on VM");
		setPageComplete(false);
	}

	@Override
	public void createControl(Composite parent) {
		this.container = new Composite(parent, SWT.NONE);
		this.layout = new GridLayout();
		this.container.setLayout(this.layout);
		this.layout.numColumns = 1;

		// Sets host input
		this.hostLabel = new Label(container, SWT.NONE);
		this.hostLabel.setText(this.hostLabelText);
		this.hostText = new Text(container, SWT.BORDER | SWT.SINGLE);
		this.hostText.setText(this.HOST_TEXT);
		this.hostText.addKeyListener(new KeyListener() {

			@Override
			public void keyReleased(KeyEvent e) {
				setPageComplete(isAllFieldsSet());
			}

			@Override
			public void keyPressed(KeyEvent e) {

			}
		});

		// Sets username input
		this.usernameLabel = new Label(container, SWT.NONE);
		this.usernameLabel.setText(this.usernameLabelText);
		this.usernameText = new Text(container, SWT.BORDER | SWT.SINGLE);
		this.usernameText.setText(this.USERNAME_TEXT);
		this.usernameText.addKeyListener(new KeyListener() {

			@Override
			public void keyReleased(KeyEvent e) {
				setPageComplete(isAllFieldsSet());
			}

			@Override
			public void keyPressed(KeyEvent e) {

			}
		});
		
		this.passwordLabel = new Label(container, SWT.NONE);
		this.passwordLabel.setText(this.passwordLabelText);
		this.passwordText = new Text(container, SWT.BORDER | SWT.SINGLE);
		this.passwordText.setText(this.PASSWORD_TEXT);
		this.passwordText.addKeyListener(new KeyListener() {

			@Override
			public void keyReleased(KeyEvent e) {
				setPageComplete(isAllFieldsSet());
			}

			@Override
			public void keyPressed(KeyEvent e) {

			}
		});



		GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		this.hostText.setLayoutData(gd);
		this.usernameText.setLayoutData(gd);
		this.passwordText.setLayoutData(gd);
		setControl(container);

	}
	
	private void browseFile() {
		DirectoryDialog fd = new DirectoryDialog(container.getShell(), SWT.OPEN);
		fd.setText("Open");
		fd.setFilterPath("");
		// String[] filterExt = { "*.*" };
		// fd.setFilterExtensions(filterExt);
		
		try {
			this.passwordText.setText(fd.open());
		} catch (IllegalArgumentException ex) {
			return;
		}
	}
	
	protected String getHost() {
		return this.hostText.getText();
	}


	private boolean isAllFieldsSet() {
		return true;
	}

}
