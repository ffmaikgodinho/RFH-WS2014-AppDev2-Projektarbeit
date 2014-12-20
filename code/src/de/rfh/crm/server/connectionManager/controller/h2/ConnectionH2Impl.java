package de.rfh.crm.server.connectionManager.controller.h2;

import java.sql.Connection;
import java.sql.SQLException;

import de.rfh.crm.server.connectionManager.boundary.CRMConnection;

public class ConnectionH2Impl implements CRMConnection {

	@Override
	public Connection connect() throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void disconnect(Connection con) throws SQLException {
		// TODO Auto-generated method stub
		
	}

}
