package com.airline.athena.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.airline.athena.model.enums.FlightMethod;
import com.airline.athena.model.forms.FlightSearchForm;
import com.airline.athena.service.AirportService;
import com.airline.athena.service.FlightCostService;
import com.airline.athena.service.FlightSearchService;

@Controller
public class BookAFlightController {
	@Autowired
	private FlightSearchService flightSearchService;
	@Autowired
	private AirportService airportService;
	@Autowired
	private FlightCostService flightCostService;

	@GetMapping("/airports")
	public @ResponseBody List<String> getAirports() {
		return airportService.getAll();
	}

//	@GetMapping("/scheduledFlights")
//	public @ResponseBody List<ScheduledFlight> getAll() {
//		return flightSearchService.getAll();
//	}

	@GetMapping("/")
	public String getHomeAndSearchForFlights(FlightSearchForm flightSearchForm) {
		return "home";
	}

	@GetMapping("/search-flights/book-a-flight")
	public String submitFlightForm(@Valid FlightSearchForm flightSearchForm, BindingResult bindingResult,
			ModelMap modelMap, HttpServletRequest request) {

		if (bindingResult.hasErrors()) {
			System.out.println("--------------------------Error-----------------------------");
			System.out.println(bindingResult.getFieldError());
			return "home";
		}

		System.out.println("------------------------------Success---------------------------");

		flightSearchForm.setFlightMethod((FlightMethod.valueOf(request.getParameter("flightMethod"))));
		flightSearchService.getFlightSearchResults(modelMap, flightSearchForm);
		flightCostService.getFlightCosts(modelMap);
		airportService.getDepartingCityAirportAndArrivalCityAirport(flightSearchForm, modelMap);

		System.out.println(flightSearchForm);

		return "flight-search-results";
	}

}
