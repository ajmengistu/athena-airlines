package com.airline.athena.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.airline.athena.model.Orders;

@Repository
public interface OrdersRepository extends JpaRepository<Orders, Long>{
	public Orders findTopByOrderByOrderIdDesc();
}
