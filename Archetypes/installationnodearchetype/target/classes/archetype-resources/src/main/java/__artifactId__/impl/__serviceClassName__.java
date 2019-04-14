package ${groupId}.${artifactId};

import java.util.Locale;

import com.ur.urcap.api.contribution.ViewAPIProvider;
import com.ur.urcap.api.contribution.installation.ContributionConfiguration;
import com.ur.urcap.api.contribution.installation.CreationContext;
import com.ur.urcap.api.contribution.installation.InstallationAPIProvider;
import com.ur.urcap.api.contribution.installation.swing.SwingInstallationNodeService;
import com.ur.urcap.api.domain.data.DataModel;

public class ${serviceClassName}
		implements SwingInstallationNodeService<${contributionClassName}, MyInstallationNodeView> {

	public ${serviceClassName}() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void configureContribution(ContributionConfiguration configuration) {
		// TODO Auto-generated method stub

	}

	@Override
	public String getTitle(Locale locale) {
		// TODO Auto-generated method stub
		return "${nodeTitle}";
	}

	@Override
	public MyInstallationNodeView createView(ViewAPIProvider apiProvider) {
		// TODO Auto-generated method stub
		return new ${viewClassName}(apiProvider);
	}

	@Override
	public MyInstallationNodeContribution createInstallationNode(InstallationAPIProvider apiProvider,
			${viewClassName} view, DataModel model, CreationContext context) {
		// TODO Auto-generated method stub
		return new ${contributionClassName}(apiProvider, view, model, context);
	}

}