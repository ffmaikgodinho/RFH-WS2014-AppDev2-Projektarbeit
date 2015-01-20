package de.rfh.crm.server.appointmentService.persistence.xml;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.UUID;

import javax.xml.parsers.*;
import javax.xml.transform.*;
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

import de.rfh.crm.server.appointmentService.boundary.AppointmentServicePersistence;
import de.rfh.crm.server.appointmentService.entity.Appointment;
import de.rfh.crm.server.contactService.entity.Address;
import de.rfh.crm.server.contactService.entity.Contact;

/**
 * This class uses JAXP-API for parsing XML-files.
 * @author NZI
 */
public class AppointmentServiceXML implements AppointmentServicePersistence {
	
	/**
	 * Path to local xml file. ToDo: May be stored in a configuration file.
	 */
	private final File file = new File("data/appointments.xml");
	
	/**
	 * Findet einen Termin mit der übergebenen ID als Attribut und gibt diesen aus.
	 */
	@Override
	public Appointment getAppointment(UUID id) {
		Document document = readFile();
		NodeList results = findElementWithUuid(id, document);
		Appointment app = new Appointment();
		
		for (int j = 0; j < results.getLength(); j++) {
			Element el = (org.w3c.dom.Element) results.item(j);
			
			app.setId(id);
			app.setSubject(getElementValue(el, "subject"));
			
			// Parse Date using a pattern, because Date.parse() is deprecated.
			try {
				DateFormat df = new SimpleDateFormat("yyyy-mm-dd");
				app.setStartDate(df.parse(getElementValue(el, "startDate")));
				app.setEndDate(df.parse(getElementValue(el, "endDate")));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			Element contactNode = (Element) el.getElementsByTagName("contact").item(0);
			app.setContact(new Contact());
			app.getContact().setFirstName(getElementValue(contactNode, "firstName"));
			app.getContact().setLastName(getElementValue(contactNode, "lastName"));
			
			Element addressNode = (Element) contactNode.getElementsByTagName("address").item(0);
			app.getContact().setAddress(new Address());
			app.getContact().getAddress().setStreet(getElementValue(addressNode, "street"));
			app.getContact().getAddress().setZipcode(getElementValue(addressNode, "zipcode"));
			app.getContact().getAddress().setCity(getElementValue(addressNode, "city"));
			app.getContact().getAddress().setCountry(getElementValue(addressNode, "country"));
		}
		
		return app;
	}

	/**
	 * Entfernt den Termin mit der übergebenen ID aus dem Dokument.
	 */
	@Override
	public void deleteAppointment(UUID id) {
		Document document = readFile();
		NodeList results = findElementWithUuid(id, document);
		
		for (int j = 0; j < results.getLength(); j++) {
			     Element el = (org.w3c.dom.Element) results.item(j);
			     document.getDocumentElement().removeChild(el);
		}
		
		saveFile(document);
	}
	
	/**
	 * Creates a xml node appointment with subnode contact and address and adds it to a xml file.
	 */
	@Override
	public void createAppointment(Appointment appointment) {
		Document document = readFile();
		Element rootNode = document.getDocumentElement();
		
		Element newAppointment = createAppointmentNode(appointment, 
				document);
		
		rootNode.appendChild(newAppointment);
		saveFile(document);
	}

	/**
	 * Ersetzt den bestehenden Termin mit dem aktualisierten Termin. Die ID bleibt dabei
	 * identisch.
	 * @param appointment Der aktualisierte Termin
	 */
	@Override
	public void updateAppointment(Appointment appointment) {
		Document document = readFile();
		NodeList results = findElementWithUuid(appointment.getId(), document);
		
		for (int j = 0; j < results.getLength(); j++) {
			     Element oldAppointment = (org.w3c.dom.Element) results.item(j);
			     Element newAppointment = createAppointmentNode(appointment, document);
			     document.getDocumentElement().replaceChild(newAppointment, oldAppointment);
		}
		
		saveFile(document);
	}
	
	/**
	 * Erstellt einen neuen Node für einen kompletten Termin, der unterhalb des rootNode angesiedelt ist.
	 * @param appointment Die Inhalte des Termins
	 * @param document Das Dokument
	 * @return Den XML Node
	 */
	private Element createAppointmentNode(Appointment appointment, Document document) {
		DateFormat df = new SimpleDateFormat("yyyy-mm-dd");
		
		Element newAppointment = document.createElement("appointment");
		newAppointment.setAttribute("UUID", appointment.getId().toString());
		
		addElementToNode("subject", appointment.getSubject(), document, newAppointment);
		addElementToNode("startDate", df.format(appointment.getStartDate()), document, newAppointment);
		addElementToNode("endDate", df.format(appointment.getEndDate()), document, newAppointment);
		
		// create contact node
		Node contact = document.createElement("contact");
			
		addElementToNode("firstName", appointment.getContact().getFirstName(), document, contact);
		addElementToNode("lastName", appointment.getContact().getLastName(), document, contact);
		
		// Create contact subnode address
		Node address = document.createElement("address");
		
		addElementToNode("street", appointment.getContact().getAddress().getStreet(), document, address);
		addElementToNode("zipcode", appointment.getContact().getAddress().getZipcode(), document, address);
		addElementToNode("city", appointment.getContact().getAddress().getCity(), document, address);
		addElementToNode("country", appointment.getContact().getAddress().getCountry(), document, address);
		
		contact.appendChild(address);
		
		newAppointment.appendChild(contact);
		return newAppointment;
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
			XPathExpression expr = xpath.compile("//appointment[@UUID=\"" + uuid.toString() + "\"]");
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
