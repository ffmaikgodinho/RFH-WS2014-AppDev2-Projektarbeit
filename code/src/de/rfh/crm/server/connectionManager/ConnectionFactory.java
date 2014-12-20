package de.rfh.crm.server.connectionManager;

import de.rfh.crm.server.connectionManager.boundary.CRMConnection;
import de.rfh.crm.server.connectionManager.controller.mysql.ConnectionMySqlImpl;

public class ConnectionFactory {

	public static CRMConnection getMySqlConnection()  {
		return new ConnectionMySqlImpl();
	}
}
