package lessons.xml;

//Contains all necessary Objects such as Element etc. to represent / interact the XML-document
import org.w3c.dom.*;

import javax.xml.xpath.*;
import java.io.IOException;
import javax.xml.parsers.*;
import org.xml.sax.SAXException;

/* TOPIC: XML & Java XPath
 * */

public class Lesson45 {

	public static void main(String[] args)
	{
		DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance();
		
		domFactory.setNamespaceAware(true);
		
		DocumentBuilder builder;  // will turn the XML into a DOM-tree
		
		Document doc = null;
		
		try {
			builder = domFactory.newDocumentBuilder();
			
			doc = builder.parse("src/resources/Lesson44/tvshows.xml");
			
		} catch (ParserConfigurationException | SAXException | IOException e) {
			e.printStackTrace();

		}
		
		XPath xpath = XPathFactory.newInstance().newXPath();
		
		getNodeNameandValue(doc, xpath);
		
		
	}  // END OF main METHOD
	
	private static void getNodeNameandValue(Document doc, XPath xpath)
	{
		XPathExpression expr;
		
		Object result = null;
		
		try {
			// expr = xpath.compile("//show/name//text()");
			
			// search by attribute-value -> [@attribute_name='search_value']
			// expr = xpath.compile("//show/name[@id_code='show_001']//text()");
			
			// expr = xpath.compile("//show/actors/actor/*/text()");
			
			expr = xpath.compile("//show/actors/actor/character[@profession='Student']//text()");
			
			// .evaluate(InputSource source, QName returnType)
			// -> this will return a new NodeSet. Could be BOOLEAN; DOM_OBJECTS_MODEL, NODE etc.
			result = expr.evaluate(doc,  XPathConstants.NODESET);
		} catch (XPathExpressionException e) {
			e.printStackTrace();
		}
		
		NodeList nodes = (NodeList) result;
		
		for (int i = 0; i < nodes.getLength(); i++)
		{
			// The xpath is searching for a text
			// The node that contains that text is a parent of the text
			// Therefore, to get the node's name, getParentNode() is necessary
			System.out.println("'" + nodes.item(i).getParentNode().getNodeName() + "'" + 
					" " + nodes.item(i).getNodeValue());
		}
	}
}
