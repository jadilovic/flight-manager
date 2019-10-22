package com.flight_manager;

import java.util.ArrayList;
import java.util.List;

public class Airline {
	
	private String name;
	private List<String> names = new ArrayList<>();
	
	public Airline(){
		
	}
	
	public Airline(String name){
		setName(name);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
		names.add(name);
	}

	@Override
	public String toString() {
		return "Airline [name=" + name + "]";
	}
	
	

}
