package com.snipe.myTuitionCenter.data.entity;


import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.snipe.myTuitionCenter.data.util.StringPrefixedSequenceIdGenerator;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Table(name="student_details")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "student_id_seq")
	@GenericGenerator( name = "student_id_seq", strategy =  "com.snipe.myTuitionCenter.data.util.StringPrefixedSequenceIdGenerator",
	parameters = {
			@Parameter(name = StringPrefixedSequenceIdGenerator.INCREMENT_PARAM, value = "1"),
			@Parameter(name = StringPrefixedSequenceIdGenerator.VALUE_PREFIX_PARAMETER, value = "S_"),
			@Parameter(name = StringPrefixedSequenceIdGenerator.NUMBER_FORMAT_PARAMETER,  value = "%05d") })
	@Column(name="student_id")
	private String studentId;

	@JoinColumn(referencedColumnName = "tutor_id")
	@ManyToOne
	Tutor tutor;

	@Column(name="mode_of_study")
	String modeOfStudy; 

	@Enumerated(EnumType.STRING)
	Status status;

	@Column(name="tutor_gender_preferred")
	private String tutorGenderPreference;

	@Column(name="max_fees")
	private String maxfees;
	
	@Enumerated(EnumType.STRING)
	StudyGroupType studyGroupType;
	
	@Enumerated(EnumType.STRING)
	Board board;
	
	@Enumerated(EnumType.STRING)
	Tuitiontype tuitionType;
	
	@Enumerated(EnumType.STRING)
	Availability availability;
	
}

