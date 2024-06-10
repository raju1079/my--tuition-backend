package com.snipe.myTuitionCenter.admin.data.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RolesDTO {
	
	private String roleId;
	
	private String roleName;
	
	@JsonProperty(value = "isActive",defaultValue = "true")
	private boolean isActive;
	
	private Date creationDate;
	
	private String status;
	
}
