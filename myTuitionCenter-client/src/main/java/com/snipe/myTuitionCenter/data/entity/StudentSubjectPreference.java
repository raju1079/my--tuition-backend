package com.snipe.myTuitionCenter.data.entity;

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
@Table(name="student_subject_preference")
public class StudentSubjectPreference {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="preference_id")
	private long preferenceId;
	
	@JoinColumn(name="student_id",referencedColumnName="student_id")
	@ManyToOne
	Student student;
	
	@JoinColumn(name="category_id",referencedColumnName="category_id")
	@ManyToOne
	SubjectCategory subjectCategory;
}
