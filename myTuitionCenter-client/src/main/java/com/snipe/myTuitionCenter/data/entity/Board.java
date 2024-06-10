package com.snipe.myTuitionCenter.data.entity;

public enum Board {
	CBSE("CBSE"),
	ICSE("ICSE"),
	STATE("STATE"),
	IGCSE("IGCSE");
	
	private String value;
	
	private Board(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return value;
	}

}
