package wizardmanager;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

/**
 * Superclass for wizard pages for the node installations. Has a method to create the input forms with label and a text field.
 * TODO Consider what else might fit here. Some variables from the classes might be a good idea here. 
 * @author jacob
 *
 */
public abstract class NodeWizard extends WizardPage {

	protected NodeWizard(String pageName) {
		super(pageName);
	}

	/**
	 * Creates and sets the input text fields and their labels.
	 * 
	 * @param text
	 * @param message
	 * @param label
	 * @param labelText
	 */
	public void createInputForm(Text text, String message, Label label, String labelText, boolean isHint) {
		label.setText(labelText);
		if (isHint) {
			text.setMessage(message);
		} else {
			text.setText(message);
		}
	}
	
	/**
	 * Verify first letter is capital
	 * Verify no spaces
	 * Verify not empty
	 * Sets warning messages for the different cases. Change 2 in setMessage() to 3 for an error icon instead of warning
	 * @return - true if input field is not empty, first letter is capitalized and does not contain any whitespaces. False otherwise
	 */
	public boolean verifyInput(String input) {		
		if (input.isEmpty()) {
			setMessage("Node name is empty", 2);
			return false;
		}
		
		if (!input.substring(0, 1).toUpperCase().equals(input.substring(0, 1))) {
			setMessage("Type name is discouraged. By convention, Java type names usually start with an uppercase letter", 2); //This message is taken directly from the eclipse warning for same type of error
			return false;
		}
		
		if (input.contains(" ")) {
			setMessage("Class name cannot contain whitespace", 2);
			return false;	
		}
		
		setMessage(null); //Sets message back to description
		return true;
	}

}
