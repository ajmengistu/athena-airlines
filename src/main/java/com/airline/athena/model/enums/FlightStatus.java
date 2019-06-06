package com.airline.athena.model.enums;

public enum FlightStatus {
	DEPARTED("DEPARTED"), ONTIME("ONTIME"), BOARDING("BOARDING");

	private String value;

	FlightStatus(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return this.value;
	}
}
