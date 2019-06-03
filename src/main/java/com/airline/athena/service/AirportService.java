package com.airline.athena.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import com.airline.athena.model.Airport;
import com.airline.athena.model.forms.FlightSearchForm;
import com.airline.athena.repository.AirportRepository;

@Service
public class AirportService {
	@Autowired
	private AirportRepository airportRepository;

	public List<String> getAll() {
		List<String> airportIds = new ArrayList<>();
		List<Airport> airports = airportRepository.findAll();

		for (Airport airport : airports) {
			airportIds.add(airport.getAirportId() + " - " + airport.getCity());
		}

		return airportIds;
	}

	public void getDepartingCityAirportAndArrivalCityAirport(FlightSearchForm flightSearchForm, ModelMap modelMap) {
		Airport departingAirport = airportRepository.getOne(flightSearchForm.getFrom());
		Airport arrivalAirport = airportRepository.getOne(flightSearchForm.getTo());
		System.out.println("---" + departingAirport.getCity() + " to " + arrivalAirport.getCity());
		modelMap.put("departureCityAndArrivalCity", departingAirport.getCity() + " to " + arrivalAirport.getCity());
	}
}
