package com.snipe.myTuitionCenter.admin.data.entity;

import java.time.LocalDate;

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
@Table(name="subject_category")
public class SubjectCategory {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long categoryId;
	
	@JoinColumn(referencedColumnName = "subject_id" )
	@ManyToOne
	private Subjects subject;
	//private String subjectId;
	
	@JoinColumn(referencedColumnName = "grade_id")
	@ManyToOne
	private Grades grade;
	//private String gradeId;
	
	@Column(name="creation_date")
	private LocalDate creationDate;
	
	@Column(name="modified_date")
	private LocalDate modifiedDate;
	
	@Column(name="is_active")
	private boolean isActive;
}
