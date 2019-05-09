package wizardmanager;

import org.eclipse.jface.wizard.Wizard;

public interface IWizardFactory {

	Wizard getWizard(String wizardType, String projectArtifactId, String projectPath, String groupId);
}
