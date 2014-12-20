package de.rfh.crm.server.connectionManager.controller.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import de.rfh.crm.server.connectionManager.boundary.DefaultConnection;

public class DefaultConnectionMySql implements DefaultConnection{
	private String driver = "com.mysql.jdbc.Driver";
	private String dbURL = "jdbc:mysql://localhost/crm?user=root&password=";
	
	public Connection connect() throws SQLException, ClassNotFoundException {
		Class.forName(driver);
		Connection con = DriverManager.getConnection(dbURL);
		return con;
	}
	
	public void disconnect(Connection con) throws SQLException {
		con.close();
	}

}
