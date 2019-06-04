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

import com.airline.athena.model.Passenger;
import com.airline.athena.repository.PassengerRepository;

@Service
public class PassengerService {
	@Autowired
	private PassengerRepository passengerRepository;

	public void submitPassengerForm(ModelMap modelMap, HttpServletRequest request) {
		List<Long> passengerIdsList = this.addNewPassengers(modelMap, request);
		modelMap.put("passengerIdsList", passengerIdsList);

		List<Passenger> passengersAddedList = new ArrayList<>();

		for (Long id : passengerIdsList) {
			passengersAddedList.add(passengerRepository.getOne(id));
		}

		modelMap.put("passengersAddedList", passengersAddedList);
	}

	public List<Long> addNewPassengers(ModelMap modelMap, HttpServletRequest request) {
		Integer numOfPass = Integer.valueOf((Integer) modelMap.get("selectedNumPassengers"));

		List<Long> passengerIdsList = new ArrayList<Long>();

		for (int i = 0; i < numOfPass; i++) {
			Date date;
			try {
				String dob = request.getParameter((i + 1) + "birthday");
				date = new SimpleDateFormat("MM/dd/yyyy").parse(dob);

				Passenger p = new Passenger(request.getParameter("firstName" + (i + 1)),
						request.getParameter("middleName" + (i + 1)), request.getParameter("lastName" + (i + 1)), date,
						request.getParameter("gender" + (i + 1)));

				passengerRepository.save(p);
				passengerIdsList.add(passengerRepository.findTopByOrderByIdDesc().getId());
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return passengerIdsList;
	}
}
