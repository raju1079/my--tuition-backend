package com.snipe.myTuitionCenter.auth.data.entity;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.snipe.myTuitionCenter.auth.util.StringPrefixedSequenceIdGenerator;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
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
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "login_id_seq")
	@GenericGenerator( name = "login_id_seq", strategy =  "com.snipe.myTuitionCenter.auth.util.StringPrefixedSequenceIdGenerator",
	parameters = {
			@Parameter(name = StringPrefixedSequenceIdGenerator.INCREMENT_PARAM, value = "1"),
			@Parameter(name = StringPrefixedSequenceIdGenerator.VALUE_PREFIX_PARAMETER, value = "U_"),
			@Parameter(name = StringPrefixedSequenceIdGenerator.NUMBER_FORMAT_PARAMETER,  value = "%05d") })
	
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
