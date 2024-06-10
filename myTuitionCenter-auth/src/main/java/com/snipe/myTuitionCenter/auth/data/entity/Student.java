package com.snipe.myTuitionCenter.auth.data.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
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
	@Column(name="student_id")
	private String studentId;
	
}

