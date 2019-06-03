package com.airline.athena.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import com.airline.athena.repository.FlightCostRepository;

@Service
public class FlightCostService {
	@Autowired
	private FlightCostRepository flightCostRepository;

	public void getFlightCosts(ModelMap modelMap) {
		modelMap.addAttribute("airfare", flightCostRepository.findAll());
	}
}
