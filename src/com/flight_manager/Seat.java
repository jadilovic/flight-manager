package com.flight_manager;

public class Seat {
	// row ("A", "B", "C", "D", "E", "F")
	private String row;
	
	// seat number ex. 1,5,16
	private Integer seatNumber;
	
	//is it booked, or it is available
	private boolean available = true;
	
	public Seat() {
		
	}
	
	public Seat(String row, Integer seatNumber, boolean availabe){
		setRow(row);
		setSeatNumber(seatNumber);
		setAvailable(available);
	}
	
	public String getRow() {
		return row;
	}
	public void setRow(String row) {
		this.row = row;
	}
	public Integer getSeatNumber() {
		return seatNumber;
	}
	public void setSeatNumber(Integer seatNumber) {
		this.seatNumber = seatNumber;
	}
	public boolean isAvailable() {
		return available;
	}
	public void setAvailable(boolean available) {
		this.available = available;
	}

	@Override
	public String toString() {
		return "\nSeat [row=" + row + ", seatNumber=" + seatNumber + ", available=" + available + "]";
	}
	
	
	

}
