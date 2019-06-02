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

	public boolean getFlightSearchResults(ModelMap modelMap, FlightSearchForm flightSearchForm) {

		modelMap.addAttribute("flight", scheduledFlightRepository.findBySourceAndDestAndLocalDepartingDate(
				flightSearchForm.getFrom(), flightSearchForm.getTo(), flightSearchForm.getDepartureDate()));
//		modelMap.addAttribute("airfare", )

		return false;
	}

	public List<ScheduledFlight> getAll() {
		return scheduledFlightRepository.findAll();
	}
}
