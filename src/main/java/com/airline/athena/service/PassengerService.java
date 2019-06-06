package com.airline.athena.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import com.airline.athena.model.Passenger;
import com.airline.athena.model.SeatNumber;
import com.airline.athena.repository.PassengerRepository;
import com.airline.athena.repository.SeatNumberRepository;

@Service
public class PassengerService {
	@Autowired
	private PassengerRepository passengerRepository;
	@Autowired
	public SeatNumberRepository seatNumberRepository;

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

	public void assignEachPassengerASeat(ModelMap modelMap) {
		@SuppressWarnings("unchecked")
		List<Long> ids = (List<Long>) modelMap.get("passengerIdsList");
		for (Long id : ids) {
			Passenger passenger = passengerRepository.getOne(id);
			passenger.setPayed(true);
			// Give each passenger a confirmation code
			passenger.setConfirmationCode(UUID.randomUUID().toString().substring(0, 8));
			// --------------Assign a Seat & Update -------------------
			SeatNumber seatNumber = seatNumberRepository.findFirstByFlightIdAndSeatTypeAndSeatTakenOrderByIdAsc(
					modelMap.get("selectedFlightId").toString(), modelMap.get("seatType").toString(), false);

			passenger.setSeatNumber(seatNumber.getSeatNumber());
			seatNumber.setSeatTaken(true);

			seatNumberRepository.save(seatNumber);
			passengerRepository.save(passenger);
		}
	}

	public void getCurrentBookingPassengers(ModelMap modelMap) {
		List<Passenger> passengerAddedList = new ArrayList<>();
		@SuppressWarnings("unchecked")
		List<Long> ids = (List<Long>) modelMap.get("passengerIdsList");
		for (Long id : ids) {
			passengerAddedList.add(passengerRepository.getOne(id));
		}
		modelMap.put("passengerAddedList", passengerAddedList);
	}
}
