package de.rfh.crm.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import de.rfh.crm.server.addressService.boundary.AddressService;
import de.rfh.crm.server.addressService.entity.Address;

public class SimpleCRMClient {

	public static void main(String[] args) {
//		try {
		String choice = null;
		while (choice != "5") {
			//AddressService addressService = (AddressService) Naming.lookup("rmi://localhost:1099/addressservice");
			//System.out.println("Client is running");

			choice = getInputValue("Bitte wählen Sie eine Aktion aus:\n"
					+ "(1) Kontakt anlegen\n"
					+ "(2) Kontakt suchen\n"
					+ "(3) Kontakt ändern\n"
					+ "(4) Kontakt löschen\n"
					+ "(5) beenden");
			
			switch (choice) {
			case "1" : //Contact contact = new Contact();
					   //contact.firstname = 
							   
					   
					   //addressService.createContact(contact);
						System.out.println("Adresse wurde eingegeben");
						break;
			case "2" :	System.out.println("Adresse wurde gefunden");
						break;
			case "3" :	System.out.println("Adresse wurde geändert");
						break;
			case "4" :	System.out.println("Adresse wurde gelöscht");
						break;
			case "5" :  return;
			default: System.out.println("Eingabe ungültig");
					 break;
	
			}
//		} catch (MalformedURLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (RemoteException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (NotBoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		}
	}

	/**
	 * Erstellt ein Kontaktobjekt anhand der eingegebenen Werte.
	 * @return Der Kontakt
	 */
	private Contact createContactFromInput() {
		Contact contact = new Contact();
		contact.firstname = getInputValue("Bitte Vornamen eingeben: ");
		contact.lastname = getInputValue("Bitte Nachnamen eingeben: ");
		
		Address address = new Address();
		address.street = getInputValue("Bitte Straße eingeben: ");
		
		contact.address = address;
		
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
