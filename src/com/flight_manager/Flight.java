/*
 * Flight Manager App
 * By: Jasmin Adilovic
 * E-mail: adilovic79yahoo.com
 * Date: OCT 2019
 */

package com.flight_manager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Flight{
	
	static Connection conn = ConnManagerFlights.getInstance().getConnection();
	
	//unique ID
	private Integer id;
	
	//flight name
	private String flightName;
	
	//which airline owns this flight
	private Airline airline;
	
	//from which airport flight takes of
	private Airport airport;
	
	//all seats in this flight
	private static ArrayList<Seat> seats;
	
	//city where it takes off from
	private String origin;
	
	//city where the flight is going
	private String destination;
	
	public Flight() {
	}
	
	// Overloaded constructor to collect all data needed to create Flight
	public Flight(Integer id, String flightName, Airline airline, Airport airport, ArrayList<Seat> seats,  String origin, String destination){
		this.id = id;
		this.flightName = flightName;
		this.airline = airline;
		this.airport = airport;
		setSeats(seats);
		this.origin = origin;
		this.destination = destination;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Airline getAirline() {
		return airline;
	}

	public void setAirline(Airline airline) {
		this.airline = airline;
	}

	// Method to create seats for the Flight based on the number of seats per row
	public static ArrayList<Seat> createSeats(Integer numberOfSeatsPerRow, String name) throws SQLException {
		ArrayList<Seat> seats = new ArrayList<>();
		String givenTableName = "seats" + name;
		if(tableExists(givenTableName)){
			System.out.println("Seats table with name seats" + name + " already exists.");
			return null;
		}
		String queryTable = "CREATE TABLE seats" + name + " ("
				+ "id INTEGER PRIMARY KEY NOT NULL AUTO_INCREMENT, "
				+ "`row` VARCHAR(1), "
				+ "seat_number INTEGER(11), "
				+ "available TINYINT(1)"
				+ ");";
	
		try{
			Statement statement = conn.createStatement();
			statement.executeUpdate(queryTable);
			System.out.println("Table seats" + name + " was created");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		String queryColumns = "INSERT INTO seats" + name + " (`row`, seat_number, available) VALUES (?, ?, ?)";
		
		String [] rowsSQL = {"A", "B", "C", "D", "E", "F"};
		for(int i = 0; i < rowsSQL.length; i++){
			for(int j = 1; j <= numberOfSeatsPerRow; j++){
				try(PreparedStatement pstat = conn.prepareStatement(queryColumns);){
					pstat.setString(1, rowsSQL[i]);
					pstat.setInt(2, j);
					pstat.setBoolean(3, true);
					pstat.executeUpdate();
				}
			}
		}
		conn.clearWarnings();
		
		Seat seat;
		String [] rows = {"A", "B", "C", "D", "E", "F"};
		for(int a = 0; a < rows.length; a++){
			for(int b = 1; b <= numberOfSeatsPerRow; b++){
				seat = new Seat(rows[a], b, true);
				seats.add(seat);
			}
		}
		return seats;
	}
	
	private static boolean tableExists(String givenTableName) throws SQLException {
		String sqlQuery = "SHOW TABLES";
		String test1 = null;
		ResultSet rs = null;
		try(Statement stat = conn.createStatement();){
			rs = stat.executeQuery(sqlQuery);
			while(rs.next()){
				test1 = rs.getString("Tables_in_flights");
				if(test1.equals(givenTableName))
					return true;
			}
			return false;
		}
	}

	public static void setSeats(ArrayList<Seat> newSeats) {
		seats = newSeats;
	}
	
	public ArrayList<Seat> getSeats(){
		return seats;
	}

	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}
	
	public Airport getAirport() {
		return airport;
	}

	public void setAirport(Airport airport) {
		this.airport = airport;
	}

	/**
	 * @return the flightName
	 */
	public String getFlightName() {
		return flightName;
	}

	/**
	 * @param flightName the flightName to set
	 */
	public void setFlightName(String flightName) {
		this.flightName = flightName;
	}

	@Override
	public String toString() {
		return "Flight [id=" + id + ", airline=" + airline + ", seats=" + seats + ", origin=" + origin
				+ ", destination=" + destination + ", flight name=" + flightName + "]";
	}
	
	

}
