package nature;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectNature;
import org.eclipse.core.runtime.CoreException;

public class URCapNature implements IProjectNature {

	public static final String NATURE_ID = "URCapProjectNature.urcapprojectnature";
	private IProject project;

	@Override
	public void configure() throws CoreException {
		// TODO Auto-generated method stub

	}

	@Override
	public void deconfigure() throws CoreException {
		// TODO Auto-generated method stub

	}

	@Override
	public IProject getProject() {

		return this.project;
	}

	@Override
	public void setProject(IProject project) {

		this.project = project;

	}

}
