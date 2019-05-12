package ${groupId}.${artifactId}.impl;

import java.util.Locale;

import com.ur.urcap.api.contribution.ViewAPIProvider;
import com.ur.urcap.api.contribution.program.ContributionConfiguration;
import com.ur.urcap.api.contribution.program.CreationContext;
import com.ur.urcap.api.contribution.program.ProgramAPIProvider;
import com.ur.urcap.api.contribution.program.swing.SwingProgramNodeService;
import com.ur.urcap.api.domain.data.DataModel;


public class ${serviceClassName} implements SwingProgramNodeService<${contributionClassName}, ${viewClassName}> {

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
	public ${viewClassName} createView(ViewAPIProvider apiProvider) {
		return new ${viewClassName}();

	}

	@Override
	public ${contributionClassName} createNode(ProgramAPIProvider apiProvider, ${viewClassName} view,
			DataModel model, CreationContext context) {

		return new ${contributionClassName}(apiProvider, view, model, context);
	}

}