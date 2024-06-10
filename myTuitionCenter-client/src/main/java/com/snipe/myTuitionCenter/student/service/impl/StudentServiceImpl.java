package com.snipe.myTuitionCenter.student.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.snipe.myTuitionCenter.data.dto.StudentDTO;
import com.snipe.myTuitionCenter.data.dto.SubjectCategoryDTO;
import com.snipe.myTuitionCenter.data.entity.Address;
import com.snipe.myTuitionCenter.data.entity.ContactDetails;
import com.snipe.myTuitionCenter.data.entity.Student;
import com.snipe.myTuitionCenter.data.entity.StudentSubjectPreference;
import com.snipe.myTuitionCenter.data.entity.SubjectCategory;
import com.snipe.myTuitionCenter.data.entity.UserDetails;
import com.snipe.myTuitionCenter.student.constants.ResponseConstants;
import com.snipe.myTuitionCenter.student.exception.MyTuitionCenterAppException.CONTACTDETAILS_NOT_FOUND;
import com.snipe.myTuitionCenter.student.exception.MyTuitionCenterAppException.STUDENT_NOT_FOUND;
import com.snipe.myTuitionCenter.student.exception.MyTuitionCenterAppException.STUDENT_PREFERENCE_ALREADY_EXISTS;
import com.snipe.myTuitionCenter.student.exception.MyTuitionCenterAppException.STUDENT_SUBJECT_PREFERENCE_NOT_FOUND;
import com.snipe.myTuitionCenter.student.exception.MyTuitionCenterAppException.SUBJECT_CATEGORY_NOT_FOUND;
import com.snipe.myTuitionCenter.student.exception.MyTuitionCenterAppException.USER_NOT_FOUND;
import com.snipe.myTuitionCenter.student.repository.ContactDetailsRepository;
import com.snipe.myTuitionCenter.student.repository.StudentRepository;
import com.snipe.myTuitionCenter.student.repository.StudentSubjectPreferenceRepository;
import com.snipe.myTuitionCenter.student.repository.SubjectCategoryRepository;
import com.snipe.myTuitionCenter.student.repository.UserDetailsRepository;
import com.snipe.myTuitionCenter.student.service.StudentService;
@Service
public class StudentServiceImpl implements StudentService {
	
	private static final Logger logger = LoggerFactory.getLogger(StudentServiceImpl.class);
	
	@Autowired
	private UserDetailsRepository userRepository;
	
	@Autowired
	private ContactDetailsRepository contactDetailsRepository;

	@Autowired
	private StudentRepository studentRepository;
	
	@Autowired
	private SubjectCategoryRepository subjectCategoryRepository;

	@Autowired 
	StudentSubjectPreferenceRepository studentSubjectPreferenceRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	
	@Override
	public StudentDTO createStudentProfile(StudentDTO studentDTO) {
		logger.debug("In clientApp, StudentServiceImpl, register student method");

		//get User signedup
		 UserDetails user = updateUser(studentDTO);
		 //saving student
		 Student student = modelMapper.map(studentDTO, Student.class);
		 user.setStudent(student);
		 userRepository.save(user);
		 
		 List<StudentSubjectPreference> studentSubjectPreferences = new ArrayList<StudentSubjectPreference>();
		 
		 List<SubjectCategoryDTO>subjectCategoryDTOs = studentDTO.getSubjectCategory();
		 for( SubjectCategoryDTO subjectCategoryDTO : subjectCategoryDTOs) {
			 SubjectCategory subjectCategoryRecord = subjectCategoryRepository.findById(subjectCategoryDTO.getCategoryId())
					 .orElseThrow(()-> new SUBJECT_CATEGORY_NOT_FOUND());
				 StudentSubjectPreference studentSubjectPreference  = new StudentSubjectPreference();
				 studentSubjectPreference.setStudent(user.getStudent());
				 studentSubjectPreference.setSubjectCategory(subjectCategoryRecord);
				 studentSubjectPreferences.add(studentSubjectPreference);
		 }
		 studentSubjectPreferenceRepository.saveAll(studentSubjectPreferences);
		 // prepare response
		 StudentDTO studentOutputDTO = new StudentDTO();
		 studentOutputDTO.setStudentId(user.getStudent().getStudentId());
		 return studentOutputDTO;

	}
	
