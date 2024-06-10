package com.snipe.myTuitionCenter.student.service;

import com.snipe.myTuitionCenter.data.dto.StudentDTO;

public interface StudentService {

	public StudentDTO createStudentProfile(StudentDTO studentDTO) ;
	public StudentDTO getStudentById(String studentId);
	public StudentDTO updateStudentProfile(StudentDTO studentDTO); 

}
