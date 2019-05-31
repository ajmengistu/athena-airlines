package com.airline.athena.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.airline.athena.model.Airport;
import com.airline.athena.model.FlightSearchForm;
import com.airline.athena.repository.AirportRepository;
//import com.airline.infinity.model.FlightForm;

@Controller
public class BookAFlightController {
	@Autowired
	private AirportRepository airportRepository;

	@GetMapping("/airports")
	public @ResponseBody List<Airport> getAirports() {
		return airportRepository.findAll();
	}
	
	@GetMapping("/")
	public String getHome(FlightSearchForm flightSearchForm) {
		return "home";
	}
}