	public UserDetails updateUser(StudentDTO studentDTO) {
		UserDetails userDetails = userRepository.findById(studentDTO.getUserId())
				.orElseThrow(() -> new USER_NOT_FOUND());
		ContactDetails contactDetails = contactDetailsRepository.findById(userDetails.getContactDetails().getContactId())
				.orElseThrow(() -> new CONTACTDETAILS_NOT_FOUND());
		Address address = modelMapper.map(studentDTO.getAddress(),Address.class);
		contactDetails.setAddress(address);
		userDetails.setGender(studentDTO.getGender());
		return userDetails;
	}
	
	@Override
	public StudentDTO getStudentById(String studentId) {
		logger.debug("In clientApp, StudentServiceImpl, getStudentById method");
		Student student = studentRepository.findById(studentId).
				orElseThrow(() -> new STUDENT_NOT_FOUND());
			//get the category preffered by student.
		
			List<StudentSubjectPreference> studentSubjectPreferences = 
					studentSubjectPreferenceRepository.findByStudentId(studentId)
					.orElseThrow(()->new STUDENT_SUBJECT_PREFERENCE_NOT_FOUND());
			
			List<SubjectCategoryDTO> subjectCategoryDTOs = new ArrayList<SubjectCategoryDTO>();
			for(StudentSubjectPreference studentSubjectPreference : studentSubjectPreferences) {
				SubjectCategoryDTO subjectCategoryDTO = modelMapper.map(studentSubjectPreference.getSubjectCategory(), SubjectCategoryDTO.class);
				subjectCategoryDTOs.add(subjectCategoryDTO);
			}
			StudentDTO studentDTOOutput = new StudentDTO();
			studentDTOOutput.setSubjectCategory(subjectCategoryDTOs);
			modelMapper.map(student,studentDTOOutput);
			return studentDTOOutput;
	}
	
	@Override
	public StudentDTO updateStudentProfile(StudentDTO studentDTO) {

		if(studentDTO.getTabType().equals(ResponseConstants.STUDENT_PERSONAL_INFO_TAB)) {
			UserDetails userDetails = userRepository.findByStudentId(studentDTO.getStudentId())
					.orElseThrow(()->new USER_NOT_FOUND());
				modelMapper.map(studentDTO, userDetails);
				ContactDetails contactDetails = contactDetailsRepository.findById(userDetails.getContactDetails().getContactId()) 
						 .orElseThrow(() -> new CONTACTDETAILS_NOT_FOUND());
				modelMapper.map(studentDTO.getAddress(),contactDetails.getAddress());
				userRepository.save(userDetails);
		}
		
		if(studentDTO.getTabType().equals(ResponseConstants.STUDENT_PROFESSIONAL_INFO_TAB)) {
			Student oldStudentRecord =  studentRepository.findById(studentDTO.getStudentId()).
					orElseThrow(() -> new STUDENT_NOT_FOUND());
			
			List<SubjectCategoryDTO> preferredSubjectCategories = studentDTO.getSubjectCategory();
			List<StudentSubjectPreference> studentSubjectPreferences = studentSubjectPreferenceRepository.findAll();
			
			for(SubjectCategoryDTO preferredSubjectCategory : preferredSubjectCategories) {
				SubjectCategory subjectCategoryRecord = subjectCategoryRepository.findById(preferredSubjectCategory.getCategoryId())
						 .orElseThrow(()-> new SUBJECT_CATEGORY_NOT_FOUND());
				StudentSubjectPreference studentSubjectPreference = studentSubjectPreferenceRepository
						.findByCategoryId(preferredSubjectCategory.getCategoryId());
				if(null == studentSubjectPreference) {
					studentSubjectPreference = new StudentSubjectPreference();
					studentSubjectPreference.setStudent(oldStudentRecord);
					studentSubjectPreference.setSubjectCategory(subjectCategoryRecord);
					studentSubjectPreferences.add(studentSubjectPreference);
				} else {
					throw new STUDENT_PREFERENCE_ALREADY_EXISTS();
				}
			}
			//studentSubjectPreferenceRepository.deleteAll();
			studentSubjectPreferenceRepository.saveAll(studentSubjectPreferences);
			
			modelMapper.map(studentDTO, oldStudentRecord);
			studentRepository.save(oldStudentRecord);
		}
		return studentDTO;
	}
	
}
