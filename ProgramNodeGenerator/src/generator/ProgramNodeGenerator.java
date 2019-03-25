package generator;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Locale;

import javax.lang.model.element.Modifier;
import javax.swing.JPanel;

import org.eclipse.core.resources.ResourcesPlugin;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterizedTypeName;
import com.squareup.javapoet.TypeSpec;

import programnodegenerator.modelClasses.ProgramNodeModel;

/**
 * Class to generate files a program node using the JavaPoet API
 * Class uses a ProgramNodeModel as the model for generating the classes.
 * 
 * TODO Get artifact and group id from project the classes should be generated in, so the path import for all auto generated classes are right
 * 
 * @author jacob
 *
 */
public class ProgramNodeGenerator {

	private ProgramNodeModel model;
	private String path;
	private File filePath;
	private ClassName contributionConfigurationClass, viewAPIProviderClass, viewClass, contributionClass, programAPIProviderClass,
					dataModelClass, creationContextClass, swingNodeServiceClass, contributionProviderClass, programAPIClass,
					swingProgramNodeViewClass, scriptWriterClass, serviceClass;
	
	public ProgramNodeGenerator(ProgramNodeModel model) {
		this.path = ResourcesPlugin.getWorkspace().getRoot().getLocation().toString(); 
		this.filePath = new File(path);
		System.out.println(filePath.getPath());
		this.model = model;
		this.serviceClassGenerator();
		this.viewClassGenerator();
		this.contributionClassGenerator();
		this.activatorClassGenerator();
		
		
	}

	
	private void activatorClassGenerator() {
		this.serviceClass = ClassName.get("", this.model.getServiceClassname());
		MethodSpec start = MethodSpec.methodBuilder("start")
				.addAnnotation(Override.class)
				.addModifiers(Modifier.PUBLIC)
				.returns(void.class)
				.addParameter(BundleContext.class, "bundleContext")
				.addStatement("bundleContext.registerService(SwingProgramNodeService.class, new $T(), null)", this.serviceClass)
				.addStatement("bundleContext.registerService(SwingProgramNodeService.class, new $T(), null)", this.viewClass)
				.addStatement("bundleContext.registerService(SwingProgramNodeService.class, new $T(), null)", this.contributionClass)
				.addException(Exception.class)
				.build();
		
		
		MethodSpec stop = MethodSpec.methodBuilder("stop")
				.addAnnotation(Override.class)
				.addModifiers(Modifier.PUBLIC)
				.returns(void.class)
				.addParameter(BundleContext.class, "bundleContext")
				.addStatement("// TODO Auto-generated method stub")
				.addException(Exception.class)
				.build();
		
		TypeSpec activatorClass = TypeSpec.classBuilder("Activator")
				.addModifiers(Modifier.PUBLIC)
				.addSuperinterface(BundleActivator.class)
				.addMethod(start)
				.addMethod(stop)
				.build();
		
		JavaFile javaFile = JavaFile.builder("com.ur.cap", activatorClass).build();
		
		try {
			javaFile.writeTo(filePath);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void contributionClassGenerator() {
		MethodSpec openView = MethodSpec.methodBuilder("openView")
				.addAnnotation(Override.class)
				.addModifiers(Modifier.PUBLIC)
				.returns(void.class)
				.build();
		
		MethodSpec closeView = MethodSpec.methodBuilder("closeView")
				.addAnnotation(Override.class)
				.addModifiers(Modifier.PUBLIC)
				.returns(void.class)
				.build();
		
		MethodSpec getTitle = MethodSpec.methodBuilder("getTitle")
				.addAnnotation(Override.class)
				.addModifiers(Modifier.PUBLIC)
				.returns(String.class)
				.addStatement("return $S", this.model.getNodeTitle())
				.build();
		
		MethodSpec isDefined = MethodSpec.methodBuilder("isDefined")
				.addAnnotation(Override.class)
				.addModifiers(Modifier.PUBLIC)
				.returns(boolean.class)
				.addStatement("return true")
				.build();
		
		this.scriptWriterClass = ClassName.get("com.ur.urcap.api.domain.script", "ScriptWriter");
		MethodSpec generateScript = MethodSpec.methodBuilder("generateScript")
				.addAnnotation(Override.class)
				.addModifiers(Modifier.PUBLIC)
				.addParameter(scriptWriterClass, "writer")
				.returns(void.class)
				.build();
		
		MethodSpec constructor = MethodSpec.constructorBuilder()
				.addModifiers(Modifier.PUBLIC)
				.addParameter(programAPIProviderClass, "apiProvider")
				.addParameter(viewClass, "view")
				.addParameter(dataModelClass, "model")
				.addStatement("this.$N = $N", "programAPI", "apiProvider.getProgramAPI()")
				.addStatement("this.$N = $N", "view", "view")
				.addStatement("this.$N = $N", "model", "model")
				.build();
		
		this.programAPIClass = ClassName.get("com.ur.urcap.api.domain", "ProgramAPI");
		TypeSpec contributionClass = TypeSpec.classBuilder(this.model.getContributionClassname())
				.addModifiers(Modifier.PUBLIC)
				.addSuperinterface(this.contributionClass)
				.addField(this.programAPIClass, "programAPI", Modifier.PRIVATE, Modifier.FINAL)
				.addField(this.viewClass, "view", Modifier.PRIVATE, Modifier.FINAL)
				.addField(this.dataModelClass, "model", Modifier.PRIVATE, Modifier.FINAL)
				.addMethod(constructor)
				.addMethod(openView)
				.addMethod(closeView)
				.addMethod(getTitle)
				.addMethod(isDefined)
				.addMethod(generateScript)
				.build();
		
		JavaFile javaFile = JavaFile.builder("com.ur.cap", contributionClass).build();
		
		try {
			javaFile.writeTo(filePath);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void viewClassGenerator() {
		this.contributionProviderClass = ClassName.get("com.ur.urcap.api.contribution","ContributionProvider");
		MethodSpec buildUI = MethodSpec.methodBuilder("buildUI")
				.addAnnotation(Override.class)
				.addModifiers(Modifier.PUBLIC)
				.returns(void.class)
				.addParameter(JPanel.class, "panel")
				.addParameter(ParameterizedTypeName.get(contributionProviderClass, contributionClass), "provider")
				.addStatement("panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS))")
				.build();
		
		MethodSpec constructor = MethodSpec.constructorBuilder()
				.build();
		
		this.swingProgramNodeViewClass = ClassName.get("com.ur.urcap.api.contribution.program.swing", "SwingProgramNodeView");
		TypeSpec viewClass = TypeSpec.classBuilder(this.model.getViewClassname())
				.addModifiers(Modifier.PUBLIC)
				.addSuperinterface(ParameterizedTypeName.get(swingProgramNodeViewClass, contributionClass))
				.addMethod(buildUI)
				.addMethod(constructor)
				.build();
		
		JavaFile javaFile = JavaFile.builder("com.ur.cap", viewClass).build();
		
		try {
			javaFile.writeTo(filePath);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
	}
	
	
	private void serviceClassGenerator() {
		MethodSpec getId = MethodSpec.methodBuilder("getId")
				.addAnnotation(Override.class)
				.addModifiers(Modifier.PUBLIC)
				.returns(String.class)
				.addStatement("return $S", this.model.getNodeId())
				.build();
		
		String configureContributionCode = "configuration.setChildrenAllowed(" + this.model.isSetChildrenAllowed() + ")";
		this.contributionConfigurationClass = ClassName.get("com.ur.urcap.api.contribution.program", "ContributionConfiguration");
		MethodSpec configureContribution = MethodSpec.methodBuilder("configureContribution")
				.addModifiers(Modifier.PUBLIC)
				.addAnnotation(Override.class)
				.addParameter(contributionConfigurationClass, "configuration")
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
		
		this.viewAPIProviderClass = ClassName.get("com.ur.urcap.api.contribution", "ViewAPIProvider");
		this.viewClass = ClassName.get("", this.model.getViewClassname());
		MethodSpec createView = MethodSpec.methodBuilder("createView")
				.addModifiers(Modifier.PUBLIC)
				.addAnnotation(Override.class)
				.addParameter(viewAPIProviderClass, "apiProvider")
				.returns(viewClass)
				.addStatement("return new $T()", this.viewClass)
				.build();
		
		this.contributionClass = ClassName.get("", this.model.getContributionClassname());
		this.programAPIProviderClass = ClassName.get("com.ur.urcap.api.contribution.program", "ProgramAPIProvider");
		this.dataModelClass = ClassName.get("com.ur.urcap.api.domain.data", "DataModel");
		this.creationContextClass = ClassName.get("com.ur.urcap.api.contribution.program", "CreationContext");
		MethodSpec createNode = MethodSpec.methodBuilder("createNode")
				.addModifiers(Modifier.PUBLIC)
				.addAnnotation(Override.class)
				.addParameter(programAPIProviderClass, "apiProvider")
				.addParameter(viewClass, "view")
				.addParameter(dataModelClass, "model")
				.addParameter(creationContextClass, "context")
				.returns(contributionClass)
				.addStatement("return new $T(apiProvider, view, model)", this.contributionClass)
				.build();
		
		this.swingNodeServiceClass = ClassName.get("com.ur.urcap.api.contribution.program.swing","SwingProgramNodeService");
		TypeSpec serviceClass = TypeSpec.classBuilder(this.model.getServiceClassname())
				.addModifiers(Modifier.PUBLIC)
				.addSuperinterface(ParameterizedTypeName.get(swingNodeServiceClass, contributionClass, viewClass))
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
