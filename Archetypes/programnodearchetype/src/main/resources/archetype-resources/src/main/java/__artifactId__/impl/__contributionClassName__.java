package ${groupId}.${artifactId}.impl;


import com.ur.urcap.api.contribution.ProgramNodeContribution;
import com.ur.urcap.api.contribution.ProgramNodeService;
import com.ur.urcap.api.contribution.program.ProgramAPIProvider;
import com.ur.urcap.api.domain.ProgramAPI;
import com.ur.urcap.api.domain.URCapAPI;
import com.ur.urcap.api.domain.data.DataModel;
import com.ur.urcap.api.domain.script.ScriptWriter;
import com.ur.urcap.api.domain.io.DigitalIO;
import com.ur.urcap.api.domain.program.nodes.contributable.URCapProgramNode;


public class ${contributionClassName} implements ProgramNodeContribution {

	private final ProgramAPI programAPI;
	private final ${viewClassName} view;
	private final DataModel model;


	public ${contributionClassName}(ProgramAPIProvider apiProvider, ${viewClassName} view, DataModel model) {

	
		this.programAPI = apiProvider.getProgramAPI();
		this.view = view;
		this.model = model;

	}

	@Override
	public void openView() {

	}


	@Override
	public void closeView() {
		// TODO Auto-generated method stub

	}

	@Override
	public String getTitle() {
		// TODO Auto-generated method stub
		return "${nodeTitle}";
	}

	@Override
	public boolean isDefined() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public void generateScript(ScriptWriter writer) {

	}

}