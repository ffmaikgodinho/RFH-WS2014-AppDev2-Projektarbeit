package de.rfh.crm.server.contactService.entity;

import java.io.Serializable;
import java.util.UUID;

public class Contact implements Serializable {

	private static final long serialVersionUID = -6404387813921025506L;
	
	UUID id;
	Address address;
	String firstName = "";
	String lastName = "";
	
	@Override
	public String toString()  {
		return this.firstName + " " + this.lastName;
	}
	public Contact()  {
		setId(UUID.randomUUID());
	}
	public Contact(UUID id)  {
		setId(id);
	}
	public UUID getId() {
		return id;
	}
	private void setId(UUID id) {
		this.id = id;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
}
