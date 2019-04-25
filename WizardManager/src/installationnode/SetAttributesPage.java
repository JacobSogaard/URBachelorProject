package installationnode;

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

	private Text nodeTitle;
	private Label nodeTitleLabel, generatingLabel;
	private Composite container;
	private GridLayout layout;
	private final String NODE_TITLE_MESSAGE = "Node title";

	protected SetAttributesPage() {
		super("Set Installation Node Attributed Page");
		setTitle("Set Installation Node Attributes");
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
		
		
		

		GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		this.nodeTitle.setLayoutData(gd);
		setControl(container);
	
		

	}




	/**
	 * Checks if all input fields are non empty. 
	 * @return
	 */
	private boolean isAllFieldsSet() {
		return !this.nodeTitle.getText().isEmpty();
	}
	
	/**
	 * Get the specified node title from the node title input field
	 * @return node title as a String
	 */
	public String getNodeTitle() {
		return this.nodeTitle.getText();
	}
	
	

}
