package ${groupId}.${artifactId};

import com.ur.urcap.api.contribution.InstallationNodeContribution;
import com.ur.urcap.api.contribution.installation.CreationContext;
import com.ur.urcap.api.contribution.installation.InstallationAPIProvider;
import com.ur.urcap.api.domain.data.DataModel;
import com.ur.urcap.api.domain.script.ScriptWriter;

public class ${contributionClassName} implements InstallationNodeContribution {

	public ${contributionClassName}(InstallationAPIProvider apiProvider, MyInstallationNodeView view,
			DataModel model, CreationContext context) {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void openView() {
		// TODO Auto-generated method stub

	}

	@Override
	public void closeView() {
		// TODO Auto-generated method stub

	}

	@Override
	public void generateScript(ScriptWriter writer) {
		// TODO Auto-generated method stub

	}

}