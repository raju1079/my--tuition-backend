package com.snipe.myTuitionCenter.data.entity;


import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.snipe.myTuitionCenter.data.util.StringPrefixedSequenceIdGenerator;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
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
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_id_seq")
	@GenericGenerator( name = "user_id_seq", strategy =  "com.snipe.myTuitionCenter.data.util.StringPrefixedSequenceIdGenerator",
	parameters = {
			@Parameter(name = StringPrefixedSequenceIdGenerator.INCREMENT_PARAM, value = "1"),
			@Parameter(name = StringPrefixedSequenceIdGenerator.VALUE_PREFIX_PARAMETER, value = "U_"),
			@Parameter(name = StringPrefixedSequenceIdGenerator.NUMBER_FORMAT_PARAMETER,  value = "%05d") })
	@Column(name="user_id")
	private String userId;
	
	@Column(name="user_name")
	private String userName;
	
	@Column(name="gender")
	private String gender;
	
	@JoinColumn(referencedColumnName = "contact_id")
	@OneToOne(cascade=CascadeType.ALL)
	ContactDetails contactDetails;
	
	@JoinColumn(name="student_id",referencedColumnName="student_id")
	@OneToOne(cascade=CascadeType.ALL)
	Student student;

}
