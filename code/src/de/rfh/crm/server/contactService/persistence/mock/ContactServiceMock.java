package de.rfh.crm.server.contactService.persistence.mock;

import java.util.ArrayList;
import java.util.UUID;

import de.rfh.crm.server.contactService.boundary.ContactServicePersistence;
import de.rfh.crm.server.contactService.entity.Address;
import de.rfh.crm.server.contactService.entity.Contact;

public class ContactServiceMock implements ContactServicePersistence {

	@Override
	public Contact getContact(UUID id) {
		Contact contact = new Contact();
		contact.setId(UUID.randomUUID());
		contact.setFirstName("Maik");
		contact.setLastName("Godinho");
		Address ad = new Address();
		ad.setCity("Köln");
		ad.setCountry("Germany");
		ad.setStreet("Musterstr. 1");
		ad.setZipcode("50999");
		contact.setAddress(ad);
		
		return contact;
	}

	@Override
	public void deleteContact(UUID id) {
	}

	@Override
	public void createContact(Contact address) {
		
		
	}

	@Override
	public void updateContact(Contact address) {
	}

	@Override
	public ArrayList<Contact> getContacts(String searchCriteria) {
		// TODO Auto-generated method stub
		return null;
	}

}
