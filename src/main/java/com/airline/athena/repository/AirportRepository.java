package com.airline.athena.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.airline.athena.model.Airport;

public interface AirportRepository extends JpaRepository<Airport, String>{
}
