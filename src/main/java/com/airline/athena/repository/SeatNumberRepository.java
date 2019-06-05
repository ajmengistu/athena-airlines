package com.airline.athena.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.airline.athena.model.SeatNumber;

public interface SeatNumberRepository extends JpaRepository<SeatNumber, Long> {
	public SeatNumber findTopByOrderByIdAsc();

	public List<SeatNumber> findByFlightIdAndSeatType(String flightId, String seatType);

	public List<SeatNumber> findByFlightIdAndSeatTypeAndSeatTaken(String flightId, String seatType, Boolean seatTaken);

	public List<SeatNumber> findOneByFlightIdAndSeatTypeAndSeatTaken(String flightId, String seatType,
			Boolean seatTaken);
	public SeatNumber findFirstByFlightIdAndSeatTypeAndSeatTakenOrderByIdAsc(String flightId, String seatType,
			Boolean seatTaken);
	
	public SeatNumber findOneById(Long id);
}
