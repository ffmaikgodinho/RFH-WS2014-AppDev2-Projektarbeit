package de.rfh.crm.server.contactService.control;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.UUID;

import de.rfh.crm.server.contactService.boundary.ContactService;
import de.rfh.crm.server.contactService.boundary.ContactServicePersistence;
import de.rfh.crm.server.contactService.entity.Contact;
import de.rfh.crm.server.contactService.persistence.db.ContactServiceDB;
import de.rfh.crm.server.contactService.persistence.mock.ContactServiceMock;

public class ContactServiceImpl extends UnicastRemoteObject implements ContactService {

	private static final long serialVersionUID = -8001374305095924375L;

	private ContactServicePersistence contactServvicePersistence;
	
	public ContactServiceImpl() throws RemoteException {
		super();
		this.contactServvicePersistence = new ContactServiceDB();
	}
	@Override
	public Contact getContact(UUID id) {
		System.out.println("GetContact was called");
		return this.contactServvicePersistence.getContact(id);
	}
	
	@Override
	public ArrayList<Contact> getContacts(String searchCriteria)
			throws RemoteException {
		return this.contactServvicePersistence.getContacts(searchCriteria);
	}

	@Override
	public void deleteContact(UUID id) {
		//hier kann später noch geprüft werden ob der Kontakt überhaupt existiert.
		this.contactServvicePersistence.deleteContact(id);
	}

	@Override
	public void createContact(Contact contact) {
		//hier kann später zum Beispiel noch ein Dublettencheck stattfinden
		this.contactServvicePersistence.createContact(contact);
	}

	@Override
	public void updateContact(Contact contact) {
		this.contactServvicePersistence.updateContact(contact);
		
	}
}
