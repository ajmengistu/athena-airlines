package com.airline.athena.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity(name = "seating_numbers")
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = { "flightId", "seatType", "seatNumber" }) })
public class SeatNumber {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "seat_number_id", updatable = true, nullable = false)
	private Long id;

	@Column(length = 6, nullable = false)
	private String flightId;

	@Column(length = 15, nullable = false)
	private String seatType;

	@Column(length = 8, nullable = true)
	private String seatNumber;

	@Column(nullable = false)
	private Boolean seatTaken;

	public SeatNumber() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFlightId() {
		return flightId;
	}

	public void setFlightId(String flightId) {
		this.flightId = flightId;
	}

	public String getSeatType() {
		return seatType;
	}

	public void setSeatType(String seatType) {
		this.seatType = seatType;
	}

	public String getSeatNumber() {
		return seatNumber;
	}

	public void setSeatNumber(String seatNumber) {
		this.seatNumber = seatNumber;
	}

	public Boolean getSeatTaken() {
		return seatTaken;
	}

	public void setSeatTaken(Boolean seatTaken) {
		this.seatTaken = seatTaken;
	}
}
