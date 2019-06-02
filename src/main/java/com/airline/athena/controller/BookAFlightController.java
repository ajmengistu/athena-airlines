package com.airline.athena.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.airline.athena.model.Airport;
import com.airline.athena.model.FlightSearchForm;
import com.airline.athena.repository.AirportRepository;

@Controller
public class BookAFlightController {
	@Autowired
	private AirportRepository airportRepository;

	@GetMapping("/airports")
	public @ResponseBody List<String> getAirports() {
		List<String> airportIds = new ArrayList<>();
		List<Airport> airports = airportRepository.findAll();

		for (Airport airport : airports) {
			airportIds.add(airport.getAirportId() + " - " + airport.getCity());
		}

		return airportIds;
	}

	@GetMapping("/")
	public String getHomeAndSearchForFlights(FlightSearchForm flightSearchForm) {
		return "home";
	}

	@GetMapping("/search-flights/book-a-flight")
	public String submitFlightForm(@Valid FlightSearchForm flightSearchForm, BindingResult bindingResult, Model model,
			ModelMap modelMap, HttpServletRequest request) {

		if (bindingResult.hasErrors()) {
			System.out.println("--------------------------Error-----------------------------");
			System.out.println(bindingResult.getFieldError());
			return "home";
		}
		System.out.println("------------------------------Success---------------------------");

		System.out.println(flightSearchForm);
		System.out.println("Flight Method: " + request.getParameter("flightMethod"));

		return "flight-search-results";
	}
}
