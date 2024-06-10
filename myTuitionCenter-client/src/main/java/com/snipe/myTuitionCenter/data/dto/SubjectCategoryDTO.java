package com.snipe.myTuitionCenter.data.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubjectCategoryDTO {

	private long categoryId;
	private SubjectDTO subject;
	private GradesDTO grade;
}
