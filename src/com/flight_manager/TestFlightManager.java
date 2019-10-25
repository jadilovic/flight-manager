/*
 * Flight Manager App
 * By: Jasmin Adilovic
 * E-mail: adilovic79yahoo.com
 * Date: OCT 2019
 * ***Flight manager application***

1. An airport must have a name consisting of exactly three alphabetical
 characters. No two airports can have the same name.
2. An airline has a name that must have less than 6 alphabetic
 characters. No two airlines can have the same name.
3. Each flight consists of seats organized in rows. Each row is labeled
 with ("A", "B", "C", "D", "E", "F"). Each row has number of seats 
in row.
4. Every flight has an airport,airline origin destination and seats.
 
 User can:
 	-Create airport
 	-Create airline
 	-Create flight
 	-Book a seat on a flight
 */

package com.flight_manager;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class TestFlightManager extends SystemManager{
	
	static Scanner input = new Scanner(System.in);

	public static void main(String[] args) throws SQLException {
		
		int option = 0;
		
		while(option != 6){
		System.out.println("\nPlease select option: \n"
				+ "1 to create airport. \n"
				+ "2 to create airline. \n"
				+ "3 to create flight. \n"
				+ "4 to find flight. \n"
				+ "5 to book a seat on a flight. \n"
				+ "6 to exit the application.");
		
		try{
			option = input.nextInt();
		} catch(InputMismatchException ime){
			System.out.println("Wrong data entered. Please try again");
			option = 6;
		}
			switch(option){
			case 1: 
				// Create Airport
				enterAirportName();
				break;
			case 2: 
				// Create Airline
				enterAirlineName();
				break;
			case 3:
				// Create Flight
				enterFlightData();
				break;
			case 4:
				// Find Flight
				enterOriginAndDestination();
				break;
			case 5:
				// Book Seat
				enterDataToBookSeat();
				break;
			case 6:
				break;
			default: 
				System.out.println("Wrong option. Please try again");
			break;
			}
		}
		input.close();
	}

	// CASE 1: Asking for name to create Airport
	private static void enterAirportName() throws SQLException {
		System.out.println("Please enter the name of the Airport");
		String givenName = input.next();
		
		// Creating Airport based on given name
		Airport newAirport = createAirport(givenName.toUpperCase());
		if(newAirport == null)
			System.out.println("Try again");
		else
		System.out.println("New Airport with the name '" + newAirport.getName() + "' was created");
	}
	
	// CASE 2: Asking for name to create Airline
	private static void enterAirlineName() throws SQLException {
		System.out.println("Please enter the name of the Airline");
		String givenName = input.next();
		
		// Creating Airline based on the given name
		Airline newAirline = createAirline(givenName.toUpperCase());
		if(newAirline == null)
			System.out.println("Try again");
		else
		System.out.println("New Airline with the name '" + newAirline.getName() + "' was created");
	}
	
	// CASE 3: Create a Flight
	private static void enterFlightData() throws SQLException {
		
		// Collecting input of initial data to create a flight
		System.out.println("Please enter the name of the Flight");
		String flightName = input.next();
		
		System.out.println("Please enter origin of the Flight");
		String flightOrigin = input.next();
		
		System.out.println("Please enter destination of the Flight");
		String flightDestination = input.next();
		
		System.out.println("Please enter unique ID of the Flight");
		Integer id = input.nextInt();
		
		// Entering initial data collected to create a Flight
		Flight newFlight = createFlight(flightName, flightOrigin, flightDestination, id);
		
		// If entered data is not valid asking user to try again
		if(newFlight == null)
			System.out.println("Try again");
		else{
			// If Entered data is valid showing information about new created Flight
		System.out.println("New Flight with the name '" + newFlight.getFlightName() + "' was created");
		System.out.println(newFlight.toString());
		}
	}
	
	// CASE 4: Finding Flight
	private static void enterOriginAndDestination() throws SQLException {
		
		System.out.println("Please enter origin of the Flight");
		String flightOrigin = input.next();
		System.out.println("Please enter destination of the Flight");
		String flightDestination = input.next();
		
		List<Flight> availableFlightsSQL = findAvailableFlightsInMySQL(flightOrigin, flightDestination);
		
		// Searching for available flights based on entered origin and destination
		List<Flight> availableFlights = findAvailableFlights(flightOrigin, flightDestination);
		
		if(availableFlightsSQL == null)
			System.out.println("There are no available flights for the orgin and destination provided");
		else{
			// Printing available flight for the user to see and choose from
			for(Flight flight: availableFlightsSQL){
				System.out.println("Available flight is " + flight.getFlightName() + " from " + flight.getOrigin() + " to "
						+ "" + flight.getDestination());
			}
		}
	}
	
	// CASE 5: entering initial data to book a seat
	private static void enterDataToBookSeat() {
		
		System.out.println("Please enter Airline name");
		String airlineName = input.next();
		
		System.out.println("Please enter Flight name");
		String flightName = input.next();
		
		// checking for available seats based on the give Flight and Airline
		Flight flight = availableSeats(airlineName, flightName);
		
		// If there is no flight or all seats are taken user needs to try again
		if(flight == null)
			System.out.println("Please try again");
		else{
			
			// If there are available seats on a flight user can choose seat number and row
			System.out.println("Please enter the seat number");
			int seatNumber = input.nextInt();
			
			System.out.println("Please enter the row");
			String row = input.next();
			
			Seat seat = bookSeat(seatNumber, row.toUpperCase(), flight);
			
			// If seat was not booked user needs to try again
			if(seat == null){
				System.out.println("Please try again");
			} else {
				System.out.println("You have booked seat " + seat.toString());
			}
		}
	}
}
