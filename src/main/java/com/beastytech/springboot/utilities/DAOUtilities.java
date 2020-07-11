package com.beastytech.springboot.utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class DAOUtilities {
	@Autowired
	private DbSettings dbSettings;
	
	private static Connection connection;
	
	public synchronized Connection getConnection() throws SQLException {
		//System.out.println("Username: " + dbSettings.getUsername() + " Password: " + dbSettings.getPassword());
		if (connection == null) {
			try {
				Class.forName("org.postgresql.Driver");
			} catch (ClassNotFoundException e) {
				System.out.println("Could not register driver!");
				e.printStackTrace();
			}
			connection = DriverManager.getConnection(dbSettings.getUrl() , dbSettings.getUsername(), dbSettings.getPassword());
		}
		
		//If connection was closed then retrieve a new connection
		if (connection.isClosed()){
			System.out.println("Opening new connection...");
			connection = DriverManager.getConnection(dbSettings.getUrl() , dbSettings.getUsername(), dbSettings.getPassword());
		}
		return connection;
	}
	
}
