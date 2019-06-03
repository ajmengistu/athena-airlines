package com.airline.athena.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import com.airline.athena.model.ScheduledFlight;
import com.airline.athena.model.forms.FlightSearchForm;
import com.airline.athena.repository.ScheduledFlightRepository;

@Service
public class FlightSearchService {

	@Autowired
	private ScheduledFlightRepository scheduledFlightRepository;

	public void getFlightSearchResults(ModelMap modelMap, FlightSearchForm flightSearchForm) {

		// Get only the Airline code. "LAX - Los Angeles" -> "LAX"
		flightSearchForm.setFrom(flightSearchForm.getFrom().split(" ")[0]);
		flightSearchForm.setTo(flightSearchForm.getTo().split(" ")[0]);

		modelMap.addAttribute("flights", scheduledFlightRepository.findBySourceAndDestAndLocalDepartingDate(
				flightSearchForm.getFrom(), flightSearchForm.getTo(), flightSearchForm.getDepartureDate()));
	}

	public List<ScheduledFlight> getAll() {
		return scheduledFlightRepository.findAll();
	}

	public String getDepartingDate() {
		return "";
	}
}
