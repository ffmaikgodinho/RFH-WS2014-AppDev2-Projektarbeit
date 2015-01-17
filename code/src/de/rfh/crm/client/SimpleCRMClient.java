package de.rfh.crm.client;

public class SimpleCRMClient {
	public static void main(String[] args) {
		String category = null;
		
		Observable observable = new ObservableImpl();
		observable.attach(new ObserverAppointmentImpl());
		observable.attach(new ObserverContactImpl());
		
		for (Observer observer : observable.getObservers()) {
			observer.consoleOutMainMenu();
		}
		
		category = ClientHelper.getInputValue("Bitte wählen Sie eine Kategorie aus.");
		
		if (ClientHelper.isInteger(category))  {
			observable.notifyObserverById(Integer.parseInt(category));
		}
	}
}
