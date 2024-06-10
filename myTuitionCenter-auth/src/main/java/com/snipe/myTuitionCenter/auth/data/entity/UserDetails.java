package com.snipe.myTuitionCenter.auth.data.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name="user_details")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDetails {
	
	@Id
	@Column(name="user_id")
	private String userId;
	
	@Column(name="user_name")
	private String userName;
	
	@JoinColumn(referencedColumnName="student_id",name="student_id")
	@OneToOne(cascade=CascadeType.ALL)
	Student student;
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(referencedColumnName = "login_id")
	LoginCredential loginCredential;

}
