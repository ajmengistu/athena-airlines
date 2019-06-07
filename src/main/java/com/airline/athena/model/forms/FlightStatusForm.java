package com.airline.athena.model.forms;

import java.util.Date;

public class FlightStatusForm {

	private String from;
	private String to;
	private String AirportCity;
	private Date date;
	private String flightNumber;

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getAirportCity() {
		return AirportCity;
	}

	public void setAirportCity(String airportCity) {
		AirportCity = airportCity;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getFlightNumber() {
		return flightNumber;
	}

	public void setFlightNumber(String flightNumber) {
		this.flightNumber = flightNumber;
	}

}
