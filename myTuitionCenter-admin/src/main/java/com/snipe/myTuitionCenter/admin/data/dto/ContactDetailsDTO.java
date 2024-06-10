package com.snipe.myTuitionCenter.admin.data.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContactDetailsDTO {

	private long contactId;
	private long phoneNo;
	private String emailId;

}
