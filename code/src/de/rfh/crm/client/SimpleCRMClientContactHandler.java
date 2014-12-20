package de.rfh.crm.client;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import de.rfh.crm.server.contactService.boundary.ContactService;
import de.rfh.crm.server.contactService.entity.Address;
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
				case "1" :  contactService.createContact(updateContact());
							System.out.println("Adresse wurde eingegeben");
							break;
				case "2" :	List<Contact> contacts = new ArrayList();
							contacts = contactService.getContacts(SimpleCRMClientHelper.getInputValue("Bitte geben Sie einen Suchbegriff ein: "));
							for(Contact contact : contacts)
							{
								System.out.println(contact.getFirstName());
							}
							
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
	private static Contact updateContact(Contact contact) {
		
		contact.setFirstName(SimpleCRMClientHelper.getInputValue("Bitte Vornamen eingeben: "));
		contact.setLastName(SimpleCRMClientHelper.getInputValue("Bitte Nachnamen eingeben: "));
		Address address = contact.getAddress();
		address.setStreet(SimpleCRMClientHelper.getInputValue("Bitte Straße eingeben: "));
		address.setZipcode(SimpleCRMClientHelper.getInputValue("Bitte Postleitzahl eingeben: "));
		address.setCity(SimpleCRMClientHelper.getInputValue("Bitte Stadt eingeben: "));
		address.setCountry(SimpleCRMClientHelper.getInputValue("Bitte Land eingeben: "));
		return contact;
	}
	
	private static Contact updateContact() {
		Contact contact = new Contact();
		Address address = new Address();
		contact.setAddress(address);
		return updateContact(contact);
	}
	
}
