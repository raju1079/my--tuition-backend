package com.snipe.myTuitionCenter.data.entity;

public enum Tuitiontype {
		HOME("home"),
		ONSITE("onsite");
	
		private String value;
		
		private Tuitiontype(String value) {
			this.value = value;
		}
		
		public String getValue() {
			return value;
		}
}
