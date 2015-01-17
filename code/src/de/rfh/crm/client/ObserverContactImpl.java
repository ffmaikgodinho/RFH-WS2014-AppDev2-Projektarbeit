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

public class ObserverContactImpl extends Observer {
	
	public void displayContactMenu() {
		String choice = null;
		while (choice != "5") {
			try {
				ContactService contactService = (ContactService) Naming.lookup("rmi://localhost:1099/crm/contactService");
				//System.out.println("Client is running");
	
				choice = ClientHelper.getInputValue("Bitte wählen Sie eine Aktion aus:\n"
						+ "(1) Kontakt anlegen\n"
						+ "(2) Kontakt suchen\n"
						+ "(3) Kontakt ändern\n"
						+ "(4) Kontakt löschen\n"
						+ "(0) zurück");
				
				switch (choice) {
				case "0" :  return;
				case "1" :  contactService.createContact(updateContact());
							System.out.println("Adresse wurde gespeichert!");
							break;
				case "2" :	List<Contact> contacts = new ArrayList();
							contacts = contactService.getContacts(ClientHelper.getInputValue("Bitte geben Sie einen Suchbegriff ein: "));
							for(Contact contact : contacts)
							{
								System.out.println(contact.getFirstName());
							}
							break;
				case "3" :	Contact contact = contactService.getContact(UUID.fromString(ClientHelper.getInputValue("Bitte geben Sie die ID der Adresse ein, die Sie bearbeiten möchten: ")));
							contactService.createContact(updateContact(contact));
							System.out.println("Adresse wurde geändert!");
							break;
				case "4" :	contactService.deleteContact(UUID.fromString(ClientHelper.getInputValue("Bitte geben Sie die ID der Adresse ein, die Sie löschen möchten: ")));
							System.out.println("Adresse wurde gelöscht!");
							break;
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
		
		contact.setFirstName(ClientHelper.getInputValue("Bitte Vornamen eingeben: "));
		contact.setLastName(ClientHelper.getInputValue("Bitte Nachnamen eingeben: "));
		Address address = contact.getAddress();
		address.setStreet(ClientHelper.getInputValue("Bitte Straße eingeben: "));
		address.setZipcode(ClientHelper.getInputValue("Bitte Postleitzahl eingeben: "));
		address.setCity(ClientHelper.getInputValue("Bitte Stadt eingeben: "));
		address.setCountry(ClientHelper.getInputValue("Bitte Land eingeben: "));
		return contact;
	}
	
	private static Contact updateContact() {
		Contact contact = new Contact();
		Address address = new Address();
		contact.setAddress(address);
		return updateContact(contact);
	}

	@Override
	public void update() {
		this.displayContactMenu();
	}

	@Override
	public String toString() {
		return "Kontaktverwaltung";
	}

	@Override
	public void consoleOutMainMenu() {
		System.out.println(this.getId() + " - " + this.toString());
	}
}
