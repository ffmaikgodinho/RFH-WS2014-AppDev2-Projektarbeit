package de.rfh.crm.client;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import de.rfh.crm.server.addressService.boundary.AddressService;

public class SimpleCRMClient {

	public static void main(String[] args) {
		try {
			AddressService addressService = (AddressService) Naming.lookup("rmi://localhost:1099/addressservice");
			System.out.println("Client is running");
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
