package com.snipe.myTuitionCenter.auth.data.response;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SuccessResponse {
	
	private String loginId;
	private String emailId;
	private String token;
	private String Role;
	private String status;

}