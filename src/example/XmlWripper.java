package example;

import java.io.FileInputStream;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class XmlWripper {
	public XPath xPath;
	Document xmlDocument;
	
	public XmlWripper(String path){
		DocumentBuilderFactory builderFactory =
		        DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = null;
		try {
		    builder = builderFactory.newDocumentBuilder();
		    xmlDocument = builder.parse(new FileInputStream(path));
		    
		    xPath =  XPathFactory.newInstance().newXPath();
			  
//		    String expression = "/teste/*";
//		    NodeList nodeList = (NodeList) xPath.compile(expression).evaluate(xmlDocument, XPathConstants.NODESET);
//
//		    for (int i = 0; i < nodeList.getLength(); i++) {
//		    	System.out.println(nodeList.item(i).getNodeName() );
//		    }
			  
		} catch (ParserConfigurationException e) {
		    e.printStackTrace(); 
		} catch (SAXException e) {
	        e.printStackTrace();
	    } catch (IOException e) {
	        e.printStackTrace();
	    } 
	}
	public Node getNode( String path ){
		Node ret = null;
	    try {
	    	ret = (Node) xPath.compile(path).evaluate(xmlDocument, XPathConstants.NODE);
		} catch (XPathExpressionException e) {
			e.printStackTrace();
		}	
	    return ret;
	}
	public Node getNode( String path, Element element ){
		Node ret = null;
	    try {
	    	ret = (Node) xPath.compile(path).evaluate(element , XPathConstants.NODE);
		} catch (XPathExpressionException e) {
			e.printStackTrace();
		}	
	    return ret;
	}
	public NodeList getNodeList( String path ){
		NodeList ret = null;
	    try {
			ret = (NodeList) xPath.compile(path).evaluate(xmlDocument, XPathConstants.NODESET);
		} catch (XPathExpressionException e) {
			e.printStackTrace();
		}	
	    return ret;
	}
}
