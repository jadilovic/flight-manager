package com.flight_manager;

import java.util.ArrayList;
import java.util.List;

public class SystemManager extends Flight{
	
	private static List<Airport> listOfAirports = new ArrayList<>();
	private List<Flight> listOfFlights;
	private List<Airline> listOfAirlines;
	
	public static Airport createAirport(String name) {
		if(alreadyExists(name)){
			System.out.println("Entered airport name '" + name + "' already exists");
			return null;
		}
		if(name.length() != 3){
			System.out.println("Length of the Airport name '" + name + "' is not 3 characters");
			return null;
		}
		if(!onlyAlphabets(name)){
			System.out.println("Entered airport name '" + name + "' does not contain all alphabets");
			return null;
		}
		else{
			Airport airPortName = new Airport(name);
			listOfAirports.add(airPortName);
			return airPortName;
		}
	}
	
	private static boolean onlyAlphabets(String name) {
        return ((name != null) 
                && (!name.equals("")) 
                && (name.matches("^[a-zA-Z]*$")));
	}

	private static boolean alreadyExists(String name) {
		if(listOfAirports == null)
			return false;
		for(Airport airport: listOfAirports){
			if(airport.getName().equals(name))
				return true;
		}
		return false;
	}

	public Airline createAirline(String name) {
		// TODO implement
		return null;
	}
	
	public Flight createFlight(String airlaneName, String origin, String destination, Integer id) {
		
		return null;
	}
	
	public void createSeats(String airline,Integer flightID, Integer numberOfSeatsPerRow) {
		ArrayList<Seat> seats = new ArrayList<>();
		Seat seat;
		String [] rows = {"A", "B", "C", "D", "E", "F"};
		for(int i = 0; i < rows.length; i++){
			for(int j = 1; j <= numberOfSeatsPerRow; j++){
				seat = new Seat(rows[i], j, true);
				seats.add(seat);
			}
		}
		setSeats(seats);
	}
	
	public List<Flight> findAvailableFlights(String origin, String destination){
		// TODO implement
		return null;
	}
	
	public boolean bookSeat(String airline, String flightName,int seatNumber,String row) {
		// TODO implement
		return false;
	}

}
