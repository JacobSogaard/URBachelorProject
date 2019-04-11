package ${groupId}.${artifactId};

import java.util.Locale;

import com.ur.urcap.api.contribution.ViewAPIProvider;
import com.ur.urcap.api.contribution.program.ContributionConfiguration;
import com.ur.urcap.api.contribution.program.CreationContext;
import com.ur.urcap.api.contribution.program.ProgramAPIProvider;
import com.ur.urcap.api.contribution.program.swing.SwingProgramNodeService;
import com.ur.urcap.api.domain.SystemAPI;
import com.ur.urcap.api.domain.data.DataModel;


public class ${serviceClassName} implements SwingProgramNodeService<testProgramNodeContribution, testProgramNodeView> {

	@Override
	public String getId() {

		return "${nodeId}";
	}

	@Override
	public void configureContribution(ContributionConfiguration configuration) {

		configuration.setChildrenAllowed(${isChildrenAllowed});
	
	}

	@Override
	public String getTitle(Locale locale) {
	
		return "${nodeTitle}";
	}

	@Override
	public testProgramNodeView createView(ViewAPIProvider apiProvider) {
		return new testProgramNodeView();

	}

	@Override
	public testProgramNodeContribution createNode(ProgramAPIProvider apiProvider, testProgramNodeView view,
			DataModel model, CreationContext context) {

		return new testProgramNodeContribution(apiProvider, view, model);
	}

}