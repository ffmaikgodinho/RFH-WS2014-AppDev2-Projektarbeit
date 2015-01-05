package de.rfh.crm.client;

public class SimpleCRMClient {

	public static void main(String[] args) {
		
		// Observer und Broker erstellen
		// Anmeldung des Observers beim Broker erfolgt erst im Bedarfsfall.
		SimpleCRMClientBroker broker = new SimpleCRMClientBroker();
		SimpleCRMClientAppointmentHandler appointmentActions = new SimpleCRMClientAppointmentHandler(1, broker);

		String category = null;
		
		while (category != "0") {
			category = SimpleCRMClientHelper.getInputValue("Bitte wählen Sie eine Kategorie aus:\n"
					+ "(1) Kontaktverwaltung\n"
					+ "(2) Terminverwaltung\n"
					+ "(0) beenden");
			
			switch (category) {
				case "0": return;
				case "1": SimpleCRMClientContactHandler contactHandler = new SimpleCRMClientContactHandler();
						  break;
				case "2": broker.attach(appointmentActions);
					      broker.notify(appointmentActions);
						  break;
				default:  System.out.println("Ungültige Eingabe!"); 
						  break;
			}
		}
	}
}
