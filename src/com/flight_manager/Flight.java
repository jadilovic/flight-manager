package com.flight_manager;

import java.util.ArrayList;

public class Flight extends Airline{
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

	public static ArrayList<Seat> createSeats(Integer numberOfSeatsPerRow) {
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
		return seats;
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
