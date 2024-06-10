package com.snipe.myTuitionCenter.admin.controller;

import java.time.LocalDate;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.snipe.myTuitionCenter.admin.data.dto.GradeSearchDTO;
import com.snipe.myTuitionCenter.admin.data.dto.GradesDTO;
import com.snipe.myTuitionCenter.admin.service.GradesService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;


@RestController
@Tag(name = "Admin", description = "Admin grade management APIs")
public class GradesController {
	
	@Autowired
	GradesService gradesService;
	
	@Autowired
	ModelMapper modelMapper;

	@Operation(summary="Admin can add grade")
	@PostMapping("/admin/grade")
	public ResponseEntity<GradesDTO> addGrade(@Valid @RequestBody GradesDTO request) {
		
//		GradesDTO gradesDto = modelMapper.map(request,GradesDTO.class);
		GradesDTO dto = gradesService.addGrades(request);
		//GradeResponse createGradeResponse = modelMapper.map(dto, GradeDTO.class);
		return ResponseEntity.status(HttpStatus.CREATED).body(dto);
	}
	
	@Operation(summary="Get all grades")
	@GetMapping("/admin/grade")
	public List<GradesDTO> getAllGrades() {
		return gradesService.fetchAllGrades();
	}
	
	@Operation(summary="Admin can update grade")
	@PutMapping("/admin/grade")
	public ResponseEntity<GradesDTO> updateGrade(@RequestBody GradesDTO request){
		//GradesDTO gradesDto = modelMapper.map(request,GradesDTO.class);
		GradesDTO dto = gradesService.updateGrade(request);
		//GradeResponse updateGradeResponse = modelMapper.map(dto, GradeResponse.class);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
	}
	
	@Operation(summary="Admin can deactivate grade")
	@DeleteMapping("/admin/grade/{gradeId}")
	public ResponseEntity<String> deactivateGrade(@PathVariable long gradeId) {
		gradesService.deactivateGrade(gradeId);
		return ResponseEntity.status(HttpStatus.OK).body(gradeId+" deactivated");
	}
	
	@Operation(summary="Admin can activate grade")
	@PutMapping("/admin/grade/{gradeId}")
	public ResponseEntity<String> activateGrade(@PathVariable long gradeId) {
		gradesService.activateGrade(gradeId);
		return ResponseEntity.status(HttpStatus.OK).body(gradeId+" activated");
	}
	
	@Operation(summary="Admin can search grade")
	@GetMapping("/admin/grade/search")
	public ResponseEntity<List<GradesDTO>> searchGrade(@RequestBody GradeSearchDTO dto) {
		
		List<GradesDTO> gradesList = gradesService.searchGrade(dto);
		return ResponseEntity.status(HttpStatus.OK).body(gradesList);
	}
	
	@Operation(summary="Admin can search grade")
	@GetMapping("/admin/grade/searchbyname/{gradeName}")
	public ResponseEntity<List<GradesDTO>> searchGradebyGradeName(@PathVariable String gradeName) {
		
		List<GradesDTO> gradesList = gradesService.searchGradeByGradeName(gradeName);
		return ResponseEntity.status(HttpStatus.OK).body(gradesList);
	}
	
	@Operation(summary="Admin can search grade by gradeId")
	@GetMapping("/admin/grade/searchbyId/{gradeId}")
	public ResponseEntity<List<GradesDTO>> searchGradeByGradeId(@PathVariable long gradeId) {
		
		List<GradesDTO> gradesList = gradesService.searchGradeByGradeId(gradeId);
		return ResponseEntity.status(HttpStatus.OK).body(gradesList);
	}
	
	@Operation(summary="Admin can search grade by created date")
	@GetMapping("/admin/grade/searchbyCreationDate")
	public ResponseEntity<List<GradesDTO>> searchGrade(@RequestParam LocalDate creationDate) {
		
		List<GradesDTO> gradesList = gradesService.searchGradeByCreatedDate(creationDate);
		return ResponseEntity.status(HttpStatus.OK).body(gradesList);
	}
	
	
}
