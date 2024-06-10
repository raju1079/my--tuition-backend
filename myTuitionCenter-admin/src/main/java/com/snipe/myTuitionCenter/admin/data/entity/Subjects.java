package com.snipe.myTuitionCenter.admin.data.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="Subjects")
public class Subjects {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="subject_id")
	private long subjectId;
	
	@Column(name = "subject_name")
	private String subjectName;
	
	@Column(name = "tier")
	private String tier;
	
	@Column(name = "is_active")
	private boolean isActive;
	
	@Column(name = "creation_date")
	private LocalDate creationDate;
	
	@Column(name = "modified_date")
	private LocalDate modifiedDate;	
}
