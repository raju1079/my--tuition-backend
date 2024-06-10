package com.snipe.myTuitionCenter.admin.data.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GradeSearchDTO {

	private long gradeId;
	private String gradeName;
	private LocalDate createdDate;
}
