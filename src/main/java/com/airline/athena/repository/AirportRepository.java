package com.airline.athena.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.airline.athena.model.Airport;

@Repository
public interface AirportRepository extends JpaRepository<Airport, String>{
}
