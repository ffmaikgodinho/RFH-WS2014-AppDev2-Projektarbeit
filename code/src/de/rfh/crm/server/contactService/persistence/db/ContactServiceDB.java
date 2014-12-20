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
import de.rfh.crm.server.contactService.persistence.util.ConnectionException;

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
		ArrayList<Contact> contacts = getContactByWhere("UUID = ''");
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
		// TODO Auto-generated method stub
		
	}

	@Override
	public void createContact(Contact address) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateContact(Contact address) {
		// TODO Auto-generated method stub
		
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
				contact = new Contact();
				contact.setId(UUID.fromString(res.getString("UUID")));
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
