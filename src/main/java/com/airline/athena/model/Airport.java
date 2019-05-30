package com.airline.athena.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name="airports")
public class Airport {
	@Id
	@Column(length = 3, nullable = false, unique = true)
	private String airportId;

	@Column(length = 20, nullable = false)
	private String city;

	@Column(length = 100, nullable = false)
	private String name;

	public Airport() {		
	}
	
	public String getAirportId() {
		return airportId;
	}

	public void setAirportId(String airportId) {
		this.airportId = airportId;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
