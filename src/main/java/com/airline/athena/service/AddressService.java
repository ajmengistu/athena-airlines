package com.airline.athena.service;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.airline.athena.model.Address;
import com.airline.athena.repository.AddressRepository;

@Service
public class AddressService {
	@Autowired
	private AddressRepository addressRepository;

	public void AddNewAddress(HttpServletRequest request) {
		Address address = new Address(request.getParameter("firstName"), request.getParameter("lastName"),
				request.getParameter("middleName"), request.getParameter("address1"), request.getParameter("address2"),
				request.getParameter("city"), request.getParameter("state"), request.getParameter("zip"),
				request.getParameter("email"), new Date());

		addressRepository.save(address);
	}
}
