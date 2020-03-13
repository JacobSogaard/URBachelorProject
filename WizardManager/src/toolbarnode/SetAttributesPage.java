package toolbarnode;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;

public class SetAttributesPage extends WizardPage {

	private Label browseLabel;
	private Combo browseText;
	private Button browseBTN;
	private Composite container;
	private GridLayout layout;
	private String browseLabelText = "Toolbar Icon";

	protected SetAttributesPage() {
		super("Set attributes page");
		setTitle("Set attributes for toolbar");
		setDescription("");
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
		this.browseBTN.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				boolean canContinue = browseFile();
				if (canContinue) {
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
	 * @return
	 */
	private boolean browseFile() {
		boolean foundFileName = false;
		FileDialog fd = new FileDialog(container.getShell(), SWT.OPEN);
		fd.setText("Open");
		String[] filterExt = { "*.png", "*.tif", "*.jpg", "*.gif", "*.jpeg", };
		fd.setFilterExtensions(filterExt);

		try {
			fd.open();
			if (fd.getFileName() != "") {
				this.browseText.setText("/" + fd.getFileName());
				foundFileName = true;
			} else {
				this.browseText.setText("Image file missing!");
				foundFileName = false;
			}
		} catch (IllegalArgumentException ex) {

		}

		return foundFileName;
	}

	public String getIconPath() {
		return this.browseText.getText();
	}

	private boolean isAllFieldsSet() {
		return true;
	}

}
