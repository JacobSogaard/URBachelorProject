package ${groupId}.${artifactId}.impl;


import com.ur.urcap.api.contribution.ContributionProvider;
import com.ur.urcap.api.contribution.program.swing.SwingProgramNodeView;
import javax.swing.JPanel;
import javax.swing.BoxLayout;

public class ${viewClassName} implements SwingProgramNodeView<${contributionClassName}> {

	public ${viewClassName}() {

	}

	@Override
	public void buildUI(JPanel panel, ContributionProvider<${contributionClassName}> provider) {
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

	}


}