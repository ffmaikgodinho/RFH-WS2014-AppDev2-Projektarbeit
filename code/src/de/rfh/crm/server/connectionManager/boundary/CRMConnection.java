package de.rfh.crm.server.connectionManager.boundary;

import java.sql.Connection;
import java.sql.SQLException;

public interface CRMConnection {
	public Connection connect() throws SQLException, ClassNotFoundException;
	public void disconnect(Connection con) throws SQLException;
}