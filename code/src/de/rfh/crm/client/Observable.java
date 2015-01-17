package de.rfh.crm.client;

import java.util.ArrayList;

public interface Observable {
	public void attach(Observer observer);
	public void detach(Observer observer);
	public void notifyAllObservers();
	public void notifyObserverById(int id);
	public ArrayList<Observer> getObservers();
}
