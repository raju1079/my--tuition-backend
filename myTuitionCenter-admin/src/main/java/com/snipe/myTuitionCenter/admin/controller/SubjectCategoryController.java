package com.snipe.myTuitionCenter.admin.controller;

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
import org.springframework.web.bind.annotation.RestController;

import com.snipe.myTuitionCenter.admin.data.dto.SubjectCategoryDTO;
import com.snipe.myTuitionCenter.admin.service.SubjectCategoryService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@Tag(name = "admin")
public class SubjectCategoryController {
	
	@Autowired
	ModelMapper modelMapper;
	
	@Autowired
	SubjectCategoryService subCatService;

	@PostMapping("/admin/subjectCategory")
	@Operation(description = "Admin can add subject to a grade")
	public ResponseEntity<SubjectCategoryDTO> addSubjectCategory(@RequestBody 
			SubjectCategoryDTO req){
		
		SubjectCategoryDTO dto = modelMapper.map(req, SubjectCategoryDTO.class);
		
		SubjectCategoryDTO addSubjectCategoryDto = subCatService.addSubjectCategory(dto);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(addSubjectCategoryDto);
	}
	
	@GetMapping("/admin/subjectCategory")
	@Operation(description = "Get all grade and subject mappings")
	public ResponseEntity<List<SubjectCategoryDTO>> getAllCategories(){
		
		List<SubjectCategoryDTO> categories = subCatService.GetAllCategories();
				
		return ResponseEntity.status(HttpStatus.OK).body(categories);
	}
	
	@PutMapping("/admin/subjectCategory")
	@Operation(description = "update a particular grade and subject mappings")
	public ResponseEntity<SubjectCategoryDTO> UpdateCategory(@RequestBody 
			SubjectCategoryDTO req){
		
		SubjectCategoryDTO dto =  modelMapper.map(req, SubjectCategoryDTO.class);
				
		SubjectCategoryDTO updateSubjectCategoryDto = subCatService.updateSubjectCategory(dto);
				
		return ResponseEntity.status(HttpStatus.OK).body(updateSubjectCategoryDto);
	}
	
	@DeleteMapping("/admin/subjectCategory/{categoryId}")
	@Operation(description = "delete a particular grade and subject mappings")
	public ResponseEntity<String> deactivateCategory(@PathVariable long categoryId){
		
		subCatService.deactivateCategory(categoryId);
		
		return ResponseEntity.status(HttpStatus.OK).body("Deactivated");
	}
	
	@PutMapping("/admin/subjectCategory/{categoryId}")
	@Operation(description = "activate a particular grade and subject mappings")
	public ResponseEntity<String> activateCategory(@PathVariable long categoryId){
		
		subCatService.activateCategory(categoryId);
		
		return ResponseEntity.status(HttpStatus.OK).body("activated");
	}
	
	@GetMapping("/admin/subjectCategory/{searchString}")
	@Operation(description = "search grade and subject mappings")
	public ResponseEntity<List<SubjectCategoryDTO>> SearchCategory(@PathVariable String searchString){
		
		List<SubjectCategoryDTO> list = subCatService.searchCategory(searchString);
		
		return ResponseEntity.status(HttpStatus.OK).body(list);
	}
	
	@GetMapping("/admin/subjectCategorybyId/{categoryId}")
	@Operation(description = "search grade and subject mappings")
	public ResponseEntity<SubjectCategoryDTO> SearchCategoryById(@PathVariable long categoryId){
		
		SubjectCategoryDTO dto = subCatService.searchByCategoryId(categoryId);
		
		return ResponseEntity.status(HttpStatus.OK).body(dto);
	}
	
}
