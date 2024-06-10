package com.snipe.myTuitionCenter.admin.common;

public enum CommonConstants {

	CREATED("CREATED"),
	UPDATED("UPDATED"),
	DEACTIVATED("DEACTIVATED"),
	NEW("NEW"),
	MODIFIED("MODIFIED"),
	
	
	ROLENOTAVAILABLE("ROLE NOT AVAILABLE"), 
	ROLE_EXSIST("ROLE ALREADY EXSIST IN DATABASE"),
	ROLE("R"),
	
	USERNOTFOUND("USER NOT FOUND"),
	VALIDINFORMATION("Please provide valid information"),
	USEREMAILEXIST("User EmailId already exist"),
	EMAILDOESNOTEXIST("Email Id does not exist"),
	USERNAMENOTEXIST("User Name does not exist"),
	PHONENONOTEXIST("Phone Number does not exist"),
	LOGINEXCEPTION("Login Data Not Found"),
	EMAIL_TRIGGERED("Email Triggered"),
	X_SECURITY_TOKEN("X-Security-Token"),
	EMAIL("emailId"),
	ROLE_NAME("roleName"),
	EMAIL_SUBJECT("Welcome to MYTUITIONCENTER! Set Up Your Password");
	
	private final String value;

	CommonConstants(String value) {
		this.value =  value;
	}

	public String getValue() {
		return value;
	}
}
