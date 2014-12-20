package de.rfh.crm.server.connectionManager.controller.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import de.rfh.crm.server.connectionManager.boundary.CRMConnection;

public class ConnectionMySqlImpl implements CRMConnection{
	private String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	private String dbURL = "jdbc:sqlserver://ffmgonb;databaseName=crm";
	private String user = "rfh";
	private String pwd = "rfh";
	
	
	public Connection connect() throws SQLException, ClassNotFoundException {
		Class.forName(driver);
		Connection con = DriverManager.getConnection(dbURL, user, pwd);
		return con;
	}
	
	public void disconnect(Connection con) throws SQLException {
		con.close();
	}

}
