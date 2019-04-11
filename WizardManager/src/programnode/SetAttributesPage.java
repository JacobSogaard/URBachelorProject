package programnode;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.*;;

/**
 * Wizard page which sets information about the program node, excepts class names. 
 * @author jacob
 *
 */
public class SetAttributesPage extends WizardPage {

	private Text nodeId, nodeTitle;
	private Label nodeIdLabel, nodeTitleLabel, generatingLabel;
	private Button setChildrenAllowedBTN;
	private Composite container;
	private GridLayout layout;
	private final String NODE_ID_MESSAGE = "Node id";
	private final String NODE_TITLE_MESSAGE = "Node title";
	private final String SET_CHILDREN_ALLOWED_TEXT = "Allow child nodes";

	protected SetAttributesPage() {
		super("Set Program Node Attributed Page");
		setTitle("Set Program Node Attributes");
		setDescription("DESCRIPTION");
		setPageComplete(false);
		
		

	}
	

	/**
	 * Creates the look of the page. Sets all labels and input fields
	 */
	@Override
	public void createControl(Composite parent) {
		this.container = new Composite(parent, SWT.NONE);
		this.layout = new GridLayout();
		this.container.setLayout(this.layout);
		this.layout.numColumns = 1;
		
		// Sets id input
		this.nodeIdLabel = new Label(container, SWT.NONE);
		this.nodeIdLabel.setText("Node id");
		this.nodeId = new Text(container, SWT.BORDER | SWT.SINGLE);
		this.nodeId.setMessage(NODE_ID_MESSAGE);
		this.nodeId.addKeyListener(new KeyListener() {
			
			@Override
			public void keyReleased(KeyEvent e) {
				setPageComplete(isAllFieldsSet());
				
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				
				
			}
		});
		
		// Sets title input
		this.nodeTitleLabel = new Label(container, SWT.NONE);
		this.nodeTitleLabel.setText("Node title");
		this.nodeTitle = new Text(container, SWT.BORDER | SWT.SINGLE);
		this.nodeTitle.setMessage(NODE_TITLE_MESSAGE);
		this.nodeTitle.addKeyListener(new KeyListener() {
			
			@Override
			public void keyReleased(KeyEvent e) {
				setPageComplete(isAllFieldsSet());
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				
				
			}
		});
		
		
		
		//Sets check box for is children allowed.
		this.setChildrenAllowedBTN = new Button(container, SWT.CHECK);
		this.setChildrenAllowedBTN.setText(SET_CHILDREN_ALLOWED_TEXT);
		
		GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		this.nodeId.setLayoutData(gd);
		this.nodeTitle.setLayoutData(gd);
		
		
		GridData checkBoxGrid = new GridData(GridData.FILL_HORIZONTAL);
		checkBoxGrid.verticalIndent = 20;
		this.setChildrenAllowedBTN.setLayoutData(checkBoxGrid);
		
		
		setControl(container);
	
		

	}




	/**
	 * Checks if all input fields are non empty. 
	 * @return
	 */
	private boolean isAllFieldsSet() {
		return !this.nodeId.getText().isEmpty() && !this.nodeTitle.getText().isEmpty();
	}
	
	/**
	 * Get the specified id from the id input field
	 * @return node id as String
	 */
	public String getNodeId() {
		return this.nodeId.getText();
	}
	
	/**
	 * Get the specified node title from the node title input field
	 * @return node title as a String
	 */
	public String getNodeTitle() {
		return this.nodeTitle.getText();
	}
	
	public void setGeneratingLabel() {
		
		System.out.println("Generating...");
		this.generatingLabel.setText("Generating...");
	}
	
	

}
