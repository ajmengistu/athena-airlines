package com.airline.athena.model;

public enum SeatType {
	ECONOMY("ECONOMY"), FIRSTCLASS("FIRSTCLASS"), BUSINESS("BUSINESS");

	private String value;

	SeatType(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return this.value;
	}
}
