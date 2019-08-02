package lessons.xml;

/* TOPIC: XML & JDOM2
 * JDOM2 installed from "http://jdom.org/downloads/index.html"
 * Note: The latest version of JDOM2 was published in 2015 
 * */

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.Text;
import org.jdom2.input.SAXBuilder;  // Create JDOM Documemt. SAX: Simple API for XML
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;  // Handles output a JDOM Document to a file

public class Lesson46 {

	public static void main(String[] args) {
		writeXML();
		readXML();
		
	}  // END OF main METHOD
	
	private static void writeXML() {
		// Create an empty doc
		Document doc = new Document();
		
		// Create a single Node
		Element theRoot = new Element("tvshows");
		
		// Attach the node to the doc as Root node
		doc.setRootElement(theRoot);
		
		// Create two new Elements
		Element show = new Element("show");
		Element name = new Element("name");
		
		// Add "show_id" as Attribute with the value "show_001" 
		// to the Element "name"
		name.setAttribute("show_id", "show_001");
		
		// Add a Text-content to name
		name.addContent(new Text("Star Trek TNG"));
		
		Element network = new Element("network");
		network.setAttribute("country", "US");
		
		network.addContent(new Text("ABC"));
		
		// Add the nodes "name" and "network" as child-nodes to "show"
		show.addContent(name);
		show.addContent(network);
		
		// Add the show-node with all children as child-node to Root-node
		theRoot.addContent(show);
		
		// -------------- Repeat the code to add a second show! ------------
		Element show2 = new Element("show");
		Element name2 = new Element("name");
		
		// Add "show_id" as Attribute with the value "show_001" 
		// to the Element "name"
		name2.setAttribute("show_id", "show_002");
		
		// Add a Text-content to name
		name2.addContent(new Text("Fawlty Towers"));
		
		Element network2 = new Element("network");
		network2.setAttribute("country", "UK");
		
		network2.addContent(new Text("BBC"));
		
		// Add the nodes "name" and "network" as child-nodes to "show"
		show2.addContent(name2);
		show2.addContent(network2);
		
		// Add the show-node with all children as child-node to Root-node
		theRoot.addContent(show2);
		
		// -------------- Write the XML to a File ------------
		// instantiate XMLOutputter-Object with a Formatter
		XMLOutputter xmlOutput = new XMLOutputter(Format.getPrettyFormat());
		
		try {
			// .output(Document doc, OutputWriter-Class)
			xmlOutput.output(doc, new FileOutputStream(new File("src/resources/Lesson46/jdomMade.xml")));
			System.out.println("Finished writing XML-File.");
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}  // END OF writeXML METHOD
	
	private static void readXML() {
		
		// The build class reads the file and creates a Document-object from it
		SAXBuilder builder = new SAXBuilder();
		
		try {
			Document readDoc = builder.build(new File("src/resources/Lesson46/jdomMade.xml"));
			
			System.out.println("Root: " + readDoc.getRootElement());
			
			// Walk down the root the second child (root -> show -> name)
			System.out.println("Show: " + 
					readDoc.getRootElement().getChild("show").getChildText("name"));
			
			System.out.println("Show ID: " + 
					readDoc.getRootElement().getChild("show").getChild("name").getAttributeValue("show_id"));
				
			System.out.println();
			
			Element root = readDoc.getRootElement();
			
			for ( Element curEle : root.getChildren("show") ) {
				System.out.println("Show Name: " + curEle.getChildText("name"));
				System.out.println("Show ID: " + curEle.getChild("name").getAttributeValue("show_id"));
				System.out.println("On: " + curEle.getChildText("network") + " in the " + curEle.getChild("network").getAttributeValue("country"));
				System.out.println();
			}
			
		} catch (JDOMException | IOException e) {
			e.printStackTrace();
		}
		
	}  // END OF readXML METHOD
}
