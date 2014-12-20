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
		String choice = null;
		while (choice != "5") {
			try {
			ContactService contactService = (ContactService) Naming.lookup("rmi://localhost:1099/crm/contactService");
			//System.out.println("Client is running");

			choice = getInputValue("Bitte wählen Sie eine Aktion aus:\n"
					+ "(1) Kontakt anlegen\n"
					+ "(2) Kontakt suchen\n"
					+ "(3) Kontakt ändern\n"
					+ "(4) Kontakt löschen\n"
					+ "(5) beenden");
			
			switch (choice) {
			case "1" :  contactService.createContact(createContactFromInput());
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

	/**
	 * Erstellt ein Kontaktobjekt anhand der eingegebenen Werte.
	 * @return Der Kontakt
	 */
	private static Contact createContactFromInput() {
		Contact contact = new Contact();
		contact.setFirstName(getInputValue("Bitte Vornamen eingeben: "));
		contact.setLastName(getInputValue("Bitte Nachnamen eingeben: "));
		
		Address address = new Address();
		address.setStreet(getInputValue("Bitte Straße eingeben: "));
		address.setZipcode(getInputValue("Bitte Postleitzahl eingeben: "));
		address.setCity(getInputValue("Bitte Stadt eingeben: "));
		address.setCountry(getInputValue("Bitte Land eingeben: "));
		contact.setAddress(address);
		
		return contact;
	}
	
	/**
	 * Erzeugt eine Konsolenausgabe mit Aufforderung zur Eingabe.
	 * @param question Die zu beantwortende Frage.
	 * @return Den auf der Konsole eingegebenen Wert
	 */
	private static String getInputValue(String question) {
		String input = null;
		
		System.out.println(question);
		
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		try {
			input = reader.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return input;
	}
}
