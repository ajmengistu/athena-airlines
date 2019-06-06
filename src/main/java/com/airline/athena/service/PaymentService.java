package com.airline.athena.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import com.airline.athena.model.Payment;
import com.airline.athena.repository.OrderRepository;
import com.airline.athena.repository.PaymentRepository;

@Service
public class PaymentService {
	@Autowired
	private PaymentRepository paymentRepository;
	@Autowired
	private OrderRepository orderRepository;

	public void addNewPayment(ModelMap modelMap, String transactionId) {
		Payment payment = new Payment(orderRepository.findTopByOrderByOrderIdDesc().getOrderId(), transactionId,
				new Date());
		paymentRepository.save(payment);
	}	
	
}
