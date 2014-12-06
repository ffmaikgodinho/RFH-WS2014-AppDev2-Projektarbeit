package de.rfh.crm.server.addressService.boundary;

import java.rmi.Remote;
import java.util.UUID;

import de.rfh.crm.server.addressService.entity.Address;

public interface AddressService extends Remote {
	Address getAddress(UUID id);
	void deleteAddress(UUID id);
	void createAddress(Address address);
	void updateAddress(Address address);
}
