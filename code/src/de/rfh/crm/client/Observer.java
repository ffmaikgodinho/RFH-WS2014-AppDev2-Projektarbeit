package de.rfh.crm.client;

public abstract class Observer {
	protected SimpleCRMClientBroker handler;
	public abstract void update();
}
