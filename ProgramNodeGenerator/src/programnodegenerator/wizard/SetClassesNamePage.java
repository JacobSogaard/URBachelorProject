package programnodegenerator.wizard;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;

public class SetClassesNamePage extends WizardPage{

	private Text viewClassName, serviceClassName, contributionClassName;
	private Label viewInputLabel, serviceInputLabel, contributionInputLabel;
	private Composite container;
	private GridLayout layout;
	private final String VIEW_INPUT_LABEL = "Program node view";
	private final String SERVICE_INPUT_LABEL = "Program node service";
	private final String CONTRIBUTION_INPUT_LABEL = "Program node contribution";
	private String viewInputText, serviceInputText, contributionInputText;
	
	
	protected SetClassesNamePage() {
		super("Set Program Node Classes Page");
		setTitle("Setup Program Node Classes");
		setDescription("Set names of classes in program node (Standard names recommended)");
		setPageComplete(true);
	}

	@Override
	public void createControl(Composite parent) {
		this.container = new Composite(parent, SWT.NONE);
		this.layout = new GridLayout();
		this.container.setLayout(this.layout);
		this.layout.numColumns = 1;
		
		this.setInputFieldsText();
		
		//Set view input and label
		this.viewInputLabel = new Label(container, SWT.NONE);
		this.viewClassName = new Text(container, SWT.BORDER | SWT.SINGLE);
		
		IWorkbenchWindow window = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
		ISelection selection = window.getSelectionService().getSelection("org.eclipse.jdt.ui.PackageExplorer");
		
		this.createInputForm(this.viewClassName, selection.toString(), this.viewInputLabel, VIEW_INPUT_LABEL);
		
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
	
		
	}
	
	private void setAllLayout(GridData gd) {
		this.viewClassName.setLayoutData(gd);
		this.serviceClassName.setLayoutData(gd);
		this.contributionClassName.setLayoutData(gd);
	}
	
	private void createInputForm(Text text, String message, Label label, String labelText) {
		
		
		
		label.setText(labelText);
		text.setText(message);
		
	}
	
	/**
	 * Method to set the text of the input fields.
	 * Should be refactored to get artifact id from project selected i project explorer. 
	 */
	private void setInputFieldsText() {
		this.viewInputText = "ProgramNodeView";
		this.serviceInputText = "ProgramNodeService";
		this.contributionInputText = "ProgramNodeContribution";
	}
	
	private boolean isAllFieldsSet() {
		return true;
	}
	
	@Override
	public boolean canFlipToNextPage() {		
		 return (!this.viewClassName.getText().isEmpty() && !this.serviceClassName.getText().isEmpty()
				&& !this.contributionClassName.getText().isEmpty());
	}
	
	/**
	 * Get the input from view class name field
	 * @return view class name as String
	 */
	public String getViewClassname() {
		return this.viewClassName.getText();
	}
	
	/**
	 * Get the input from service class name field
	 * @return view class name as String
	 */
	public String getServiceClassname() {
		return this.serviceClassName.getText();
	}
	
	/**
	 * Get the input from contribution class name field
	 * @return view class name as String
	 */
	public String getContributionClassname() {
		return this.contributionClassName.getText();
	}

}










