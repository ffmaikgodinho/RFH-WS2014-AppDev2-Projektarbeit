package de.rfh.crm.server.contactService.boundary;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.UUID;

import de.rfh.crm.server.contactService.entity.Contact;

public interface ContactService extends Remote {
	Contact getContact(UUID id) throws RemoteException;
	ArrayList<Contact> getContacts(String searchCriteria) throws RemoteException;
	void deleteContact(UUID id) throws RemoteException;
	void createContact(Contact contact) throws RemoteException;
	void updateContact(Contact contact) throws RemoteException;
}
