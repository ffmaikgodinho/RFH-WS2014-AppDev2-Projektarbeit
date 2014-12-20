package de.rfh.crm.server.contactService.persistence.util;

public class ConnectionException extends Exception {
	public ConnectionException() {
		super("Data-Connection not possible");
	}
}
