package com.airline.athena.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.airline.athena.model.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long>{
	public Order findTopByOrderByOrderIdDesc();
}
