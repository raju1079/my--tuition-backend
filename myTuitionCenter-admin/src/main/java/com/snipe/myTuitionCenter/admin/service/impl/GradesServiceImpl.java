package com.snipe.myTuitionCenter.admin.service.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.snipe.myTuitionCenter.admin.data.dto.GradeSearchDTO;
import com.snipe.myTuitionCenter.admin.data.dto.GradesDTO;
import com.snipe.myTuitionCenter.admin.data.entity.Grades;
import com.snipe.myTuitionCenter.admin.repository.GradesRepository;

import com.snipe.myTuitionCenter.admin.service.GradesService;

@Service
public class GradesServiceImpl implements GradesService{

	@Autowired
	GradesRepository gradesRepo;
	
	@Autowired
	ModelMapper modelMapper;
	
	@Override
	public GradesDTO addGrades(GradesDTO gradesDto) {
		
		Grades grade = modelMapper.map(gradesDto,Grades.class);
						
		gradesRepo.save(grade);
		
		GradesDTO dto = modelMapper.map(grade,GradesDTO.class);
		return dto;		
	}

	@Override
	public GradesDTO updateGrade(GradesDTO gradesDto) {
		
		Grades grade = modelMapper.map(gradesDto,Grades.class);
		
		Grades g = gradesRepo.findById(grade.getGradeId()).get();
			
		g.setName(gradesDto.getName());	
		g.setDescription(gradesDto.getDescription());
		g.setBoard(gradesDto.getBoard());
		g.setGradeCategory(gradesDto.getGradeCategory());
		g.setModifiedDate(gradesDto.getModifiedDate());
		gradesRepo.save(g);
		
		GradesDTO dto = modelMapper.map(g,GradesDTO.class);
		return dto;	
		
	}

	@Override
	public List<GradesDTO> fetchAllGrades() {

		List<Grades> grades = new ArrayList<>();
		
		grades = gradesRepo.findAll();
		
		return convertToDto(grades);
	}

	@Override
	public void deactivateGrade(long gradeId) {
	
		Grades g = gradesRepo.findById(gradeId).get();
		g.setActive(false);	
		g.setModifiedDate(LocalDate.now());
		gradesRepo.save(g);
	}

	@Override
	public void activateGrade(long gradeId) {
		
		Grades g = gradesRepo.findById(gradeId).get();
		g.setActive(true);	
		g.setModifiedDate(LocalDate.now());
		gradesRepo.save(g);		
	}

	@Override
	public List<GradesDTO> searchGrade(GradeSearchDTO gradesearchDto) {

		List<Grades> grades = new ArrayList<>();
		
		grades = gradesRepo.findByGradeDto(gradesearchDto.getGradeId(),
				gradesearchDto.getGradeName(),gradesearchDto.getCreatedDate());
		
		return convertToDto(grades);
	}

	@Override
	public List<GradesDTO> searchGradeByGradeName(String gradeName) {

		List<Grades> grades = new ArrayList<>();
		
		grades = gradesRepo.findByName(gradeName);
			
		return convertToDto(grades);
	}

	@Override
	public List<GradesDTO> searchGradeByGradeId(long gradeId) {
	
		List<Grades> grades = new ArrayList<>();
		grades = gradesRepo.findByGradeId(gradeId);
		
		return convertToDto(grades);
	}

	@Override
	public List<GradesDTO> searchGradeByCreatedDate(LocalDate creationDate) {
		
		List<Grades> grades = new ArrayList<>();
		grades = gradesRepo.findBycreationDate(creationDate);
		
		return convertToDto(grades);
	}
	
	public List<GradesDTO> convertToDto(List<Grades> grades){
		
		List<GradesDTO> gradesList = new ArrayList<>();
		for(Grades grade : grades) {
			GradesDTO dto = modelMapper.map(grade, GradesDTO.class);
			gradesList.add(dto);
		}
		return gradesList;
	}

}
