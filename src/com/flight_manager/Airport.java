/*
 * Flight Manager App
 * By: Jasmin Adilovic
 * E-mail: adilovic79yahoo.com
 * Date: OCT 2019
 */

package com.flight_manager;

public class Airport {
	
	private String name;
	
	
	public Airport() {
	}
	
	public Airport(String name) {
		setName(name);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Airport [name=" + name + "]";
	}
	
	

}
