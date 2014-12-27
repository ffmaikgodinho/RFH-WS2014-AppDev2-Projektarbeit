package de.rfh.crm.server.contactService.entity;

import java.io.Serializable;
import java.util.UUID;

public class Contact implements Serializable {
	UUID id;
	Address address;
	String firstName;
	String lastName;
	
	@Override
	public String toString()  {
		return this.firstName + " " + this.lastName;
	}
	
	public UUID getId() {
		return id;
	}
	public void setId(UUID id) {
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
