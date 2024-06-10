package com.snipe.myTuitionCenter.admin.data.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
@Table(name="Grades")
public class Grades {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="grade_id")
	private long gradeId;
	
	@Column(name="grade_name")
	private String name;
	
	@Column(name="description")
	private String description;
	
	@Column(name="board")
	@Enumerated(EnumType.STRING)
	private Board board;
	
	@Column(name="grade_category")
	private String gradeCategory;
	
	@Column(name="is_active")
	private boolean isActive;
	
	@Column(name="creation_date")
	private LocalDate creationDate;
	
	@Column(name="modified_date")
	private LocalDate modifiedDate;	
}
