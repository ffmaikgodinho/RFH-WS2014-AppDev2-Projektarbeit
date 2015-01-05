package de.rfh.crm.server.appointmentService.persistence.xml;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.UUID;

import javax.xml.parsers.*;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import de.rfh.crm.server.appointmentService.boundary.AppointmentServicePersistence;
import de.rfh.crm.server.appointmentService.entity.Appointment;

/**
 * This class uses JAXP-API for parsing XML-files.
 * @author NZI
 */
public class AppointmentServiceXML implements AppointmentServicePersistence {
	
	/**
	 * Path to local xml file
	 */
	private File file = new File("C:\\Users\\windo_000\\workspace\\RFH-WS2014-AppDev2-Projektarbeit\\code\\data\\appointments.xml");
	
	@Override
	public Appointment getAppointment(UUID id) {
		Document document = readFile();
		Element result = findElementWithUuid(id, document);
	    
		Appointment app = new Appointment();
		app.setId(UUID.fromString(getElementValue(result, "uuid")));
		app.setSubject(getElementValue(result, "subject"));
		
		// Parse Date using a pattern, because Date.parse() is deprecated.
		try {
			DateFormat df = new SimpleDateFormat("MM dd kk:mm:ss z yyyy");
			app.setStartDate(df.parse(getElementValue(result, "startdate")));
			app.setEndDate(df.parse(getElementValue(result, "enddate")));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return app;
	}

	@Override
	public void deleteAppointment(UUID id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void createAppointment(Appointment appointment) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateAppointment(Appointment appointment) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * This method iterated through all nodes of an xml file and search 
	 * for an element, whose tag "UUID" contains the given uuid value.
	 * @param uuid The searched uuid
	 * @param document The xml file which should contain the uuid
	 * @return the full node
	 */
	private Element findElementWithUuid(UUID uuid, Document document) {
		Element element = null;
		
		NodeList allAppointments = document.getChildNodes();
		int appointmentCount = allAppointments.getLength();
		
	    for (int i = 0; i < appointmentCount; i++) {
	    	Node appointment = allAppointments.item(i);
	    	if (appointment.getNodeType() == Node.ELEMENT_NODE) {
	    		element = (Element) appointment;
	    		String currentId = element.getElementsByTagName("UUID").item(0).getNodeValue();
	    		if (UUID.fromString(currentId) == uuid) 
	    			break;
	    	}
	    }
	    
	    return element;
	}

	/**
	 * This method returns the value of a specific child node of element.
	 * @param element A node which contains all information for an appointment
	 * @param tagName The name of a specific child node
	 * @return The value of tagName
	 */
	private static String getElementValue(Element element, String tagName) {
		NodeList nodes = element.getElementsByTagName(tagName).item(0).getChildNodes();
		Node node = (Node) nodes.item(0);
		return node.getNodeValue();
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

}
