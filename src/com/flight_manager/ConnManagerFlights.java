package com.flight_manager;
/*
 *  * By Jasmin Adilovic
 * Date: NOV2019
 * E-mail: adilovic79@yahoo.com
 * 
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnManagerFlights {

	private static ConnManagerFlights instance = null;
	
	// Connection manager to the database flights
	private static final String USER_NAME = "root";
	private static final String PASSWORD = "password";
	private static final String CONN_DB = "jdbc:mysql://localhost/flights";
	
	private Connection conn = null;
	
	public ConnManagerFlights(){
		
	}
	
	public static ConnManagerFlights getInstance(){
		if(instance == null)
			instance = new ConnManagerFlights();
		return instance;
	}
	
	private boolean openConnection(){
		try {
			conn = DriverManager.getConnection(CONN_DB, USER_NAME, PASSWORD);
			return true;
		} catch (SQLException e) {
			System.err.println(e);
			e.printStackTrace();
			return false;
		}
	}
	
	public Connection getConnection(){
		if(conn == null){
			if(openConnection()){
				System.out.println("Connection established");
				return conn;
			} else{
				return null;
			}
		}
		return conn;
	}
	
	public void close(){
		System.out.println("Connection is closed");
		try{
			conn.close();
			conn = null;
		} catch (Exception e){
			e.printStackTrace();
		}
	}
}
