package de.rfh.crm.server.appointmentService.boundary;

import java.util.UUID;

import de.rfh.crm.server.appointmentService.entity.Appointment;

public interface AppointmentServicePersistence {
	Appointment getAppointment(UUID id);
	void deleteAppointment(UUID id);
	void createAppointment(Appointment appointment);
	void updateAppointment(Appointment appointment);
}
