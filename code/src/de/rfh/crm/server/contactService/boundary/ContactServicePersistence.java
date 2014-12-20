package de.rfh.crm.server.contactService.boundary;

import java.util.ArrayList;
import java.util.UUID;

import de.rfh.crm.server.contactService.entity.Contact;

public interface ContactServicePersistence {
	Contact getContact(UUID id);
	ArrayList<Contact> getContacts(String searchCriteria);
	void deleteContact(UUID id);
	void createContact(Contact address);
	void updateContact(Contact address);
}
