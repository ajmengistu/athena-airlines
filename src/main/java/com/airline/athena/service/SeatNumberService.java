package com.airline.athena.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.airline.athena.repository.SeatNumberRepository;

@Service
public class SeatNumberService {
	@Autowired
	public SeatNumberRepository seatNumberRepository;	
}
