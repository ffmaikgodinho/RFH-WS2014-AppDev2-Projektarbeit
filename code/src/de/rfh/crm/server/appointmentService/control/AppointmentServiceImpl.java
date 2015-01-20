package de.rfh.crm.server.appointmentService.control;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

import de.rfh.crm.server.appointmentService.boundary.AppointmentService;
import de.rfh.crm.server.appointmentService.boundary.AppointmentServicePersistence;
import de.rfh.crm.server.appointmentService.entity.Appointment;
import de.rfh.crm.server.appointmentService.persistence.xml.AppointmentServiceXML;
import de.rfh.crm.server.contactService.entity.Contact;

public class AppointmentServiceImpl extends UnicastRemoteObject implements AppointmentService {

	private static final long serialVersionUID = 8234758923562209314L;

	private AppointmentServicePersistence appointmentServicePersistence;
	
	protected AppointmentServiceImpl() throws RemoteException {
		super();
		this.appointmentServicePersistence = new AppointmentServiceXML();
	}

	@Override
	public Appointment getAppointment(UUID id) throws RemoteException {
		return this.appointmentServicePersistence.getAppointment(id);
	}

	@Override
	public void deleteAppointment(UUID id) throws RemoteException {
		this.appointmentServicePersistence.deleteAppointment(id);
	}

	@Override
	public void createAppointment(Appointment appointment) throws RemoteException {
		this.appointmentServicePersistence.createAppointment(appointment);
		
	}

	@Override
	public void updateAppointment(Appointment appointment) throws RemoteException {
		this.appointmentServicePersistence.updateAppointment(appointment);
	}

	@Override
	public ArrayList<Appointment> getAppointments(Date startDate, Date endDate)
			throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Appointment> getAppointments(Contact contact)
			throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

}
