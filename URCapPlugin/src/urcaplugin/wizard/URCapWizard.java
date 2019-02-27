package urcaplugin.wizard;

import org.eclipse.jface.wizard.Wizard;

/**
 * Class which handles pages in wizard.
 * Each page should have it's own class and instances of the should be created here. 
 * @author jacob
 *
 */
public class URCapWizard extends Wizard {

	protected NewURCapPage one;
	//protected MyPageTwo two;

	public URCapWizard() {
		super();
		setNeedsProgressMonitor(true);
	}

	@Override
	public void addPages() {
		one = new NewURCapPage();
		//two = new MyPageTwo();
		addPage(one);
		//addPage(two);
	}

	@Override
	public boolean performFinish() {
		// Print the result to the console
		//System.out.println(one.getText1());
		//System.out.println(two.getText1());

		return true;
	}
}