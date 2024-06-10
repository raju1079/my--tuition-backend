package com.snipe.myTuitionCenter.student.controller;

import static com.snipe.myTuitionCenter.student.exception.HttpResponseUtils.prepareSuccessResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.snipe.myTuitionCenter.data.dto.StudentDTO;
import com.snipe.myTuitionCenter.student.common.GenericResponse;
import com.snipe.myTuitionCenter.student.constants.ResponseConstants;
import com.snipe.myTuitionCenter.student.service.StudentService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
@RestController
@RequestMapping("/mytuitioncenter")
@Tag(name = "Student", description = "Student client APIs")
public class StudentController {
	
	@Autowired
	StudentService studentService;
	
	private static final Logger logger = LoggerFactory.getLogger(StudentController.class);
	
	@Operation(summary = "Add new student ", description = "Adding a new student to the Database")
	@PostMapping("/student/profile")
	@ResponseStatus(code = HttpStatus.OK)
	public  ResponseEntity<GenericResponse<StudentDTO>> createStudentProfile(@RequestBody StudentDTO studentDTO) {
		logger.debug("In clientApp, Student controller,createStudentProfile method");
		return prepareSuccessResponse(studentService.createStudentProfile(studentDTO),
				ResponseConstants.STUDENT_PROFILE_INFO_CREATE_SUCCESS);
	}
	
	@Operation(summary = "Update student profile", description = "Update existing student info in DB")
	@PutMapping("/student/profile")
	@ResponseStatus(code = HttpStatus.OK)
	public  ResponseEntity<GenericResponse<StudentDTO>> updateStudentProfile(@RequestBody StudentDTO studentDTO) {
		logger.debug("In clientApp, Student controller, updateStudentProfile method");
		return prepareSuccessResponse(studentService.updateStudentProfile(studentDTO),
				ResponseConstants.STUDENT_PROFILE_INFO_UPDATE_SUCCESS);
	}
	
	@Operation(summary = "Get Student by Id", description = "fetching student by student_id from database")
	@GetMapping("/student/{studentId}")
	@ResponseStatus(code = HttpStatus.OK)
	public  ResponseEntity<GenericResponse<StudentDTO>> getStudentById(@PathVariable String studentId) {
		logger.debug("In clientApp, Student controller, getstudentById method");
		return prepareSuccessResponse(studentService.getStudentById(studentId));
	}

}
