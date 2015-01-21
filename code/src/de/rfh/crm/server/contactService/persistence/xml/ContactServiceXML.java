package de.rfh.crm.server.contactService.persistence.xml;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import de.rfh.crm.server.contactService.boundary.ContactServicePersistence;
import de.rfh.crm.server.contactService.entity.Address;
import de.rfh.crm.server.contactService.entity.Contact;

public class ContactServiceXML implements ContactServicePersistence{
	/**
	 * Path to local xml file. ToDo: May be stored in a configuration file.
	 */
	private final File file = new File("data/contacts.xml");
	
	@Override
	public Contact getContact(UUID id) {
		Document document = readFile();
		NodeList results = findElementWithUuid(id, document);
		Contact contact = new Contact(id);
		
		for (int j = 0; j < results.getLength(); j++) {
			Element el = (org.w3c.dom.Element) results.item(j);
			
			contact.setFirstName(getElementValue(el, "firstName"));
			contact.setLastName(getElementValue(el, "lastName"));
			
			Element addressNode = (Element) el.getElementsByTagName("address").item(0);
			contact.setAddress(new Address());
			contact.getAddress().setStreet(getElementValue(addressNode, "street"));
			contact.getAddress().setZipcode(getElementValue(addressNode, "zipcode"));
			contact.getAddress().setCity(getElementValue(addressNode, "city"));
			contact.getAddress().setCountry(getElementValue(addressNode, "country"));
		}
		
		return contact;
	}

	@Override
	public void deleteContact(UUID id) {
		Document document = readFile();
		NodeList results = findElementWithUuid(id, document);
		
		for (int j = 0; j < results.getLength(); j++) {
			     Element el = (org.w3c.dom.Element) results.item(j);
			     document.getDocumentElement().removeChild(el);
		}
		
		saveFile(document);
	}

	@Override
	public void createContact(Contact address) {
		Document document = readFile();
		Element rootNode = document.getDocumentElement();
		
		Element newContact = createContactNode(address, 
				document);
		
		rootNode.appendChild(newContact);
		saveFile(document);
	}

	@Override
	public void updateContact(Contact address) {
		Document document = readFile();
		NodeList results = findElementWithUuid(address.getId(), document);
		
		for (int j = 0; j < results.getLength(); j++) {
			     Element oldContact = (org.w3c.dom.Element) results.item(j);
			     Element newContact = createContactNode(address, document);
			     document.getDocumentElement().replaceChild(newContact, oldContact);
		}
		
		saveFile(document);
	}

	@Override
	public ArrayList<Contact> getContacts(String searchCriteria) {
		Document document = readFile();
		NodeList results = findElementByString(searchCriteria, document);
		ArrayList<Contact> contacts = new ArrayList<Contact>();
		
		for (int j = 0; j < results.getLength(); j++) {
			Element el = (org.w3c.dom.Element) results.item(j);
			
			Contact contact = new Contact(UUID.fromString(el.getAttribute("UUID")));
			
			contact.setFirstName(getElementValue(el, "firstName"));
			contact.setLastName(getElementValue(el, "lastName"));
			
			Element addressNode = (Element) el.getElementsByTagName("address").item(0);
			contact.setAddress(new Address());
			contact.getAddress().setStreet(getElementValue(addressNode, "street"));
			contact.getAddress().setZipcode(getElementValue(addressNode, "zipcode"));
			contact.getAddress().setCity(getElementValue(addressNode, "city"));
			contact.getAddress().setCountry(getElementValue(addressNode, "country"));
			
			contacts.add(contact);
		}
		
		return contacts;
	}


	/**
	 * Erstellt einen neuen Node für einen kompletten Kontakt, der unterhalb des rootNode angesiedelt ist.
	 * @param contact Die Inhalte des Kontakts
	 * @param document Das Dokument
	 * @return Den XML Node
	 */
	private Element createContactNode(Contact contact, Document document) {
		
		Element newContact = document.createElement("contact");
		newContact.setAttribute("UUID", contact.getId().toString());
		
		addElementToNode("firstName", contact.getFirstName(), document, newContact);
		addElementToNode("lastName", contact.getLastName(), document, newContact);
		
		// Create contact subnode address
		Node address = document.createElement("address");
		
		addElementToNode("street", contact.getAddress().getStreet(), document, address);
		addElementToNode("zipcode", contact.getAddress().getZipcode(), document, address);
		addElementToNode("city", contact.getAddress().getCity(), document, address);
		addElementToNode("country", contact.getAddress().getCountry(), document, address);
		
		newContact.appendChild(address);
		return newContact;
	}

	/**
	 * Creates an element with a text value 
	 * @param tagName The name of the new element
	 * @param value The text value of the element
	 * @param document The xml document
	 * @param parent The parent node, which the element should be appended
	 */
 	private void addElementToNode(String tagName, String value, Document document, Node parent) {
		Node newNode = document.createElement(tagName);
		newNode.appendChild(document.createTextNode(value));
		parent.appendChild(newNode);
	}

	/**
	 * This method searches for all appointments with an attribute uuid,
	 * that matches with the searched uuid.
	 * @param uuid The searched uuid
	 * @param document The xml file which should contain the uuid
	 * @return a list of all matching nodes
	 */
	private NodeList findElementWithUuid(UUID uuid, Document document) {
		XPath xpath = XPathFactory.newInstance().newXPath();
		Object exprResult = null;
		try {
			XPathExpression expr = xpath.compile("//contact[@UUID=\"" + uuid.toString() + "\"]");
			exprResult = expr.evaluate(document, XPathConstants.NODESET);
		} catch (XPathExpressionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		NodeList nodeList = (NodeList) exprResult;
		return nodeList;
	}

	private NodeList findElementByString(String searchCriteria,
			Document document) {
		XPath xpath = XPathFactory.newInstance().newXPath();
		Object exprResult = null;
		try {
			XPathExpression expr = xpath.compile("//contact[contains(., '"+ searchCriteria +"')]");
			exprResult = expr.evaluate(document, XPathConstants.NODESET);
		} catch (XPathExpressionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		NodeList nodeList = (NodeList) exprResult;
		return nodeList;
	}
	
	/**
	 * This method returns the value of a specific child node of element.
	 * @param element A node which contains all information for an appointment
	 * @param tagName The name of a specific child node
	 * @return The value of tagName
	 */
	private static String getElementValue(Element element, String tagName) {
		Node nodes = element.getElementsByTagName(tagName).item(0);
		if (nodes != null) {
			NodeList childs = nodes.getChildNodes();
			Node node = (Node) childs.item(0);
			return node.getNodeValue();
		} else {
			return "";
		}
	}
	
	/**
	 * This method reads a xml file into a Document object.
	 * @return the parsed xml file
	 */
	private Document readFile() {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder;
		Document document = null;
		
		try {
			builder = factory.newDocumentBuilder();
			document = builder.parse(file);
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return document;
	}

	/**
	 * This method saves a DOM Object in a xml file.
	 * @param document
	 */
	private void saveFile(Document document) {
	    TransformerFactory xformFactory  = TransformerFactory.newInstance();
	     Transformer idTransform = null;
	      
		try {
			idTransform = xformFactory.newTransformer();
		} catch (TransformerConfigurationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	      Source input = new DOMSource(document);
	      Result output = new StreamResult(file);
	      try {
			idTransform.transform(input, output);
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
