package com.airline.athena.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.airline.athena.model.enums.FlightMethod;
import com.airline.athena.model.enums.SeatType;
import com.airline.athena.model.forms.CheckInForm;
import com.airline.athena.model.forms.FlightSearchForm;
import com.airline.athena.model.forms.FlightStatusForm;
import com.airline.athena.service.AddressService;
import com.airline.athena.service.AirportService;
import com.airline.athena.service.FlightCostService;
import com.airline.athena.service.FlightStatusService;
import com.airline.athena.service.OrderService;
import com.airline.athena.service.PassengerService;
import com.airline.athena.service.PaymentService;
import com.airline.athena.service.ProcessPaymentService;
import com.airline.athena.service.ScheduledFlightService;
import com.braintreegateway.BraintreeGateway;
import com.braintreegateway.ClientTokenRequest;

@Controller
@SessionAttributes({ "selectedNumPassengers", "selectedFlightId", "selectedFlightMethod", "seatType",
		"passengerIdsList" })
public class BookAFlightController {
	@Autowired
	private ScheduledFlightService scheduledFlightService;
	@Autowired
	private AirportService airportService;
	@Autowired
	private FlightCostService flightCostService;
	@Autowired
	private PassengerService passengerService;
	@Autowired
	private AddressService addressService;
	@Autowired
	private ProcessPaymentService processPaymentService;
	@Autowired
	private OrderService orderService;
	@Autowired
	private PaymentService paymentService;
	@Autowired
	private FlightStatusService flightStatusService;

	// DO NOT REMOVE THIS: needed for front-end auto-complete jQuery request.
	@GetMapping("/airports")
	public @ResponseBody List<String> getAirports() {
		return airportService.getAll();
	}

	/* ******* Generate a BrainTreeGateway token for payment transaction ****** */
	@RequestMapping(value = "/search-flights/book-a-flight/token", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody Map<String, String> getClientToken() {
		BraintreeGateway gateway = processPaymentService.getBrainTreeGateway();
		ClientTokenRequest clientTokenRequest = new ClientTokenRequest();
		String clientToken = gateway.clientToken().generate(clientTokenRequest);
		HashMap<String, String> map = new HashMap<>();
		map.put("clientToken", clientToken);
		return map;
	}

	@GetMapping("/")
	public String getHomeAndSearchForFlights(FlightSearchForm flightSearchForm, CheckInForm checkInForm,
			FlightStatusForm flightStatusForm) {
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
		scheduledFlightService.getFlightSearchResults(modelMap, flightSearchForm);
		flightCostService.getFlightCosts(modelMap);
		airportService.getDepartingCityAirportAndArrivalCityAirport(flightSearchForm, modelMap);

		System.out.println(flightSearchForm);

		return "flight-search-results";
	}

	@GetMapping("/search-flights/book-a-flight/your-trip-summary")
	public String showTripSummary(ModelMap modelMap, @RequestParam String chosenFlightId,
			@RequestParam String seatType) {
		SeatType seatType2 = SeatType.valueOf(seatType.toUpperCase());
		scheduledFlightService.showTripSummary(modelMap, seatType2, chosenFlightId);
		flightCostService.showTripSummary(modelMap, seatType2);

		return "your-trip-summary";
	}

	@PostMapping("/search-flights/book-a-flight/review-and-pay")
	public String submitPassengerForm(ModelMap modelMap, HttpServletRequest request) {
		passengerService.submitPassengerForm(modelMap, request);
		scheduledFlightService.submitPassengerForm(modelMap, modelMap.get("seatType").toString(),
				modelMap.get("selectedFlightId").toString());
		flightCostService.submitPassengerForm(modelMap);
		return "review-and-pay";
	}

	@PostMapping("/search-flights/book-a-flight/order-details")
	public String processPayment(@RequestParam("payment_method_nonce") String paymentMethodNonce,
			HttpServletRequest request, ModelMap modelMap) {
		addressService.AddNewAddress(request);
		String transactionId = processPaymentService.processPayment(modelMap, paymentMethodNonce);

		if (transactionId != null) {
			passengerService.assignEachPassengerASeat(modelMap);
			scheduledFlightService.updateFlightSeatCount(modelMap);
			orderService.SaveNewOrder(modelMap);
			paymentService.addNewPayment(modelMap, transactionId);
			passengerService.sendEmailWithTicketToPassenger(modelMap);
		}
		scheduledFlightService.submitPassengerForm(modelMap, modelMap.get("seatType").toString(),
				modelMap.get("selectedFlightId").toString());
		passengerService.getCurrentBookingPassengers(modelMap);
		orderService.getCurrentOrderNumber(modelMap);

		return "order-details";
	}

	@PostMapping("/search-flights/checkin")
	public String checkIn(CheckInForm checkInForm, ModelMap modelMap) {
		passengerService.checkInPassenger(checkInForm, modelMap);
		return "flight-checkin";
	}

	@GetMapping("/search-flights/flight-status")
	public String flightStatus(FlightStatusForm flightStatusForm, ModelMap modelMap) {
		flightStatusService.getFlightStatus(flightStatusForm, modelMap);
		return "flight-status";
	}
}
