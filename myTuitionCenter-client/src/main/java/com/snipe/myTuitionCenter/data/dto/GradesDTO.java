package com.snipe.myTuitionCenter.data.dto;

import com.snipe.myTuitionCenter.data.entity.Board;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GradesDTO {
	
	long gradeId;
	private String name;
	private String description;
	Board board;
	private String gradeCategory;

}
