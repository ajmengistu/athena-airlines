package com.airline.athena.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import com.airline.athena.model.ScheduledFlight;
import com.airline.athena.model.enums.SeatType;
import com.airline.athena.model.forms.FlightSearchForm;
import com.airline.athena.repository.ScheduledFlightRepository;

@Service
public class ScheduledFlightService {

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

		ScheduledFlight scheduledFlight = this.getScheduledFlight(chosenFlightId);
		modelMap.put("selectedFlight", scheduledFlight);
		modelMap.put("formatedDepartureDate", this.formatDepartureDate(scheduledFlight.getLocalDepartingDate()));
		airportService.getDepartingCityAirportAndArrivalCityAirport(scheduledFlight.getSource(),
				scheduledFlight.getDest(), modelMap);
	}

	public ScheduledFlight getScheduledFlight(String flightId) {
		return scheduledFlightRepository.getOne(flightId);
	}

	public void submitPassengerForm(ModelMap modelMap, String seatType, String selectedFlightId) {
		ScheduledFlight scheduledFlight = this.getScheduledFlight(selectedFlightId);
		modelMap.put("selectedFlight", scheduledFlight);
		modelMap.put("formatedDepartureDate", this.formatDepartureDate(scheduledFlight.getLocalDepartingDate()));
		// returns departureCityAndArrivalCity in a ModelMap
		airportService.getDepartingCityAirportAndArrivalCityAirport(scheduledFlight.getSource(),
				scheduledFlight.getDest(), modelMap);
	}

	public void updateFlightSeatCount(ModelMap modelMap) {
		Integer numOfPassengers = (Integer) modelMap.get("selectedNumPassengers");

		ScheduledFlight scheduledFlight = scheduledFlightRepository
				.findByFlightId(modelMap.get("selectedFlightId").toString());
		if ((modelMap.get("seatType").toString().equals(SeatType.BUSINESS.toString()))) {
			scheduledFlight.setBusinessSeats(scheduledFlight.getBusinessSeats() - numOfPassengers);
		} else if ((modelMap.get("seatType").toString().equals(SeatType.FIRSTCLASS.toString()))) {
			scheduledFlight.setFirstClassSeats((scheduledFlight.getFirstClassSeats() - numOfPassengers));
		} else {
			scheduledFlight.setEconomySeats((scheduledFlight.getEconomySeats() - numOfPassengers));
		}

		scheduledFlightRepository.save(scheduledFlight);

	}
}
