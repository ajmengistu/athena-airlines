package com.airline.athena.service;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import com.airline.athena.model.Order;
import com.airline.athena.repository.AddressRepository;
import com.airline.athena.repository.OrderRepository;

@Service
public class OrderService {
	@Autowired
	private OrderRepository orderRepository;
	@Autowired
	private AddressRepository addressRepository;

	public void SaveNewOrder(ModelMap modelMap) {

		Order order = new Order((BigDecimal) modelMap.get("totalCost"), new Date(),
				addressRepository.findTopByOrderByAddressIdDesc().getAddressId());
		orderRepository.save(order);
	}
	
	public void getCurrentOrderNumber(ModelMap modelMap) {
		Order order = orderRepository.findTopByOrderByOrderIdDesc();
		modelMap.put("confirmationNumber", order.getHash());
	}
}
