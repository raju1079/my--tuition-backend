package com.snipe.myTuitionCenter.data.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="tutor_details")
public class Tutor {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="tutor_id")
	long tutorId;
	
	@Column(name="experience")
	private int experience;
	
	@JoinColumn(referencedColumnName = "contact_id")
	@OneToOne
	ContactDetails contactDetails;
	
	@JoinColumn(referencedColumnName = "profile_id")
	@OneToOne
	TutorProfile profile;
	
	@Column(name="individual")
	boolean is_individual;
	
	@JoinColumn(referencedColumnName = "institution_id")
	@OneToOne
	Institution institution;
	
	@Enumerated(EnumType.ORDINAL)
    private Status status;
	

}
