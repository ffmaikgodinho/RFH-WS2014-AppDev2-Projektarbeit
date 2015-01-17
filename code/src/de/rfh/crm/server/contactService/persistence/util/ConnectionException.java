package de.rfh.crm.server.contactService.persistence.util;

public class ConnectionException extends Exception {

	private static final long serialVersionUID = -1263699480908469976L;

	public ConnectionException() {
		super("Data-Connection not possible");
	}
}
