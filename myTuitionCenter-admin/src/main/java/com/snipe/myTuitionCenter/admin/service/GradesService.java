package com.snipe.myTuitionCenter.admin.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.snipe.myTuitionCenter.admin.data.dto.GradeSearchDTO;
import com.snipe.myTuitionCenter.admin.data.dto.GradesDTO;

@Service
public interface GradesService {

	public GradesDTO addGrades(GradesDTO gradesDto);
	public GradesDTO updateGrade(GradesDTO gradesDto);
	public List<GradesDTO> fetchAllGrades();
	public void deactivateGrade(long gradeId);
	public void activateGrade(long gradeId);
	public List<GradesDTO> searchGrade(GradeSearchDTO gradesearchDto);
	public List<GradesDTO> searchGradeByGradeName(String gradeName);
	public List<GradesDTO> searchGradeByGradeId(long gradeId);
	public List<GradesDTO> searchGradeByCreatedDate(LocalDate createdDate);
	
}
