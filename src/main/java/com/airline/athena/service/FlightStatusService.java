package com.airline.athena.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import com.airline.athena.model.FlightStatus;
import com.airline.athena.model.forms.FlightStatusForm;
import com.airline.athena.repository.FlightStatusRepository;
import com.airline.athena.repository.ScheduledFlightRepository;

@Service
public class FlightStatusService {
	@Autowired
	private FlightStatusRepository flightStatusRepository;
	@Autowired
	private ScheduledFlightRepository scheduledFlightRepository;

	public void getFlightStatus(FlightStatusForm flightStatusForm, ModelMap modelMap) {
		FlightStatus flightStatus = flightStatusRepository.findByFlightId(flightStatusForm.getFlightNumber());
		System.out.println(flightStatus.getStatus());
		modelMap.put("flight", flightStatus);
		modelMap.put("block", "block");
		modelMap.put("selectedFlight", scheduledFlightRepository.getOne(flightStatusForm.getFlightNumber()));
	}
}
