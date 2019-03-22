package programnodegenerator.wizard;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

public class SetClassesNamePage extends WizardPage{

	private Text viewClassName, serviceClassName, contributionClassName;
	private Label viewInputLabel, serviceInputLabel, contributionInputLabel;
	private Composite container;
	private GridLayout layout;
	private final String VIEW_INPUT_LABEL = "Program node view";
	private final String SERVICE_INPUT_LABEL = "Service node view";
	private final String CONTRIBUTION_INPUT_LABEL = "Contribution node view";
	private String viewInputText, serviceInputText, contributionInputText;
	
	
	protected SetClassesNamePage() {
		super("Set Program Node Classes Page");
		setTitle("Setup Program Node Classes");
		setDescription("Set names of classes in program node (Standard names recommended)");
	}

	@Override
	public void createControl(Composite parent) {
		// TODO Auto-generated method stub
		this.container = new Composite(parent, SWT.NONE);
		this.layout = new GridLayout();
		this.container.setLayout(this.layout);
		this.layout.numColumns = 1;
		
		this.setInputFieldsText();
		
		//Set view input and label
		this.viewInputLabel = new Label(container, SWT.NONE);
		this.viewClassName = new Text(container, SWT.BORDER | SWT.SINGLE);
		this.createInputForm(this.viewClassName, this.viewInputText, this.viewInputLabel, VIEW_INPUT_LABEL);
		
		//Set service input and label
		this.serviceInputLabel = new Label(container, SWT.NONE);
		this.serviceClassName = new Text(container, SWT.BORDER | SWT.SINGLE);
		this.createInputForm(this.serviceClassName, this.serviceInputText, this.serviceInputLabel, SERVICE_INPUT_LABEL);
	
		//Set contribution input and label
		this.contributionInputLabel = new Label(container, SWT.NONE);
		this.contributionClassName = new Text(container, SWT.BORDER | SWT.SINGLE);
		this.createInputForm(this.contributionClassName, this.contributionInputText, this.contributionInputLabel, CONTRIBUTION_INPUT_LABEL);
	
		GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		this.setAllLayout(gd);
		setControl(container);
		setPageComplete(false);
		
	}
	
	private void setAllLayout(GridData gd) {
		this.viewClassName.setLayoutData(gd);
		this.serviceClassName.setLayoutData(gd);
		this.contributionClassName.setLayoutData(gd);
	}
	
	private void createInputForm(Text text, String message, Label label, String labelText) {
		
		
		
		label.setText(labelText);
		text.setText(message);
		text.addKeyListener(new KeyListener() {
	
			@Override
			public void keyPressed(KeyEvent e) {
			}
	
			@Override
			public void keyReleased(KeyEvent e) {
				if (isAllFieldsSet()) {
					setPageComplete(true);
	
				}
			}
	
		});
	}
	
	/**
	 * Method to set the text of the input fields.
	 * Should be refactored to get artifact id from project selected i project explorer. 
	 */
	private void setInputFieldsText() {
		this.viewInputText = "VIEW PLACEHOLDER TEXT";
		this.serviceInputText = "SERVICE PLACEHOLDER TEXT";
		this.contributionInputText = "CONTRIBUTION PLACEHOLDER TEXT";
	}
	
	private boolean isAllFieldsSet() {
		return true;
	}
}










