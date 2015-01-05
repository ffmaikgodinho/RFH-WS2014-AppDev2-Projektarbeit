package de.rfh.crm.client;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.Date;
import java.util.UUID;

import de.rfh.crm.server.appointmentService.boundary.AppointmentService;
import de.rfh.crm.server.appointmentService.entity.Appointment;
import de.rfh.crm.server.contactService.entity.Contact;

public class SimpleCRMClientAppointmentHandler extends Observer {
	private int id;
	private SimpleCRMClientBroker broker;
	AppointmentService appointmentService = null;
	
	public SimpleCRMClientAppointmentHandler(int id, SimpleCRMClientBroker broker){
		this.id = id;
		this.broker = broker;
		
		try {
			appointmentService = (AppointmentService) Naming.lookup("rmi://localhost:1099/crm/appointmentService");
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 */
//	public void displayAppointmentMenu() {
//		String choice = null;
//		while (choice != "0") {
	
//				choice = SimpleCRMClientHelper.getInputValue("Bitte wählen Sie eine Aktion aus:\n"
//						+ "(1) Termin erstellen\n"
//						+ "(2) Termin in einem Zeitraum ausgeben\n"
//						+ "(3) Termin für Kontakt ausgeben\n"
//						+ "(4) Termin löschen\n"
//						+ "(5) ?Adresse und Termin verbinden\n"
//						+ "(6) ?Adresse von Termin lösen\n"
//						+ "(7) ?Adresse von Termin anzeigen"
//						+ "(0) zurück zum Hauptmenü");
//			    
//				switch (choice) {
//				case "0" :  return;
//				case "1" :  //contactService.getContact();
//							appointmentService.createAppointment(updateAppointment());  
//							break;
//				case "2" :	break;
//				case "3" :	break;
//				case "4" :	break;
//				case "5" :	break;
//				case "6" :	break;
//				case "7" :	break;
//				default: System.out.println("Eingabe ungültig");
//						 break;
//		
//				}
//		}
//	}

	/**
	 * 
	 * @return
	 */
	private Appointment updateAppointment(Appointment app) {
		// ToDo: DateTime instead of Date
		app.setId(UUID.randomUUID());
		app.setStartDate(Date.valueOf(SimpleCRMClientHelper.getInputValue("Bitte geben Sie das Anfangsdatum ein (yyyy-mm-dd):")));
		app.setEndDate(Date.valueOf(SimpleCRMClientHelper.getInputValue("Bitte geben Sie das Enddatum ein (yyyy-mm-dd):")));
		app.setSubject(SimpleCRMClientHelper.getInputValue("Bitte geben Sie das Thema ein:"));

		return app;
	}
	
	/**
	 * 
	 * @return
	 */
	private Appointment updateAppointment(){
		Appointment app = new Appointment();
		Contact contact = new Contact();
		app.setContact(contact);
		
		return updateAppointment(app);
	}

	@Override
	public void update() {
	      System.out.println("Actions updateAppointment() verfügbar."); 
	}
}
