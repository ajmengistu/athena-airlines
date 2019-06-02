package com.airline.athena.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "flight_costs")
public class FlightCost {
	@Id
	@Column(nullable = false, length = 20)
	private String seatType;

	@Column(nullable = false)
	private BigDecimal flatRate;

	public String getSeatType() {
		return this.seatType;
	}

	public void setSeatType(String seatType) {
		this.seatType = seatType;
	}

	public BigDecimal getFlatRate() {
		return this.flatRate;
	}

	public void setFlatRate(BigDecimal flatRate) {
		this.flatRate = flatRate;
	}
	
	public Integer getAirfare() {
		return flatRate.intValue();
	}
}
 