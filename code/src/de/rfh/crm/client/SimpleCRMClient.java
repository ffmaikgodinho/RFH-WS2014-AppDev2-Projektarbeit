package de.rfh.crm.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.UUID;

import de.rfh.crm.server.contactService.boundary.ContactService;
import de.rfh.crm.server.contactService.entity.Address;
import de.rfh.crm.server.contactService.entity.Contact;

public class SimpleCRMClient {

	public static void main(String[] args) {
		String category = null;
		
		while (category != "0") {
			category = SimpleCRMClientHelper.getInputValue("Bitte wählen Sie eine Kategorie aus:\n"
					+ "(1) Kontaktverwaltung\n"
					+ "(2) Terminverwaltung\n"
					+ "(0) beenden");
			
			switch (category) {
				case "0": return;
				case "1": SimpleCRMClientContactHandler contactHandler = new SimpleCRMClientContactHandler();
						  contactHandler.displayContactMenu();
						  break;
				case "2": SimpleCRMClientAppointmentHandler appointmentHandler = new SimpleCRMClientAppointmentHandler();
						  appointmentHandler.displayAppointmentMenu();
						  break;
				default:  System.out.println("Ungültige Eingabe!"); 
						  break;
			}
		}
	}
}
