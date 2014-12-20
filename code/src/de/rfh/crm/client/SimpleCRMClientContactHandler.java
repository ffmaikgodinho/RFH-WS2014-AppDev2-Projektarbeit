package de.rfh.crm.client;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.UUID;

import de.rfh.crm.server.contactService.boundary.ContactService;
import de.rfh.crm.server.contactService.entity.Contact;

public class SimpleCRMClientContactHandler {
	
	public void displayContactMenu() {
		String choice = null;
		while (choice != "5") {
			try {
				ContactService contactService = (ContactService) Naming.lookup("rmi://localhost:1099/crm/contactService");
				//System.out.println("Client is running");
	
				choice = SimpleCRMClientHelper.getInputValue("Bitte wählen Sie eine Aktion aus:\n"
						+ "(1) Kontakt anlegen\n"
						+ "(2) Kontakt suchen\n"
						+ "(3) Kontakt ändern\n"
						+ "(4) Kontakt löschen\n"
						+ "(5) beenden");
				
				switch (choice) {
				case "1" :  contactService.createContact(SimpleCRMClientHelper.createContactFromInput());
							System.out.println("Adresse wurde eingegeben");
							break;
				case "2" :	Contact contact = new Contact();
							contact = contactService.getContact(UUID.randomUUID());
							System.out.println(contact.getFirstName());
							System.out.println("Adresse wurde gefunden");
							break;
				case "3" :	System.out.println("Adresse wurde geändert");
							break;
				case "4" :	System.out.println("Adresse wurde gelöscht");
							break;
				case "5" :  return;
				default: System.out.println("Eingabe ungültig");
						 break;
		
				}
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
	}
}
