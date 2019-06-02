package com.airline.athena.repository;

import java.util.Date;
import java.util.List;

import com.airline.athena.model.ScheduledFlight;

public interface ScheduledFlightRepository {
	public List<ScheduledFlight> findBySourceAndDestAndLocalDepartingDate(String source, String dest,
			Date localDepartingTime);

	public List<ScheduledFlight> findBySourceAndDest(String source, String dest);

	public ScheduledFlight findByFlightId(String flightId);
}
