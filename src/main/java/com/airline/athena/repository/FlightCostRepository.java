package com.airline.athena.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.airline.athena.model.FlightCost;

@Repository
public interface FlightCostRepository extends JpaRepository<FlightCost, String>{
}
