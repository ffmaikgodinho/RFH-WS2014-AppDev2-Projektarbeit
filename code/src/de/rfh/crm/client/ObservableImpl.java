package de.rfh.crm.client;

import java.util.ArrayList;

public class ObservableImpl implements Observable {

	ArrayList<Observer> _observer = new ArrayList<Observer>();
	int lastID = 0;
	
	@Override
	public void attach(Observer observer) {
		this.lastID++;
		observer.setId(this.lastID);
		_observer.add(observer);
	}

	@Override
	public void detach(Observer observer) {
		_observer.remove(observer);
	}

	@Override
	public void notifyAllObservers() {
		for (Observer observer : _observer) {
			observer.update();
		}
	}
	
	@Override
	public void notifyObserverById(int id) {
		for (Observer observer : _observer) {
			if (observer.getId() == id)  {
				observer.update();	
			}
		}
	}

	@Override
	public ArrayList<Observer> getObservers() {
		return _observer;
	}
}
