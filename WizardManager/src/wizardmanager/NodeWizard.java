package wizardmanager;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

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
