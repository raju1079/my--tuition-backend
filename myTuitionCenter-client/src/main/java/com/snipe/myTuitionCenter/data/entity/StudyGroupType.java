package com.snipe.myTuitionCenter.data.entity;

public enum StudyGroupType {
	ONE_ON_ONE("individual"),
	GROUP("group");
	
	private String value;
	
	private StudyGroupType(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return value;
	}
    
}
