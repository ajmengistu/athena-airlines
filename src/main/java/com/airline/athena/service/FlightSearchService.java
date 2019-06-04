package com.airline.athena.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import com.airline.athena.model.ScheduledFlight;
import com.airline.athena.model.enums.SeatType;
import com.airline.athena.model.forms.FlightSearchForm;
import com.airline.athena.repository.ScheduledFlightRepository;

@Service
public class FlightSearchService {

	@Autowired
	private ScheduledFlightRepository scheduledFlightRepository;
	@Autowired
	private AirportService airportService;

	public void getFlightSearchResults(ModelMap modelMap, FlightSearchForm flightSearchForm) {

		// Get only the Airline code. "LAX - Los Angeles" -> "LAX"
		flightSearchForm.setFrom(flightSearchForm.getFrom().split(" ")[0]);
		flightSearchForm.setTo(flightSearchForm.getTo().split(" ")[0]);

		modelMap.addAttribute("flights", scheduledFlightRepository.findBySourceAndDestAndLocalDepartingDate(
				flightSearchForm.getFrom(), flightSearchForm.getTo(), flightSearchForm.getDepartureDate()));
		modelMap.put("formatedDepartureDate", this.getDepartureDate(flightSearchForm));
		modelMap.put("selectedNumPassengers", flightSearchForm.getNumPassengers());
		modelMap.put("selectedFlightMethod", flightSearchForm.getFlightMethod().toString());
	}

	public List<ScheduledFlight> getAll() {
		return scheduledFlightRepository.findAll();
	}

	public String getDepartureDate(FlightSearchForm flightSearchForm) {
		return this.formatDepartureDate(flightSearchForm.getDepartureDate());
	}

	public String formatDepartureDate(Date dateIn) {
		return new SimpleDateFormat("EEEE, MMMM dd, yyyy").format(dateIn);
	}

	public void showTripSummary(ModelMap modelMap, SeatType seatType, String chosenFlightId) {
		modelMap.put("seatType", seatType.toString());
		modelMap.put("selectedFlightId", chosenFlightId);

		ScheduledFlight scheduledFlight = scheduledFlightRepository.getOne(chosenFlightId);
		modelMap.put("selectedFlight", scheduledFlight);
		modelMap.put("formatedDepartureDate", this.formatDepartureDate(scheduledFlight.getLocalDepartingDate()));
		airportService.getDepartingCityAirportAndArrivalCityAirport(scheduledFlight.getSource(),
				scheduledFlight.getDest(), modelMap);

	}
}
