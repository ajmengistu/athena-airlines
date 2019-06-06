package com.airline.athena.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
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
}
