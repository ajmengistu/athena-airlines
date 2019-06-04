package com.airline.athena.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.airline.athena.model.enums.FlightMethod;
import com.airline.athena.model.enums.SeatType;
import com.airline.athena.model.forms.FlightSearchForm;
import com.airline.athena.service.AirportService;
import com.airline.athena.service.FlightCostService;
import com.airline.athena.service.FlightSearchService;

@Controller
@SessionAttributes({ "selectedNumPassengers", "selectedFlightId", "selectedFlightMethod" })
public class BookAFlightController {
	@Autowired
	private FlightSearchService flightSearchService;
	@Autowired
	private AirportService airportService;
	@Autowired
	private FlightCostService flightCostService;

	// DO NOT REMOVE THIS: needed for front-end auto-complete jQuery request.
	@GetMapping("/airports")
	public @ResponseBody List<String> getAirports() {
		return airportService.getAll();
	}

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

	@GetMapping("/search-flights/book-a-flight/your-trip-summary")
	public String showTripSummary(ModelMap modelMap, @RequestParam String chosenFlightId,
			@RequestParam String seatType) {
		SeatType seatType2 = SeatType.valueOf(seatType.toUpperCase());
		flightSearchService.showTripSummary(modelMap, seatType2, chosenFlightId);
		flightCostService.showTripSummary(modelMap, seatType2);

		return "your-trip-summary";
	}

	@PostMapping("/search-flights/book-a-flight/review-and-pay")
	public String submitPassengerForm(ModelMap modelMap, HttpServletRequest request) {
		flightSearchService.submitPassengerForm(modelMap, request);
		return "review-and-pay";
	}

}
