package de.rfh.crm.client;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

import de.rfh.crm.server.appointmentService.boundary.AppointmentService;
import de.rfh.crm.server.appointmentService.entity.Appointment;
import de.rfh.crm.server.contactService.entity.Address;
import de.rfh.crm.server.contactService.entity.Contact;

public class ObserverAppointmentImpl extends Observer {

	// Doesn't singleton makes sense?
	private static AppointmentService appointmentService;
	
	/**
	 * Konstruktor initialisiert die Verbindung zum RMI Server.
	 */
	public ObserverAppointmentImpl() {
		try {
			appointmentService = (AppointmentService) Naming.lookup("rmi://localhost:1099/crm/appointmentService");
		} catch (MalformedURLException | RemoteException | NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Ausgabe der Menüpunkte
	 */
	public void displayAppointmentMenu() {
		String choice = null;
		while (choice != "0") {

			choice = ClientHelper.getInputValue("Bitte wählen Sie eine Aktion aus:\n"
						+ "(1) Termin erstellen\n"
						+ "(2) Termin für ID ausgeben\n"
						+ "(3) Termin in einem Zeitraum ausgeben\n"
						+ "(4) Termin für Kontakt ausgeben\n"
						+ "(5) Termin aktualisieren\n"
						+ "(6) Termin löschen\n"
						+ "(0) zurück zum Hauptmenü");
			try {
				switch (choice) {
				case "0" :  return;
				case "1" :  createAppointment(); break;
				case "2" :  getAppointmentById(); break;
				case "3" :	getAppointmentsByDate(); break;
				case "4" :	getAppointmentsByContact(); break;
				case "5" :  updateAppointment(); break;
				case "6" :	deleteAppointment(); break;
				default: System.out.println("Eingabe ungültig");
						 break;
				}
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/** 
	 * Gibt die Daten eines Termins aus.
	 * @throws RemoteException
	 */
	private void getAppointmentById() throws RemoteException {
		String input = ClientHelper.getInputValue("Bitte geben Sie die ID an: ");
		UUID id = UUID.fromString(input);
		
		Appointment app = appointmentService.getAppointment(id);
		
		System.out.println("-------");
		System.out.println("Betreff:\t " + app.getSubject());
		System.out.println("Von:\t " + app.getStartDate());
		System.out.println("Bis:\t " + app.getEndDate());
	}
	

	/**
	 * Diese Methode erzeugt einen leeren Termin und ruft 
	 * anschließend updateAppointment(Appointment) auf.
	 * @throws RemoteException
	 */
	private void createAppointment() throws RemoteException {
		Appointment app = new Appointment();
		Contact contact = new Contact();
		Address address = new Address();
		
		contact.setAddress(address);
		app.setContact(contact);
		app.setId(UUID.randomUUID());
		
		appointmentService.createAppointment(updateAppointment(app));
		System.out.println("Termin erfolgreich erstellt.");
	}
	
	/**
	 * Holt eine Liste aller Termine in einem abgefragten Zeitraum 
	 * und gibt diese aus.
	 * @throws RemoteException
	 */
	private void getAppointmentsByDate() throws RemoteException {
		// Define DateRange
		String fromAsString = ClientHelper.getInputValue("Bitte geben Sie den Anfangszeitpunkt an (yyyy-mm-dd): ");
		Date from = ClientHelper.getDateByString(fromAsString, "yyyy-mm-dd");
		String toAsString = ClientHelper.getInputValue("Bitte geben Sie den Endzeitpunkt an (yyyy-mm-dd): ");
		Date to = ClientHelper.getDateByString(toAsString, "yyyy-mm-dd");

		// Get Appointments
		ArrayList<Appointment> apps = appointmentService.getAppointments(from, to);
		
		// Output
		printAllAppointments(apps);
	}
	
	/**
	 * Holt eine Liste aller Termine eines abgefragten Kontakts
	 * und gibt diese aus.
	 * @throws RemoteException
	 */
	private void getAppointmentsByContact() throws RemoteException {
		// Define Contact
		Contact contact = new Contact();
		contact.setFirstName(ClientHelper.getInputValue("Bitte geben Sie den Vornamen an: "));
		contact.setFirstName(ClientHelper.getInputValue("Bitte geben Sie den Nachnamen an: "));
		contact.getAddress().setStreet(ClientHelper.getInputValue("Bitte geben Sie die Straße an: "));
		contact.getAddress().setZipcode(ClientHelper.getInputValue("Bitte geben Sie die PLZ an: "));
		contact.getAddress().setCity(ClientHelper.getInputValue("Bitte geben Sie die Stadt an: "));
		contact.getAddress().setCountry(ClientHelper.getInputValue("Bitte geben Sie das Land an: "));
		
		// Get Appointments
		ArrayList<Appointment> apps = appointmentService.getAppointments(contact);
		
		// Output
		printAllAppointments(apps);
	}

	/**
	 * Löscht einen Termin nach Bestätigung.
	 * @throws RemoteException
	 */
	private void deleteAppointment() throws RemoteException {
		String input = ClientHelper.getInputValue("Bitte geben Sie die ID an: ");
		UUID id = UUID.fromString(input);
		
		Appointment app = appointmentService.getAppointment(id);
		
		String confirm = ClientHelper.getInputValue("Sind Sie sicher, dass Sie folgenden Termin löschen möchten? (Y/N)) "
				+ app.getSubject());
		
		if (confirm.equalsIgnoreCase("Y")) {
			appointmentService.deleteAppointment(id);
			System.out.println("Termin gelöscht.");
		}
	}
	
	/**
	 * Ausgabe aller Eigenschaften der übergebenen Termine.
	 * @param apps Liste aller Termine
	 */
	private void printAllAppointments(ArrayList<Appointment> apps) {
		if (apps != null) {
			System.out.println(apps.size() + " Termine gefunden.");
			for (Appointment app : apps) {
				System.out.println("-------");
				System.out.println("Betreff:\t " + app.getSubject());
				System.out.println("Von:\t " + app.getStartDate());
				System.out.println("Bis:\t " + app.getEndDate());
				System.out.println("Kunde:\t " + app.getContact().getFirstName() +" " + app.getContact().getLastName());
				System.out.println("Anschrift:\t " + app.getContact().getAddress());
			}
		} else {
			System.out.println("Es wurden keine Termine im angegebenen Zeitraum gefunden.");
		}
	}

	/**
	 * Diese Methode fragt alle erforderlichen Parameter eines 
	 * Termins inkl. des Kontakts und Adresse ab.
	 * @param app Der Termin, der aktualisiert werden soll.
	 * @return Die vollständige Adresse
	 */
	private Appointment updateAppointment(Appointment app) {
		// id bleibt identisch!
		app.setStartDate(ClientHelper.getDateByString(ClientHelper.getInputValue("Bitte geben Sie das Anfangsdatum ein (yyyy-mm-dd):"), "yyyy-mm-dd"));
		app.setEndDate(ClientHelper.getDateByString(ClientHelper.getInputValue("Bitte geben Sie das Enddatum ein (yyyy-mm-dd):"), "yyyy-mm-dd"));
		app.setSubject(ClientHelper.getInputValue("Bitte geben Sie das Thema ein:"));

		app.getContact().setFirstName(ClientHelper.getInputValue("Bitte geben Sie den Vornamen ein:"));
		app.getContact().setLastName(ClientHelper.getInputValue("Bitte geben Sie den Nachnamen ein:"));
		app.getContact().getAddress().setStreet(ClientHelper.getInputValue("Bitte geben Sie die Straße ein:"));
		app.getContact().getAddress().setZipcode(ClientHelper.getInputValue("Bitte geben Sie die PLZ ein:"));
		app.getContact().getAddress().setCity(ClientHelper.getInputValue("Bitte geben Sie die Stadt ein:"));
		app.getContact().getAddress().setCountry(ClientHelper.getInputValue("Bitte geben Sie das Land ein:"));
		
		return app;
	}
	
	/**
	 * Diese Methode speichert die bisherigen Eigenschaften und fragt anschließend 
	 * die zu ändernden Felder ab.
	 * @return Einen mit allen Eigenschaften gefüllten Termin.
	 * @throws RemoteException 
	 */
	private void updateAppointment() throws RemoteException{
		String input = ClientHelper.getInputValue("Bitte geben Sie die ID an: ");
		UUID id = UUID.fromString(input);
		
		Appointment app = appointmentService.getAppointment(id);
		appointmentService.updateAppointment(updateAppointment(app));
		System.out.println("Termin aktualisiert.");
	}

	@Override
	public void update() {
		this.displayAppointmentMenu();
	}

	@Override
	public String toString() {
		return "Terminverwaltung";
	}

	@Override
	public void consoleOutMainMenu() {
		System.out.println(this.getId() + " - " + this.toString());
	}
}
