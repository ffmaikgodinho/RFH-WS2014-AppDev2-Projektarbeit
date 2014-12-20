package de.rfh.crm.server;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

import de.rfh.crm.server.contactService.boundary.ContactService;
import de.rfh.crm.server.contactService.control.ContactServiceFactory;
import de.rfh.crm.server.contactService.control.ContactServiceImpl;

public class SimpleCRMServer {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			LocateRegistry.createRegistry(1099);
			ContactService contactService = ContactServiceFactory.getContactService();
			
			Naming.bind("rmi://localhost:1099/crm/contactService", contactService);

			System.out.println("Server is up and running");
			
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (AlreadyBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
