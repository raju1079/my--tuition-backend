package com.snipe.myTuitionCenter.data.entity;

public enum Availability {

	WEEKDAY("weekday"),
	WEEKEND("weekend"),
	BOTH("both");

	private String value;

	private Availability(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}
