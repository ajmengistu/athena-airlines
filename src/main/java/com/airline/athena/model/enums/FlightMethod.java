package com.airline.athena.model.enums;

public enum FlightMethod {
	ONEWAY("ONEWAY"), ROUNDTRIP("ROUNDTRIP");

	private String value;

	FlightMethod(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return this.value;
	}
}
