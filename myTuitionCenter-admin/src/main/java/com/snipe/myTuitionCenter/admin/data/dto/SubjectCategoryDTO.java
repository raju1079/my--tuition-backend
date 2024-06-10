package com.snipe.myTuitionCenter.admin.data.dto;

import java.time.LocalDate;

import com.snipe.myTuitionCenter.admin.data.entity.Grades;
import com.snipe.myTuitionCenter.admin.data.entity.Subjects;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubjectCategoryDTO {

	private long categoryId;
	private Grades grade;
	private Subjects subject;
	private LocalDate creationDate;
	private LocalDate modifiedDate;
	private boolean isActive;	
}
