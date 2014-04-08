package org.anvard.introtojava.xml;

import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class DomExample {

    public static void main(String[] args) throws Exception {
	DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	DocumentBuilder builder = factory.newDocumentBuilder();
	InputStream is = DomExample.class.getResourceAsStream("config.xml");
	Document doc = builder.parse(is);
	NodeList list = doc.getChildNodes();
	list = list.item(0).getChildNodes();
	for (int i = 0; i < list.getLength(); i++) {
	    Node node = list.item(i);
	    if (node.getNodeName().equals("savePath")) {
		System.out.println("Save path: " + node.getTextContent());
	    }
	}
    }
}
