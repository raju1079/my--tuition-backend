package com.snipe.myTuitionCenter.data.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.snipe.myTuitionCenter.data.entity.Availability;
import com.snipe.myTuitionCenter.data.entity.Board;
import com.snipe.myTuitionCenter.data.entity.Status;
import com.snipe.myTuitionCenter.data.entity.StudyGroupType;
import com.snipe.myTuitionCenter.data.entity.Tuitiontype;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(Include.NON_NULL)
public class StudentDTO {
	private static final long serialVersionUID = -4960554755642362234L;
	private String userId;
	private String studentId;
	private String gender;
	private String userName;
	private List<SubjectCategoryDTO> subjectCategory;
	private String modeOfStudy;
	private String tutorGenderPreference;
	private String maxfees;
	private AddressDTO address;
	@Enumerated(EnumType.STRING)
	Status status;
	@Enumerated(EnumType.STRING)
	StudyGroupType studyGroupType;
	@Enumerated(EnumType.STRING)
	Board board;
	@Enumerated(EnumType.STRING)
	Tuitiontype tuitionType;
	private String tabType;
	@Enumerated(EnumType.STRING)
	Availability availability;

}
