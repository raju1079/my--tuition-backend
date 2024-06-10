package com.snipe.myTuitionCenter.admin.data.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(Include.NON_NULL)
public class UserDetailsDTO {

	private String userId;
	private String loginId;
	private String userName;
	private String gender;
	private RolesDTO role;
	private boolean active;
	private String status;
	private LocalDateTime joiningDate;
	private LocalDateTime creationDate;
	private LocalDateTime modifiedDate;
	private ContactDetailsDTO contactDetails;
	private String emailStatus;

}
