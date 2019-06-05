package com.airline.athena.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.airline.athena.model.Payment;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long>{
	public Payment findTopByOrderByPaymentIdDesc();
}
