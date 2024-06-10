package com.snipe.myTuitionCenter.admin.data.dto;

import java.time.LocalDate;

import com.snipe.myTuitionCenter.admin.data.entity.Board;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GradesDTO {
	  
	private long gradeId;		
	private String name;	
	private String description;
	private Board board;
	private String gradeCategory;
	private boolean isActive;		
	private LocalDate creationDate;
	public LocalDate modifiedDate;
		
}
