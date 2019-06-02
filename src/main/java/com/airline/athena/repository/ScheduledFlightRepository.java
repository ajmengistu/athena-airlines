package com.airline.athena.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.airline.athena.model.ScheduledFlight;

@Repository
public interface ScheduledFlightRepository extends JpaRepository<ScheduledFlight, String>{
	public List<ScheduledFlight> findBySourceAndDestAndLocalDepartingDate(String source, String dest,
			Date localDepartingTime);

	public List<ScheduledFlight> findBySourceAndDest(String source, String dest);

	public ScheduledFlight findByFlightId(String flightId);
}
