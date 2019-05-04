package urcapplugin.toolbar.handler;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.eclipse.core.runtime.IPath;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class PomFileReader {

	String nodeName;

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
				if (this.readXMLFile(file, "urcap.install.host") != null) {
					isURCapProject = true;
				} else {
					isURCapProject = false;
				}
			} catch (SAXException | IOException | ParserConfigurationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return isURCapProject;

	}

	/**
	 * Get group id of pom file on specified path
	 * 
	 * @param projectPath - Path for root of project as String
	 * @return - Returns the group id of POM file as a String. Null if non was
	 *         found.
	 */
	public String getGroupId(IPath projectPath) {
		String groupId;
		if (projectPath != null) {
			String pomFilePath = projectPath.toString() + "/pom.xml";

			File file = new File(pomFilePath);

			try {
				groupId = this.readXMLFile(file, "groupId");
				if (groupId == null) {
					return "nullgroupId";
				}
				return groupId;
			} catch (SAXException | IOException | ParserConfigurationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return "nullprojectPath";

	}

	/**
	 * Reads the pom.xml file and validate if a URCap related property exists.
	 * 
	 * @param pomFile
	 * @return result if a URCap related property is defined inside pom.xml.
	 * @throws SAXException
	 * @throws IOException
	 * @throws ParserConfigurationException
	 */

	private String readXMLFile(File pomFile, String tagName)
			throws SAXException, IOException, ParserConfigurationException {

		String result = null;

		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document doc = builder.parse(pomFile);

		doc.getDocumentElement().normalize();

		NodeList nodeList = doc.getElementsByTagName(tagName);

		if (nodeList.getLength() > 0) {
			result = doc.getElementsByTagName(tagName).item(0).getTextContent();
		}

		return result;

	}

}
