package de.rfh.crm.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ClientHelper {
	
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

	/**
	 * Wandelt ein String-Objekt in ein Date-Objekt um.
	 * @param input Der umzuwandelnde String
	 * @param format Das Format des umzuwandelnden Strings
	 * @return Das geparste Datum
	 */
	public static Date getDateByString(String input, String format) {
		DateFormat df = new SimpleDateFormat(format);
		Date from = null;
		
		try {
			from = df.parse(input);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return from;
	}
	
	public static boolean isInteger(String s) {
	    try { 
	        Integer.parseInt(s); 
	    } catch(NumberFormatException e) { 
	        return false; 
	    }
	    // only got here if we didn't return false
	    return true;
	}
}
