package com.flight_manager;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TestFlightManager extends SystemManager{

	public static void main(String[] args) {
		
		Scanner input = new Scanner(System.in);
		int option = 0;
		
		while(option != 6){
		System.out.println("\nPlease select option: \n"
				+ "1 to create airport. \n"
				+ "2 to create airline. \n"
				+ "3 to create flight. \n"
				+ "4 to find flight. \n"
				+ "5 to book a seat on a flight. \n"
				+ "6 to exit the application.");
		
			option = input.nextInt();
		
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

	private static void enterDataToBookSeat() {
		Scanner input = new Scanner(System.in);
		System.out.println("Please enter Airline name");
		String airlineName = input.next();
		System.out.println("Please enter Flight name");
		String flightName = input.next();
		
		Flight flight = selectSeat(airlineName, flightName);
		
		System.out.println("Please enter the seat number");
		int seatNumber = input.nextInt();
		System.out.println("Please enter the row");
		String row = input.next();
		Seat seat = bookSeat(seatNumber, row, flight);
		
		System.out.println("You have booked seat " + seat.toString());
	}

	private static void enterOriginAndDestination() {
		Scanner input = new Scanner(System.in);
		List<Flight> availableFlights = new ArrayList<>();
		
		System.out.println("Please enter origin of the Flight");
		String flightOrigin = input.next();
		System.out.println("Please enter destination of the Flight");
		String flightDestination = input.next();
		availableFlights = findAvailableFlights(flightOrigin, flightDestination);
		if(availableFlights == null)
			System.out.println("There are no available flights for the orgin and destination provided");
		else{
			for(Flight flight: availableFlights){
				System.out.println("Available flight is " + flight.getFlightName() + " from " + flight.getOrigin() + " to "
						+ "" + flight.getDestination());
			}
		}
	}

	// Selection 3 to create a Flight
	private static void enterFlightData() {
		Scanner input = new Scanner(System.in);
		
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
		
		// If entered data is not valide asking user to try again
		if(newFlight == null)
			System.out.println("Try again");
		else{
			// If Entered data is valid showing information about new created Flight
		System.out.println("New Flight with the name '" + newFlight.getFlightName() + "' was created");
		System.out.println(newFlight.toString());
		}
	}

	// Asking for name to create Airline
	private static void enterAirlineName() {
		Scanner input = new Scanner(System.in);
		System.out.println("Please enter the name of the Airline");
		String givenName = input.nextLine();
		
		// Creating Airline based on the given name
		Airline newAirline = createAirline(givenName);
		if(newAirline == null)
			System.out.println("Try again");
		else
		System.out.println("New Airline with the name '" + newAirline.getName() + "' was created");
	}

	// Asking for name to create Airport
	private static void enterAirportName() {
		Scanner input = new Scanner(System.in);
		System.out.println("Please enter the name of the Airport");
		String givenName = input.nextLine();
		
		// Creating Airport based on given name
		Airport newAirport = createAirport(givenName);
		if(newAirport == null)
			System.out.println("Try again");
		else
		System.out.println("New Airport with the name '" + newAirport.getName() + "' was created");
	}
}
