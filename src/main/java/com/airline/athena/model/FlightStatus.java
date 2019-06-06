package com.airline.athena.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name="flight_status")
public class FlightStatus {
	@Id
	@Column(length = 6, nullable = false, unique = true, updatable = true)
	private String flightId;

	@Column(nullable = false)
	private Date scheduled;

	@Column(nullable = false)
	private Date actual;

	@Column(nullable = false)
	private FlightStatus status;

	public String getFlightId() {
		return flightId;
	}

	public void setFlightId(String flightId) {
		this.flightId = flightId;
	}

	public Date getScheduled() {
		return scheduled;
	}

	public void setScheduled(Date scheduled) {
		this.scheduled = scheduled;
	}

	public Date getActual() {
		return actual;
	}

	public void setActual(Date actual) {
		this.actual = actual;
	}

	public FlightStatus getStatus() {
		return status;
	}

	public void setStatus(FlightStatus status) {
		this.status = status;
	}
}
