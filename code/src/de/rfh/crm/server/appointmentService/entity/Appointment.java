package de.rfh.crm.server.appointmentService.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

import de.rfh.crm.server.contactService.entity.Contact;

public class Appointment implements Serializable{
	UUID id;
	Contact contact;
	Date startDate;
	Date endDate;
	String subject;
	
	public UUID getId() {
		return id;
	}
	public void setId(UUID id) {
		this.id = id;
	}
	public Contact getContact() {
		return contact;
	}
	public void setContact(Contact contact) {
		this.contact = contact;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}

}
