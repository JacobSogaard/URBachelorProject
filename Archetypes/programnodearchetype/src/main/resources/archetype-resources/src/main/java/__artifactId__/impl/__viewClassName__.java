package ${groupId}.${artifactId};

import com.ur.urcap.api.contribution.ContributionProvider;
import com.ur.urcap.api.contribution.program.swing.SwingProgramNodeView;

public class ${viewClassName} implements SwingProgramNodeView<testProgramNodeContribution> {

	public ${viewClassName}() {

	}

	@Override
	public void buildUI(JPanel panel, ContributionProvider<testProgramNodeContribution> provider) {
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

	}


}