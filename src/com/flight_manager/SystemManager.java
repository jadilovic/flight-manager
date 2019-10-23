/*
 * Flight Manager App
 * By: Jasmin Adilovic
 * E-mail: adilovic79yahoo.com
 * Date: OCT 2019
 */

package com.flight_manager;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SystemManager extends Flight{
	
	// List of objects that are used in the Flight Manager App
	private static List<Airport> listOfAirports = new ArrayList<>();
	private static List<Flight> listOfFlights = new ArrayList<>();
	private static List<Airline> listOfAirlines = new ArrayList<>();
	
	// Checking conditions before creating Airport name
	public static Airport createAirport(String name) {
		if(airportAlreadyExists(name)){
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
		// Creating Airport if given name meets all criteria
		else{
			Airport airPortName = new Airport(name);
			listOfAirports.add(airPortName);
			return airPortName;
		}
	}
	
	// Checking if given name is all alphabets
	private static boolean onlyAlphabets(String name) {
        return ((name != null) 
                && (!name.equals("")) 
                && (name.matches("^[a-zA-Z]*$")));
	}

	// Checking if given name for Airport already exists before creating new Airport
	private static boolean airportAlreadyExists(String name) {
		if(listOfAirports == null)
			return false;
		for(Airport airport: listOfAirports){
			if(airport.getName().equals(name))
				return true;
		}
		return false;
	}

	// Checking conditions before creating Airline
	public static Airline createAirline(String name) {
		if(airlineAlreadyExists(name)){
			System.out.println("Entered Airline name '" + name + "' already exists");
			return null;
		}
		if(name.length() > 5){
			System.out.println("Length of the Airline name '" + name + "' is greater than 5 characters");
			return null;
		}
		if(!onlyAlphabets(name)){
			System.out.println("Entered Airline name '" + name + "' does not contain all alphabets");
			return null;
		}
		// Creating Airline if given name meets all criteria
		else{
			Airline airLineName = new Airline(name);
			listOfAirlines.add(airLineName);
			return airLineName;
		}
	}
	
	// Checking if the given name of the Airline already exists
	private static boolean airlineAlreadyExists(String name) {
		if(listOfAirlines == null)
			return false;
		for(Airline airline: listOfAirlines){
			if(airline.getName().equals(name))
				return true;
		}
		return false;
	}

	// Before flight is created additional data must be entered by the user
	public static Flight createFlight(String name, String origin, String destination, Integer id) {
		Scanner input = new Scanner(System.in);
		
		System.out.println("Please enter the name of the Airline");
		String airlineName = input.next();
		Airline airline = getAirline(airlineName);
		
		// To create a Flight Airline must exist
		if(airline == null){
			System.out.println("Entered Airline named " + airlineName + " does NOT exist");
			return null;
		}
		
		System.out.println("Please enter the name of the Airport");
		String airportName = input.next();
		Airport airport = getAirport(airportName);
		
		// To create a Flight Airport must exist
		if(airport == null){
			System.out.println("Entered Airport named " + airportName  + " does NOT exist");
			return null;
		}
		
		// Creating seats based on the number of seats per row
		System.out.println("Please enter number of seats per row");
		Integer numSeatsPerRow = input.nextInt();
		ArrayList<Seat> seats = createSeats(numSeatsPerRow);
		
		// Once all needed variables and objects are available Flight is created
		Flight flight = new Flight(id, name, airline, airport, seats, origin, destination);
		listOfFlights.add(flight);
		return flight;
	}
	
	// This method return Airline object based on the String Airline name given
	private static Airline getAirline(String airlineName) {
		for(Airline airline: listOfAirlines){
			if(airline.getName().equals(airlineName))
		return airline;
		}
		return null;
	}

	// This method returns Airport object based on the String Airport name given
	private static Airport getAirport(String airportName) {
		for(Airport airport: listOfAirports){
			if(airport.getName().equals(airportName))
		return airport;
		}
		return null;
	}

	// Checking for available flights based on the entered origin and destination
	public static List<Flight> findAvailableFlights(String origin, String destination){
		List<Flight> availableFlights = new ArrayList<>();
		for(Flight flight: listOfFlights){
			if(flight.getOrigin().equals(origin) && flight.getDestination().equals(destination)){
				availableFlights.add(flight);
			}
		}
		if(availableFlights.size() == 0)
			return null;
		else
		return availableFlights;
	}
	
	// Presentation of availability of seats in the selected Flight
	public static Flight availableSeats(String airline, String flightName) {
		for(Flight flight: listOfFlights){
			if(flight.getAirline().getName().equals(airline) && flight.getFlightName().equals(flightName)){
				ArrayList<Seat> seats = flight.getSeats();
				// Dividing by 6 because there are always 6 rows in a Flight
				int count = seats.size() / 6;
				// Printing all seats to user on a screen to choose from available seats
				for(Seat seat: seats){
					System.out.print(seat.getRow() + ", " + seat.getSeatNumber() + ", " + seat.isAvailable() + "  ");
					count--;
					if(count == 0){
						System.out.println();
						count = seats.size() / 6;
					} 
				}
				return flight;
			}
		}
		System.out.println("No flight exists with specified airline and flight name");
		return null;
	}
	
	// Specifying seat and row in the selected Flight
	public static Seat bookSeat(int seatNumber, String row, Flight flight) {
		ArrayList<Seat> seats = flight.getSeats();
		for(Seat seat: seats){
			if(seat.getSeatNumber() == seatNumber && seat.getRow().equals(row)){
				if(seat.isAvailable()){
					seat.setAvailable(false);
					return seat;
				} else {
					System.out.println("Selected seat " + seatNumber + "" + row + "is not available");
					return null;
				}
			}
		}
		System.out.println("No available seats at the flight " + flight.getFlightName());
		return null;
	}

}
