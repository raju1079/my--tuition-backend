package com.snipe.myTuitionCenter.data.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="tutor_profile_info")
public class TutorProfile {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="profile_id")
	long profile_id;
	
	@Column(name="edu_bkgrnd")
	String educational_background;
	
	@Column(name="certifications")
	String certification;
	
	@JoinColumn(referencedColumnName = "grade_id")
	@ManyToOne
	Grades grade;
	
	
}
