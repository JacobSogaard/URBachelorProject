package generator;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Locale;

import javax.lang.model.element.Modifier;

import org.eclipse.core.resources.ResourcesPlugin;

import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterizedTypeName;
import com.squareup.javapoet.TypeSpec;

import programnodegenerator.modelClasses.ProgramNodeModel;

/**
 * Class to generate files a program node using the JavaPoet API
 * Class uses a ProgramNodeModel as the model for generating the classes.
 * @author jacob
 *
 */
public class ProgramNodeGenerator {

	private ProgramNodeModel model;
	private String path;
	private File filePath;
	
	public ProgramNodeGenerator(ProgramNodeModel model) {
		this.path = ResourcesPlugin.getWorkspace().getRoot().getLocation().toString(); 
		this.filePath = new File(path);
		System.out.println(filePath.getPath());
		this.model = model;
		this.serviceClassGenerator();
		
	}
	
	
	private void serviceClassGenerator() {
		MethodSpec getId = MethodSpec.methodBuilder("getId")
				.addAnnotation(Override.class)
				.addModifiers(Modifier.PUBLIC)
				.returns(String.class)
				.addStatement("return $S", this.model.getNodeId())
				.build();
		
		
		String configureContributionCode = "configuration.setChildrenAllowed(" + this.model.isSetChildrenAllowed() + ")";
		ClassName contributionConfiguration = ClassName.get("com.ur.urcap.api.contribution.program", "ContributionConfiguration");
		MethodSpec configureContribution = MethodSpec.methodBuilder("configureContribution")
				.addModifiers(Modifier.PUBLIC)
				.addAnnotation(Override.class)
				.addParameter(contributionConfiguration, "configuration")
				.returns(void.class)
				.addStatement(configureContributionCode)
				.build();
		
		MethodSpec getTitle = MethodSpec.methodBuilder("getTitle")
				.addModifiers(Modifier.PUBLIC)
				.addAnnotation(Override.class)
				.addParameter(Locale.class, "locale")
				.returns(String.class)
				.addStatement("return $S", this.model.getNodeTitle())
				.build();
		
		ClassName viewAPIProvider = ClassName.get("com.ur.urcap.api.contribution", "ViewAPIProvider");
		ClassName viewClass = ClassName.get("", this.model.getViewClassname());
		String createViewCode = "return new " + this.model.getViewClassname() + "()";
		MethodSpec createView = MethodSpec.methodBuilder("createView")
				.addModifiers(Modifier.PUBLIC)
				.addAnnotation(Override.class)
				.addParameter(viewAPIProvider, "apiProvider")
				.returns(viewClass)
				.addStatement(createViewCode)
				.build();
		
		ClassName contributionClass = ClassName.get("", this.model.getContributionClassname());
		ClassName programAPIProvider = ClassName.get("com.ur.urcap.api.contribution.program", "ProgramAPIProvider");
		ClassName dataModel = ClassName.get("com.ur.urcap.api.domain.data", "DataModel");
		ClassName creationContext = ClassName.get("com.ur.urcap.api.contribution.program", "CreationContext");
		String createNodeCode = "return new " + this.model.getContributionClassname() + "(apiProvider, view, model)";
		MethodSpec createNode = MethodSpec.methodBuilder("createNode")
				.addModifiers(Modifier.PUBLIC)
				.addAnnotation(Override.class)
				.addParameter(programAPIProvider, "apiProvider")
				.addParameter(viewClass, "view")
				.addParameter(dataModel, "model")
				.addParameter(creationContext, "context")
				.returns(contributionClass)
				.addStatement(createNodeCode)
				.build();
		
		ClassName swingNodeService = ClassName.get("com.ur.urcap.api.contribution.program.swing","SwingProgramNodeService");
		TypeSpec serviceClass = TypeSpec.classBuilder(this.model.getServiceClassname())
				.addModifiers(Modifier.PUBLIC)
				.addSuperinterface(ParameterizedTypeName.get(swingNodeService, contributionClass, viewClass))
				.addMethod(getId)
				.addMethod(configureContribution)
				.addMethod(getTitle)
				.addMethod(createView)
				.addMethod(createNode)
				.build();
		
		JavaFile javaFile = JavaFile.builder("com.ur.cap", serviceClass).build();
		
		
		try {
			javaFile.writeTo(filePath);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
	}
	
	
	
}
