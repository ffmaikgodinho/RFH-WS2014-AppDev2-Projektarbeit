package de.rfh.crm.server.appointmentService.boundary;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

import de.rfh.crm.server.appointmentService.entity.Appointment;
import de.rfh.crm.server.contactService.entity.Contact;

public interface AppointmentService extends Remote {
	Appointment getAppointment(UUID id) throws RemoteException;
	ArrayList<Appointment> getAppointments(Date startDate, Date endDate) throws RemoteException;
	ArrayList<Appointment> getAppointments(Contact contact) throws RemoteException;
	void deleteAppointment(UUID id) throws RemoteException;
	void createAppointment(Appointment appointment) throws RemoteException;
	void updateAppointment(Appointment appointment) throws RemoteException;
}
