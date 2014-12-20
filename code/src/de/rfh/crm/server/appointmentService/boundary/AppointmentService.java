package de.rfh.crm.server.appointmentService.boundary;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.UUID;

import de.rfh.crm.server.appointmentService.entity.Appointment;
import de.rfh.crm.server.contactService.entity.Contact;

public interface AppointmentService extends Remote {
	Appointment getAppointment(UUID id) throws RemoteException;
	void deleteAppointment(UUID id) throws RemoteException;
	void createAppointment(Appointment appointment) throws RemoteException;
	void updateAppointment(Appointment appointment) throws RemoteException;
}
