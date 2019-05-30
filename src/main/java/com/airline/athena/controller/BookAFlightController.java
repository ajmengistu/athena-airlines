package com.airline.athena.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.airline.athena.model.Airport;
import com.airline.athena.repository.AirportRepository;

@Controller
public class BookAFlightController {
	@Autowired
	private AirportRepository airportRepository;

	@GetMapping("/home")
	public @ResponseBody List<Airport> getAirports() {
		return airportRepository.findAll();
	}
}
