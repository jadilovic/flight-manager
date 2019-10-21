package com.flight_manager;

import java.util.Scanner;

public class TestFlightManager extends SystemManager{

	public static void main(String[] args) {
		
		Scanner input = new Scanner(System.in);
		int option = 0;
		
		while(option != 5){
		System.out.println("\nPlease select option: \n"
				+ "1 to create airport. \n"
				+ "2 to create airline. \n"
				+ "3 to create flight. \n"
				+ "4 to book a seat on a flight. \n"
				+ "5 to exit the application.");
		
			option = input.nextInt();
		
			switch(option){
			case 1: 
				enterAirportName();
			break;
			case 2: 
				enterAirlineName();
			break;
			case 5:
				break;
			default: 
				System.out.println("Wrong option. Please try again");
			break;
			}
		}
		input.close();
	}

	private static void enterAirlineName() {
		// TODO Auto-generated method stub
		
	}

	private static void enterAirportName() {
		Scanner input = new Scanner(System.in);
		System.out.println("Please enter the name of the airport");
		String givenName = input.nextLine();
		Airport newAirport = createAirport(givenName);
		if(newAirport == null)
			System.out.println("Try again");
		else
		System.out.println("New airport with the name '" + newAirport.getName() + "' was created");
	}
}
