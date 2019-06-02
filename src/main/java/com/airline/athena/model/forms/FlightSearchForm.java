package com.airline.athena.model.forms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotBlank;

import com.airline.athena.model.enums.FlightMethod;
import com.airline.athena.model.enums.SeatType;

public class FlightSearchForm {
	private static List<SeatType> seatTypes = new ArrayList<SeatType>(Arrays.asList(SeatType.values()));

	public FlightMethod flightMethod;
	@NotBlank(message = "*Please enter a valid origin.")
	private String from;
	@NotBlank(message = "*Please enter a valid destination.")
	private String to;
	private Date departureDate; // Default: current date
	private Date returnDate; // Default: one-week from current date
	private Integer numPassengers; // Default: 1 Adult

	public String getFrom() {
		return from;
	}

	public String getTo() {
		return to;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public Date getDepartureDate() {
		return departureDate;
	}

	public void setDepartureDate(Date departureDate) {
		this.departureDate = departureDate;
	}

	public List<SeatType> getSeatTypes() {
		return seatTypes;
	}

	public Integer getNumPassengers() {
		return numPassengers;
	}

	public void setNumPassengers(Integer numTravelers) {
		this.numPassengers = numTravelers;
	}

	public Date getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}
	
	public FlightMethod getFlightMethod() {
		return flightMethod;
	}
	
	public void setFlightMethod(FlightMethod flightMethod) {
		this.flightMethod = flightMethod;
	}

	@Override
	public String toString() {
		return "[From: " + this.from + ", To: " + this.to + ", departureDate: " + this.departureDate + ", Passengers: "
				+ this.numPassengers + ", returnDate: " + this.returnDate + "]";

	}
}
