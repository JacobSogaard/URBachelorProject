package programnode;

import org.eclipse.swt.SWT;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import wizardmanager.NodeWizard;

/**
 * Wizard page to set the class names for service, view and contribution class, on a program node. 
 * @author jacob
 */
public class SetClassesNamePage extends NodeWizard{

	
	//TODO reconsider variable names here and in other wizard classes, tends to be hard to navigate
	private Text viewClassName, serviceClassName, contributionClassName, nodeName;
	private Label viewInputLabel, serviceInputLabel, contributionInputLabel, nodeNameLabel;
	private Composite container;
	private GridLayout layout;
	private final String VIEW_INPUT_LABEL = "Program node view";
	private final String SERVICE_INPUT_LABEL = "Program node service";
	private final String CONTRIBUTION_INPUT_LABEL = "Program node contribution";
	private final String NODE_INPUT_LABEL = "Node name";
	private String viewInputText, serviceInputText, contributionInputText, artifactId, nodeNameText;
	private boolean canFlip;
	
	
	protected SetClassesNamePage(String artifactId) {
		super("Set Program Node Classes Page");
		setTitle("Setup Program Node Classes");
		setDescription("Set names of classes in program node (Standard names recommended)");
		setPageComplete(true);
		
		this.artifactId = artifactId;
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
		
		this.nodeNameLabel = new Label(container, SWT.NONE);
		this.nodeName = new Text(container, SWT.BORDER | SWT.SINGLE);
		createInputForm(this.nodeName, this.nodeNameText, this.nodeNameLabel, NODE_INPUT_LABEL, false);
		this.nodeName.addKeyListener(new KeyListener() {
			
			@Override
			public void keyReleased(KeyEvent e) {
				viewClassName.setText(nodeName.getText() + "ProgramNodeView");
				serviceClassName.setText(nodeName.getText() + "ProgramNodeService");
				contributionClassName.setText(nodeName.getText() + "ProgramNodeContribution");
				canFlip = verifyInput(nodeName.getText()); 
				getContainer().updateButtons();
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				
			}
		});
		
		//Set view input and label
		this.viewInputLabel = new Label(container, SWT.NONE);
		this.viewClassName = new Text(container, SWT.BORDER | SWT.SINGLE);
		createInputForm(this.viewClassName, this.viewInputText, this.viewInputLabel, VIEW_INPUT_LABEL, false);
		
		//Set service input and label
		this.serviceInputLabel = new Label(container, SWT.NONE);
		this.serviceClassName = new Text(container, SWT.BORDER | SWT.SINGLE);
		createInputForm(this.serviceClassName, this.serviceInputText, this.serviceInputLabel, SERVICE_INPUT_LABEL, false);
	
		//Set contribution input and label
		this.contributionInputLabel = new Label(container, SWT.NONE);
		this.contributionClassName = new Text(container, SWT.BORDER | SWT.SINGLE);
		createInputForm(this.contributionClassName, this.contributionInputText, this.contributionInputLabel, CONTRIBUTION_INPUT_LABEL, false);
	
		this.setClassnamesEnabled(false);
		GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		this.setAllLayout(gd);
		setControl(container);
	
		
	}
	
	/**
	 * Sets the layout grid for input fields
	 * @param gd
	 */
	private void setAllLayout(GridData gd) {
		this.nodeName.setLayoutData(gd);
		this.viewClassName.setLayoutData(gd);
		this.serviceClassName.setLayoutData(gd);
		this.contributionClassName.setLayoutData(gd);
	}
	
	
	/**
	 * Method to set the text of the input fields.
	 * Should be refactored to get artifact id from project selected i project explorer. 
	 */
	private void setInputFieldsText() {
		this.nodeNameText = this.artifactId;
		this.viewInputText = this.artifactId + "ProgramNodeView";
		this.serviceInputText = this.artifactId + "ProgramNodeService";
		this.contributionInputText = this.artifactId + "ProgramNodeContribution";
	}
	
	private void setClassnamesEnabled(boolean enabled) {
		this.viewClassName.setEnabled(enabled);
		this.serviceClassName.setEnabled(enabled);
		this.contributionClassName.setEnabled(enabled);
	
	}
	
	/**
	 * Checks to see if the next button should be active. Check if the input fields are empty. 
	 */
	@Override
	public boolean canFlipToNextPage() {		
		 return this.canFlip;
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
	
	@Override
	public void performHelp() 
	{
		
	    Shell shell = new Shell(getShell());
	    shell.setText("Installation ");
	    shell.setLayout(new FillLayout());
	    Browser browser = new Browser(shell, SWT.NONE);
	    browser.setUrl("https://plus.universal-robots.com/getting-started/principle-of-program-node-integration/");
	    browser.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
	    shell.open();
	  
	
	}

}










