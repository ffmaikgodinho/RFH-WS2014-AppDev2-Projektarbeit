package de.rfh.crm.server.contactService.persistence.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.UUID;

import de.rfh.crm.server.connectionManager.ConnectionFactory;
import de.rfh.crm.server.connectionManager.boundary.DefaultConnection;
import de.rfh.crm.server.contactService.boundary.ContactServicePersistence;
import de.rfh.crm.server.contactService.entity.Address;
import de.rfh.crm.server.contactService.entity.Contact;

public class ContactServiceDB implements ContactServicePersistence {
	
	private Connection connection;
	
	public ContactServiceDB()  {
		try {
			DefaultConnection defaultConnection = ConnectionFactory.getMySqlDefaultConnection();
			this.connection = defaultConnection.connect();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public Contact getContact(UUID id) {
		ArrayList<Contact> contacts = getContactByWhere("UUID = '" + id.toString() + "'");
		if (contacts.size() == 0)  {
			return null;
		}
		else if (contacts.size() == 1)  {
			return contacts.get(0);
		}
		else {
			return null;	//wenn mehr als 1 Ergebnis zurück kommt, sicherheitshalber null zurückgeben.
		}
	}

	@Override
	public ArrayList<Contact> getContacts(String searchCriteria) {
		///TODO Refactor, string builder
		return getContactByWhere("firstname LIKE '%" + searchCriteria + "%' OR lastname LIKE '%" + searchCriteria + "%' OR city LIKE '%" + searchCriteria + "%'");
	}
	
	@Override
	public void deleteContact(UUID id) {
		try {
			String sql = "DELETE FROM contact WHERE UUID='" + id + "'";
			java.sql.CallableStatement pStat = connection.prepareCall(sql);
			Boolean blnResult = pStat.execute();
			if (!blnResult)  {
				//weg loggen und exception werfen
			}
		} catch (SQLException e) {
			//weg loggen und exception werfen
			e.printStackTrace();
		}
		
	}

	@Override
	public void createContact(Contact contact) {
		Address ad = contact.getAddress();
		StringBuilder strSQL = new StringBuilder();
		strSQL.append("Insert INTO contact ");
		strSQL.append("(");
		strSQL.append("UUID, ");
		strSQL.append("FirstName, ");
		strSQL.append("LastName, ");
		strSQL.append("Street, ");
		strSQL.append("ZipCode, ");
		strSQL.append("City, ");
		strSQL.append("Country");
		strSQL.append(")");
		strSQL.append("Values ");
		strSQL.append("(");
		strSQL.append("'" + contact.getId() + "',");
		strSQL.append("'" + contact.getFirstName() + "',");
		strSQL.append("'" + contact.getLastName() + "',");
		strSQL.append("'" + (ad != null ? ad.getStreet() : "") + "',");
		strSQL.append("'" + (ad != null ? ad.getZipcode() : "0") + "',");
		strSQL.append("'" + (ad != null ? ad.getCity() : "") + "',");
		strSQL.append("'" + (ad != null ? ad.getCountry() : "") + "'");
		strSQL.append(")");
		try {
			java.sql.CallableStatement pStat = connection.prepareCall(strSQL.toString());
			Boolean blnResult = pStat.execute();			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void updateContact(Contact contact) {
		StringBuilder strSQL = new StringBuilder();
		strSQL.append("UPDATE contact ");
		strSQL.append("SET ");
		strSQL.append("FirstName = '" + contact.getFirstName() + "',");
		strSQL.append("LastName = '" + contact.getLastName() + "'");
		if (contact.getAddress() != null)  {
			strSQL.append(", ");
			strSQL.append("Street = '" + contact.getAddress().getStreet() + "',");
			strSQL.append("ZipCode = '" + contact.getAddress().getZipcode() + "',");
			strSQL.append("City = '" + contact.getAddress().getCity() + "',");
			strSQL.append("Country = '" + contact.getAddress().getCountry() + "'");
		}
		strSQL.append(" WHERE UUID = '" + contact.getId() + "'");
		
		try {
			java.sql.CallableStatement pStat = connection.prepareCall(strSQL.toString());
			Boolean blnResult = pStat.execute();	
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private ArrayList<Contact> getContactByWhere(String strWhere)  {
		if (strWhere == "") {
			strWhere = "1=1";	//damit es nicht zu SQL Fehlern kommt.
		}
		ArrayList<Contact> list = new ArrayList<Contact>();
		Contact contact = null;
		try {
			String sql = "SELECT * FROM contact WHERE " + strWhere;
			PreparedStatement pStat = connection.prepareStatement(sql);
			ResultSet res = pStat.executeQuery();
			while(res.next()) {
				contact = new Contact(UUID.fromString(res.getString("UUID")));
				contact.setFirstName(res.getString("FirstName"));
				contact.setLastName(res.getString("LastName"));
				Address ad = new Address();
				ad.setCity(res.getString("City"));
				ad.setCountry(res.getString("Country"));
				ad.setStreet(res.getString("Street"));
				ad.setZipcode(res.getString("ZipCode"));
				contact.setAddress(ad);
				list.add(contact);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}
}
