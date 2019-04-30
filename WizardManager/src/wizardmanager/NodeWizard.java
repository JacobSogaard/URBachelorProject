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

}
