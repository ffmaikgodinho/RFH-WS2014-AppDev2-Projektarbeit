package de.rfh.crm.server;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

import de.rfh.crm.server.appointmentService.boundary.AppointmentService;
import de.rfh.crm.server.appointmentService.control.AppointmentServiceFactory;
import de.rfh.crm.server.contactService.boundary.ContactService;
import de.rfh.crm.server.contactService.control.ContactServiceFactory;

public class SimpleCRMServer {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			LocateRegistry.createRegistry(1099);
			ContactService contactService = ContactServiceFactory.getContactService();
			AppointmentService appointmentService = AppointmentServiceFactory.getAppointmentService();
			
			Naming.bind("rmi://localhost:1099/crm/contactService", contactService);
			Naming.bind("rmi://localhost:1099/crm/appointmentService", appointmentService);

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
