package de.rfh.crm.server.appointmentService.persistence.mock;

import java.util.Date;
import java.util.UUID;

import de.rfh.crm.server.appointmentService.boundary.AppointmentServicePersistence;
import de.rfh.crm.server.appointmentService.entity.Appointment;

public class AppointmentServiceMock implements AppointmentServicePersistence{

	@Override
	public Appointment getAppointment(UUID id) {
		Appointment app = new Appointment();
		app.setId(UUID.randomUUID());
		app.setStartDate(new Date());
		app.setEndDate(new Date());
		app.setSubject("La Le Lu ... nur der Mann im Mond ...");
		return app;
	}

	@Override
	public void deleteAppointment(UUID id) {
		
	}

	@Override
	public void createAppointment(Appointment appointment) {
		
	}

	@Override
	public void updateAppointment(Appointment appointment) {
		
	}

}
