package de.rfh.crm.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import de.rfh.crm.server.contactService.entity.Address;
import de.rfh.crm.server.contactService.entity.Contact;

public class SimpleCRMClientHelper {
	
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
