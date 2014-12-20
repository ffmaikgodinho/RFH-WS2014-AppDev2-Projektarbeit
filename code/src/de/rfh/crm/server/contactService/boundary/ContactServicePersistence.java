package de.rfh.crm.server.contactService.boundary;

import java.util.UUID;

import de.rfh.crm.server.contactService.entity.Contact;

public interface ContactServicePersistence {
	Contact getContact(UUID id);
	void deleteContact(UUID id);
	void createContact(Contact address);
	void updateContact(Contact address);
}
