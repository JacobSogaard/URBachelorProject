package ${groupId}.${artifactId};

import javax.swing.Icon;

import com.ur.urcap.api.contribution.toolbar.ToolbarConfiguration;
import com.ur.urcap.api.contribution.toolbar.ToolbarContext;
import com.ur.urcap.api.contribution.toolbar.swing.SwingToolbarContribution;
import com.ur.urcap.api.contribution.toolbar.swing.SwingToolbarService;

public class ${serviceClassname} implements SwingToolbarService {

	@Override
	public Icon getIcon() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void configureContribution(ToolbarConfiguration configuration) {
		// TODO Auto-generated method stub

	}

	@Override
	public SwingToolbarContribution createToolbar(ToolbarContext context) {
		// TODO Auto-generated method stub
		return new ${contributionClassname}(context);
	}

}
