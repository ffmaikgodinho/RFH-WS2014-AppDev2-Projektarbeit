package de.rfh.crm.server.contactService.control;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.UUID;

import de.rfh.crm.server.contactService.boundary.ContactService;
import de.rfh.crm.server.contactService.boundary.ContactServicePersistence;
import de.rfh.crm.server.contactService.entity.Contact;
import de.rfh.crm.server.contactService.persistence.mock.ContactServiceMock;

public class ContactServiceImpl extends UnicastRemoteObject implements ContactService {

	private static final long serialVersionUID = -8001374305095924375L;

	private ContactServicePersistence contactServvicePersistence;
	
	public ContactServiceImpl() throws RemoteException {
		super();
		this.contactServvicePersistence= new ContactServiceMock();
	}
	@Override
	public Contact getContact(UUID id) {
		System.out.println("GetContact was called");
		return this.contactServvicePersistence.getContact(id);
	}

	@Override
	public void deleteContact(UUID id) {
		this.contactServvicePersistence.deleteContact(id);
	}

	@Override
	public void createContact(Contact contact) {
		this.contactServvicePersistence.createContact(contact);
	}

	@Override
	public void updateContact(Contact contact) {
		this.contactServvicePersistence.updateContact(contact);
		
	}

}
