package de.rfh.crm.server.addressService.control;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.UUID;

import de.rfh.crm.server.addressService.boundary.AddressService;
import de.rfh.crm.server.addressService.entity.Address;

public class AddressServiceImpl extends UnicastRemoteObject implements AddressService {

	private static final long serialVersionUID = -8001374305095924375L;

	public AddressServiceImpl() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public Address getAddress(UUID id) {
		return null;
	}

	@Override
	public void deleteAddress(UUID id) {
		
	}

	@Override
	public void createAddress(Address address) {
		
	}

	@Override
	public void updateAddress(Address address) {
		
	}

}
