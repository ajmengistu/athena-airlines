package com.airline.athena.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotBlank;

public class FlightSearchForm {
	private static List<SeatType> seatTypes = new ArrayList<SeatType>(Arrays.asList(SeatType.values()));

	private Boolean roundTrip;
	private Boolean oneWay;
	@NotBlank(message = "*Please enter a valid origin.")
	private String from;
	@NotBlank(message = "*Please enter a valid destination.")
	private String to;
	private String departureDate; // Default: current date
	private Date returnDate; // Default: one-week from current date
	private Integer numTravelers; // Default: 1 Adult
	private SeatType seatType; // Default: Economy

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

	public String getDepartureDate() {
		return departureDate;
	}

	public void setDepartureDate(String departureDate) {
		this.departureDate = departureDate;
	}

	public SeatType getSeatType() {
		return seatType;
	}

	public void setSeatType(SeatType seatType) {
		this.seatType = seatType;
	}

	public List<SeatType> getSeatTypes() {
		return seatTypes;
	}

	public Integer getNumTravelers() {
		return numTravelers;
	}

	public void setNumTravelers(Integer numTravelers) {
		this.numTravelers = numTravelers;
	}

	@Override
	public String toString() {
		return "[From: " + this.from + ", To: " + this.to + ", dDate: " + this.departureDate + ", Travelers: "
				+ this.numTravelers + ", Seat: " + this.seatType + "]";

	}
}
