package com.snipe.myTuitionCenter.admin.data.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="login_credential")
public class LoginCredential {
	
	
	@Id
	@Column(name = "login_id")
	private String loginId;

	@Column(name = "email_id", unique = true)
	private String emailId;

	@Column(name = "password")
	private String password;
	
	@JoinColumn(referencedColumnName = "role_id")
	@ManyToOne	
	private Roles role;

	@Column(name = "created_by")
	private String createdBy;
	
	@Column(name = "creation_date")
	private String creationDate;
	
	@Column(name = "reset_password_token")
	private String resetPasswordToken;
}
