package de.rfh.crm.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import de.rfh.crm.server.addressService.boundary.AddressService;
import de.rfh.crm.server.addressService.entity.Address;

public class SimpleCRMClient {

	public static void main(String[] args) {
//		try {
			String choice = null;
		AddressService addressService = (AddressService) Naming.lookup("rmi://localhost:1099/addressservice");
			//System.out.println("Client is running");
			
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			System.out.println("Bitte wählen Sie eine Aktion aus:");
			System.out.println("(1) Adresse eingeben");
			System.out.println("(2) Adresse suchen");
			System.out.println("(3) Adresse ändern");
			System.out.println("(4) Adresse löschen");
			
			try {
				choice = reader.readLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			switch (choice) {
			case "1" : Address address = new Address();
					   
					   addressService.createAddress(address);
			case "2" :
			case "3" :
			case "4" :
			default: System.out.println("Eingabe ungültig");
					 break;
	
			}
//		} catch (MalformedURLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (RemoteException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (NotBoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}
}
