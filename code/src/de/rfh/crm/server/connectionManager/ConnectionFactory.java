package de.rfh.crm.server.connectionManager;

import de.rfh.crm.server.connectionManager.boundary.DefaultConnection;
import de.rfh.crm.server.connectionManager.controller.h2.DefaultConnectionH2;
import de.rfh.crm.server.connectionManager.controller.mysql.DefaultConnectionMySql;

public class ConnectionFactory {

	public static DefaultConnection getMySqlDefaultConnection()  {
		return new DefaultConnectionMySql();
	}
	public static DefaultConnection getH2DefaultConnection()  {
		return new DefaultConnectionH2();
	}
}
