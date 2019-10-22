package com.flight_manager;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SystemManager extends Flight{
	
	private static List<Airport> listOfAirports = new ArrayList<>();
	private static List<Flight> listOfFlights = new ArrayList<>();
	private static List<Airline> listOfAirlines = new ArrayList<>();
	
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

	private static boolean airportAlreadyExists(String name) {
		if(listOfAirports == null)
			return false;
		for(Airport airport: listOfAirports){
			if(airport.getName().equals(name))
				return true;
		}
		return false;
	}

	// CREATE AIRLINE
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
		else{
			Airline airLineName = new Airline(name);
			listOfAirlines.add(airLineName);
			return airLineName;
		}
	}
	
	private static boolean airlineAlreadyExists(String name) {
		if(listOfAirlines == null)
			return false;
		for(Airline airline: listOfAirlines){
			if(airline.getName().equals(name))
				return true;
		}
		return false;
	}

	public static Flight createFlight(String name, String origin, String destination, Integer id) {
		System.out.println("Please enter the name of the Airline");
		Scanner input = new Scanner(System.in);
		String airlineName = input.next();
		Airline airline = getAirline(airlineName);
		if(airline == null){
			System.out.println("Entered Airline named " + airlineName + " does NOT exist");
			return null;
		}
		
		System.out.println("Please enter the name of the Airport");
		String airportName = input.next();
		Airport airport = getAirport(airportName);
		if(airport == null){
			System.out.println("Entered Airport named " + airportName  + " does NOT exist");
			return null;
		}
		
		System.out.println("Please enter number of seats per row");
		Integer numSeatsPerRow = input.nextInt();
		ArrayList<Seat> seats = createSeats(numSeatsPerRow);
		
		Flight flight = new Flight(id, name, airline, airport, seats, origin, destination);
		listOfFlights.add(flight);
		return flight;
	}
	
	private static Airline getAirline(String airlineName) {
		for(Airline airline: listOfAirlines){
			if(airline.getName().equals(airlineName))
		return airline;
		}
		return null;
	}

	private static Airport getAirport(String airportName) {
		for(Airport airport: listOfAirports){
			if(airport.getName().equals(airportName))
		return airport;
		}
		return null;
	}

	public static List<Flight> findAvailableFlights(String origin, String destination){
		List<Flight> availableFlights = new ArrayList<>();
		for(Flight flight: listOfFlights){
			if(flight.getOrigin().equals(origin) && flight.getDestination().equals(destination)){
				availableFlights.add(flight);
			}
		}
		return availableFlights;
	}
	
	public static Flight selectSeat(String airline, String flightName) {
		for(Flight flight: listOfFlights){
			if(flight.getAirline().getName().equals(airline) && flight.getFlightName().equals(flightName)){
				ArrayList<Seat> seats = flight.getSeats();
				int count = seats.size() / 6;
				for(Seat seat: seats){
					System.out.print(seat.getRow() + ", " + seat.getSeatNumber() + ", " + seat.isAvailable() + "  ");
					count--;
					if(count == 0){
						System.out.println();
						count = seats.size() / 6;
					} 
				}
				return flight;
			} else {
				System.out.println("No flight exists with specified airline and flight name");
				return null;
			}
		}
		return null;
	}
	
	public static Seat bookSeat(int seatNumber, String row, Flight flight) {
		ArrayList<Seat> seats = flight.getSeats();
		for(Seat seat: seats){
			if(seat.getSeatNumber() == seatNumber && seat.getRow().equals(row)){
				seat.setAvailable(false);
				return seat;
			}
		}
		return null;
	}

}
