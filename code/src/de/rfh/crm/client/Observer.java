package de.rfh.crm.client;

public abstract class Observer {
	
	private int id = 0;
	
	public void setId(int id)  {
		this.id = id;
	}
	
	public int getId()  {
		return this.id;
	}
	
	public abstract void update();
	public abstract String toString();
	public abstract void consoleOutMainMenu();
}
