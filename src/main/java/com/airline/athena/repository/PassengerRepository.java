package com.airline.athena.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.airline.athena.model.Passenger;

@Repository
public interface PassengerRepository extends JpaRepository<Passenger, Long> {
	public Passenger findTopByOrderByIdDesc();
}
