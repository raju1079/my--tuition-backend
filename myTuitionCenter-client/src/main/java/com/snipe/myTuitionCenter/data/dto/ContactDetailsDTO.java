package com.snipe.myTuitionCenter.data.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContactDetailsDTO {
	
	private long phoneNo;
	private String emailId;
	private AddressDTO address;
	
	
}
