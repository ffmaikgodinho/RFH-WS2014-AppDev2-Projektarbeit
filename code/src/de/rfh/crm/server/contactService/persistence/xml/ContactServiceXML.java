package de.rfh.crm.server.contactService.persistence.xml;

import java.util.ArrayList;
import java.util.UUID;

import de.rfh.crm.server.contactService.boundary.ContactServicePersistence;
import de.rfh.crm.server.contactService.entity.Contact;

public class ContactServiceXML implements ContactServicePersistence{

	@Override
	public Contact getContact(UUID id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteContact(UUID id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void createContact(Contact address) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateContact(Contact address) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ArrayList<Contact> getContacts(String searchCriteria) {
		// TODO Auto-generated method stub
		return null;
	}

}
