package lessons.xml;

/* TOPIC: Parsing XML
 * 
 * */ 


import org.xml.sax.*;
import org.w3c.dom.*;

import javax.xml.parsers.*;


public class Lesson44 {

	public static void main(String[] args) 
	{
		Document xmlDoc = getDocument("src/resources/Lesson44/tvshows.xml");
		
		
		// .getDocumentElement() -> This is a convenience attribute that allows direct 
		// access to the child node that is the document element of the document.
		// In this case the first child of the Document itself is the Root node
		System.out.println("Root: " + xmlDoc.getDocumentElement().getNodeName());
		
		NodeList listOfShows = xmlDoc.getElementsByTagName("show");
		
		System.out.println("Number of shows: " + 
				listOfShows.getLength());
		
		String elementName = "network";
		String attributeName = "country";
		
		getElementAndAttribute(listOfShows, elementName, attributeName);
		
	}  // END OF main METHOD

	private static void getElementAndAttribute(NodeList listOfShows, String elementName, String attributeName) {
		
		try {
			for (int i = 0; i < listOfShows.getLength(); i++) 
			{
				Node showNode = listOfShows.item(i);
				
				// Cast showNode to an xml Element
				Element showElement = (Element) showNode;
				System.out.println(showElement.getTextContent());
				// Get all nodes that have the right elementName
				NodeList networkList = showElement.getElementsByTagName(elementName);
				
				// We know that the networkList contains only 1 element
				// Therefore, item(0) is sufficient - no looping over the items is necessary
				Element networkElement = (Element) networkList.item(0);
				
				System.out.println(networkElement.getTextContent());
				System.out.println(networkElement.getAttribute(attributeName));
				
				NodeList elementList = networkElement.getChildNodes();
				
				if ( networkElement.hasAttribute(attributeName)) 
				{
					/* Get the text inside of the networkElement
					 * The text is a child-Node itself!
					 * 
					 * It could also be accessed by .getTextContent
					 * BUT this would include the text of 
					 * all other child and sub-child-nodes as well! 
					 * */
					System.out.println(networkElement.getTextContent());
					System.out.println(elementName + " : " + 
							((Node) elementList.item(0)).getNodeValue().trim() + 
							" has attribute " + networkElement.getAttribute(attributeName));
				} else {
					System.out.println(elementName);
				}
			} 
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
	}  // END OF getElementAndAttribute METHOD

	private static Document getDocument(String docString) {
		
		try {
			// Create and configure a new DocumentBuilder-Factory
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			
			// Ignore Comments and white spaces
			factory.setIgnoringComments(true);
			factory.setIgnoringElementContentWhitespace(true);
			
			// Enable validating XML before parsing. Works only if xsl and dtd are specified
			// factory.setValidating(true);
			
			// Based on the configuration - create the actual DocumentBuilder
			DocumentBuilder builder = factory.newDocumentBuilder();
			
			return builder.parse(new InputSource(docString));
			
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		return null;
	}  // END OF getDocument METHOD

}  // END OF Lesson44 CLASS
