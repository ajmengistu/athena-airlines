package com.airline.athena.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import com.airline.athena.model.FlightCost;
import com.airline.athena.model.enums.SeatType;
import com.airline.athena.repository.FlightCostRepository;

@Service
public class FlightCostService {
	@Autowired
	private FlightCostRepository flightCostRepository;
	
	public void getFlightCosts(ModelMap modelMap) {
		modelMap.addAttribute("airfare", flightCostRepository.findAll());
	}

	public void showTripSummary(ModelMap modelMap, SeatType seatType) {
		FlightCost flightCost = this.getFlightCostById(seatType);
		Integer numOfPassengers = Integer.valueOf((Integer) modelMap.get("selectedNumPassengers"));

		modelMap.put("flightCost", flightCost);
		Integer totalCost = numOfPassengers * flightCost.getAirfare();
		modelMap.put("totalCost", totalCost);

		List<Integer> passengers = new ArrayList<Integer>(numOfPassengers);

		for (int i = 0; i < numOfPassengers; i++) {
			passengers.add(i + 1);
		}

		modelMap.addAttribute("passengers", passengers);

	}

	public FlightCost getFlightCostById(SeatType seatType) {
		return flightCostRepository.getOne(seatType.toString());
	}

	
	public void submitPassengerForm(ModelMap modelMap) {
		// Tax 10 percent of purchase price
		BigDecimal taxRate = new BigDecimal("0.09");

		FlightCost flightCost = this.getFlightCostById(SeatType.valueOf(modelMap.get("seatType").toString()));
		Integer numOfPassengers = Integer.valueOf((Integer) modelMap.get("selectedNumPassengers"));

		BigDecimal subTotal = flightCost.getFlatRate().multiply(new BigDecimal(Integer.toString(numOfPassengers)));
		BigDecimal tax = subTotal.multiply(taxRate).setScale(2, RoundingMode.CEILING);
		BigDecimal totalCost = subTotal.add(tax);
		
		modelMap.put("taxes", tax);
		modelMap.put("subTotal", subTotal);
		modelMap.put("totalCost", totalCost);
		System.out.println(subTotal);
		System.out.println(tax);
		System.out.println(totalCost);
		System.out.println(taxRate);
	}
}
