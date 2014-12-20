package de.rfh.crm.server.contactService.control;

import java.rmi.RemoteException;

import de.rfh.crm.server.contactService.boundary.ContactService;
import de.rfh.crm.server.contactService.persistence.mock.ContactServiceMock;

public class ContactServiceFactory {

	public static ContactService getContactService() throws RemoteException {
		return new ContactServiceImpl();
	}
	
}
