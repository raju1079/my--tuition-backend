package com.snipe.myTuitionCenter.admin.service;

import java.util.List;

import org.springframework.stereotype.Service;
import com.snipe.myTuitionCenter.admin.data.dto.SubjectCategoryDTO;

@Service
public interface SubjectCategoryService {

	public SubjectCategoryDTO addSubjectCategory(SubjectCategoryDTO subjectCategoryDto);
	public SubjectCategoryDTO updateSubjectCategory(SubjectCategoryDTO subjectCategoryDto);
	public List<SubjectCategoryDTO> GetAllCategories();
	public void deactivateCategory(long CategoryId);
	public List<SubjectCategoryDTO> searchCategory(String searchString);
	public void activateCategory(long categoryId);
	public SubjectCategoryDTO searchByCategoryId(long categoryId);
}
