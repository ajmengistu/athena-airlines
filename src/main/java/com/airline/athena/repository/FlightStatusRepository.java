package com.airline.athena.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.airline.athena.model.FlightStatus;

public interface FlightStatusRepository extends JpaRepository<FlightStatus, String> {
	public FlightStatus findByFlightId(String flightId);
}
