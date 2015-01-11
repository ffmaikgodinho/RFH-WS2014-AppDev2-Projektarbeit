package de.rfh.crm.client;

public class SimpleCRMClient {
	public static void main(String[] args) {
		String category = null;
		
		while (category != "0") {
			category = SimpleCRMClientHelper.getInputValue("Bitte wählen Sie eine Kategorie aus:\n"
					+ "(1) Kontaktverwaltung\n"
					+ "(2) Terminverwaltung\n"
					+ "(0) beenden");
			
			switch (category) {
				case "0": return;
				case "1": SimpleCRMClientContactHandler contactHandler = new SimpleCRMClientContactHandler();
						  contactHandler.displayContactMenu();
						  break;
				case "2": SimpleCRMClientAppointmentHandler appointmentHandler = new SimpleCRMClientAppointmentHandler();
						  appointmentHandler.displayAppointmentMenu();
						  break;
				default:  System.out.println("Ungültige Eingabe!"); 
						  break;
			}
		}
	}
}
