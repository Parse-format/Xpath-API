package com.acc.dev.Core;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class UseXPath {
	private static List<String> listTwo = new ArrayList<String>();

	public static void read(String xml)
			throws SAXException, IOException, ParserConfigurationException, XPathExpressionException {

		// Turn String into a Document
		Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder()
				.parse(new ByteArrayInputStream(xml.getBytes()));

		// Setup XPath to retrieve all tags and values
		XPath xPath = XPathFactory.newInstance().newXPath();
		NodeList nodeList = (NodeList) xPath.evaluate("/customer/name", document, XPathConstants.NODESET);

		// Iterate through nodes
		for (int i = 0; i < nodeList.getLength(); i++) {
			Node node = nodeList.item(i);
			if (node.getChildNodes() != null)
				listTwo.add(i, node.getTextContent());

		}
		System.out.println(listTwo);
	}

	public static void main(String[] args)
			throws XPathExpressionException, SAXException, IOException, ParserConfigurationException {
		String xml = "<customer>" + "<age>35</age>" + "<name>Basanta</name>" + "<age>24</age>" + "<name>Santosh</name>"
				+ "</customer>";
		InputSource source = new InputSource(new StringReader(xml));
		XPath xpath = XPathFactory.newInstance().newXPath();
		Object customer = xpath.evaluate("/customer", source, XPathConstants.NODE);
		String age = xpath.evaluate("age", customer);
		String name = xpath.evaluate("name", customer);
		// System.out.println(age + " " + name);
		read(xml);
	}
}
