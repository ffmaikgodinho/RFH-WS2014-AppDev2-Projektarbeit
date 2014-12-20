package de.rfh.crm.server.appointmentService.control;

import java.rmi.RemoteException;

import de.rfh.crm.server.appointmentService.boundary.AppointmentService;

public class AppointmentServiceFactory {
	public static AppointmentService getAppointmentService() throws RemoteException {
		return new AppointmentServiceImpl();
	}
}
