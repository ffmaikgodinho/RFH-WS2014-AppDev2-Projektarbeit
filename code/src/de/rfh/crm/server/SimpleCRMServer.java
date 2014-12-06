package de.rfh.crm.server;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

import de.rfh.crm.server.addressService.boundary.AddressService;
import de.rfh.crm.server.addressService.control.AddressServiceImpl;

public class SimpleCRMServer {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			LocateRegistry.createRegistry(1099);
			AddressService adService = new AddressServiceImpl();
			//BlaService bla = new BlaService();
			
			Naming.bind("rmi://localhost:1099/addressservice", adService);
			//Naming.bind("rmi://localhost:1099/blaService", blaService);
			
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
