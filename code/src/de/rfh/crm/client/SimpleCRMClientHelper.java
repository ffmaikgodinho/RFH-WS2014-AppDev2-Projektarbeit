package de.rfh.crm.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import de.rfh.crm.server.contactService.entity.Address;
import de.rfh.crm.server.contactService.entity.Contact;

public class SimpleCRMClientHelper {
	
	/**
	 * Erstellt ein Kontaktobjekt anhand der eingegebenen Werte.
	 * @return Der Kontakt
	 */
	public static Contact createContactFromInput() {
		Contact contact = new Contact();
		contact.setFirstName(getInputValue("Bitte Vornamen eingeben: "));
		contact.setLastName(getInputValue("Bitte Nachnamen eingeben: "));
		
		Address address = new Address();
		address.setStreet(getInputValue("Bitte Straﬂe eingeben: "));
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
	public static String getInputValue(String question) {
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
