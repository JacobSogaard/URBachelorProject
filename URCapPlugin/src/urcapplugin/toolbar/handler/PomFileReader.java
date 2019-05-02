package urcapplugin.toolbar.handler;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.eclipse.core.runtime.IPath;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

public class PomFileReader {
	/**
	 * This method reads the pom file on a specific path of a project and validate
	 * if the project is an URCap.
	 * 
	 * @param projectPath
	 * @return true if it is an URCap project.
	 */
	public boolean validateProjectAsURCap(IPath projectPath) {

		boolean isURCapProject = false;

		if (projectPath != null) {
			String pomFilePath = projectPath.toString() + "/pom.xml";
			File file = new File(pomFilePath);

			try {
				isURCapProject = this.readXMLFile(file, "urcap.install.host") != null;
			} catch (SAXException | IOException | ParserConfigurationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return isURCapProject;

	}
	
	/**
	 * Get group id of pom file on specified path
	 * @param projectPath - Path for root of project as String
	 * @return - Returns the group id of POM file as a String. Null if non was found. 
	 */
	public String getGroupId(String projectPath) {
		String groupId;
		if (projectPath != null) {
			String pomFilePath = projectPath + "/pom.xml";
			File file = new File(pomFilePath);
		
			try {
				groupId = this.readXMLFile(file, "urcap.install.host");
				return groupId;
			} catch (SAXException | IOException | ParserConfigurationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
		
		
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

	private String readXMLFile(File pomFile, String tagName) throws SAXException, IOException, ParserConfigurationException {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document doc = builder.parse(pomFile);

		doc.getDocumentElement().normalize();

		return doc.getElementsByTagName(tagName).item(0).getTextContent();
	

	}
	
	
}
