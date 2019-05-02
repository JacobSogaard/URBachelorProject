package urcapplugin.toolbar.handler;

import java.io.Console;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;

import org.apache.commons.lang.ArrayUtils;
import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IAdapterManager;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.handlers.HandlerUtil;
import org.xml.sax.SAXException;

import nature.URCapNature;


public class ConvertToURCapHandler extends AbstractHandler {
	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		ISelection currentSelection = HandlerUtil.getCurrentSelection(event);
		if (currentSelection instanceof IStructuredSelection) {

			Object firstElement = ((IStructuredSelection) currentSelection).getFirstElement();

			// Get an IResource as an adapter from the current selection
			IAdapterManager adapterManager = Platform.getAdapterManager();
			IResource resourceAdapter = adapterManager.getAdapter(firstElement, IResource.class);

			if (resourceAdapter != null) {
				IResource resource = resourceAdapter;
				IProject project = resource.getProject();

				// Validating if the project is an URCap.
				IPath path = project.getLocation();
				if (this.validateProjectAsURCap(path)) {

					try {
						IProjectDescription description = project.getDescription();
						String[] natures = description.getNatureIds();
						String[] newNatures = new String[natures.length + 1];
						System.arraycopy(natures, 0, newNatures, 0, natures.length);

						newNatures[natures.length] = URCapNature.NATURE_ID;

						// validate the natures
						IWorkspace workspace = ResourcesPlugin.getWorkspace();
						IStatus status = workspace.validateNatureSet(newNatures);

						// only apply new nature, if the status is ok
						if (status.getCode() == IStatus.OK) {
							// Flip array of natures so URCap nature is first, and UR icon is show on
							// project in explorer
							// ArrayUtils.reverse(newNatures);
							description.setNatureIds(newNatures);
							project.setDescription(description, null);
						}
						return status;
					} catch (CoreException e) {
						throw new ExecutionException(e.getMessage(), e);
					}
				}
			}
		}

		return Status.OK_STATUS;
	}

	/**
	 * This method reads the pom file on a specific path of a project and validate
	 * if the project is an URCap.
	 * 
	 * @param projectPath
	 * @return true if it is an URCap project.
	 */
	private boolean validateProjectAsURCap(IPath projectPath) {

		boolean isURCapProject = false;

		if (projectPath != null) {
			String pomFilePath = projectPath.toString() + "/pom.xml";
			File file = new File(pomFilePath);

			try {
				isURCapProject = this.readXMLFile(file);
			} catch (SAXException | IOException | ParserConfigurationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return isURCapProject;

	}

	/**
	 * Reads the pom.xml file and validate if a URCap related property exists.
	 * 
	 * @param pomFile
	 * @return true if a URCap related property is defined inside pom.xml.
	 * @throws SAXException
	 * @throws IOException
	 * @throws ParserConfigurationException
	 */

	private boolean readXMLFile(File pomFile) throws SAXException, IOException, ParserConfigurationException {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document doc = builder.parse(pomFile);

		doc.getDocumentElement().normalize();

		if (doc.getElementsByTagName("urcap.install.host").item(0).getTextContent() != null) {
			return true;
		}

		return false;

	}
	
	
}
