package com.snipe.myTuitionCenter.data.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddressDTO implements Serializable{
	
	private static final long serialVersionUID = 3539092712415244929L;
	private String addressLine1;
	private String locality;
	private String city;
	private String state;
	private String country;
	private String pincode;
}
