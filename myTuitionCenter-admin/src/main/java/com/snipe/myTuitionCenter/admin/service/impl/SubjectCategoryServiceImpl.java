package com.snipe.myTuitionCenter.admin.service.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.snipe.myTuitionCenter.admin.data.dto.SubjectCategoryDTO;
import com.snipe.myTuitionCenter.admin.data.entity.SubjectCategory;
import com.snipe.myTuitionCenter.admin.repository.SubjectCategoryRepository;
import com.snipe.myTuitionCenter.admin.service.SubjectCategoryService;

@Service
public class SubjectCategoryServiceImpl implements SubjectCategoryService {

	@Autowired
	private SubjectCategoryRepository subCatRepo;
	
	@Autowired
	ModelMapper modelMapper;
		
	@Override
	public SubjectCategoryDTO addSubjectCategory(SubjectCategoryDTO subjectCategoryDto) {
		

		SubjectCategory subjectCategory = modelMapper.map(subjectCategoryDto,SubjectCategory.class);
		SubjectCategory cat = subCatRepo.save(subjectCategory);
		subjectCategoryDto.setCategoryId(cat.getCategoryId());
		
		return subjectCategoryDto;
	}

	@Override
	public SubjectCategoryDTO updateSubjectCategory(SubjectCategoryDTO subjectCategoryDto) {
		
		SubjectCategory cat = subCatRepo.findById(subjectCategoryDto.getCategoryId()).get();
		
		cat.setCreationDate(subjectCategoryDto.getCreationDate());
		cat.setModifiedDate(subjectCategoryDto.getModifiedDate());
		cat.setGrade(subjectCategoryDto.getGrade());
		cat.setSubject(subjectCategoryDto.getSubject());
		cat.setActive(false);
		
		subCatRepo.save(cat);
		
		return subjectCategoryDto;
	}

	@Override
	public List<SubjectCategoryDTO> GetAllCategories() {
		
		List<SubjectCategory> categoriesList = subCatRepo.findAll();
		System.out.println(categoriesList);
		
		List<SubjectCategoryDTO> list = new ArrayList<>();
		
		for(SubjectCategory subCat : categoriesList) {
			
			SubjectCategoryDTO dto = modelMapper.map(subCat, SubjectCategoryDTO.class);
			list.add(dto);
		}
		return list;
	}

	@Override
	public void deactivateCategory(long categoryId) {
		
		SubjectCategory cat = subCatRepo.findById(categoryId).get();
		cat.setActive(false);
		cat.setModifiedDate(LocalDate.now());
		subCatRepo.save(cat);
		
	}

	@Override
	public List<SubjectCategoryDTO> searchCategory(String searchString) {
		
		List<SubjectCategoryDTO> list = new ArrayList<>();
		
		List<SubjectCategory> categoriesList = subCatRepo.findBySubjectNameOrGradeName(searchString);
		
		for(SubjectCategory subCat : categoriesList) {
			
			SubjectCategoryDTO dto = modelMapper.map(subCat, SubjectCategoryDTO.class);
			list.add(dto);
		}
		return list;
	}

	@Override
	public void activateCategory(long categoryId) {
		SubjectCategory cat = subCatRepo.findById(categoryId).get();
		cat.setActive(true);
		cat.setModifiedDate(LocalDate.now());
		subCatRepo.save(cat);
		
	}

	@Override
	public SubjectCategoryDTO searchByCategoryId(long categoryId) {
		SubjectCategory cat = subCatRepo.findById(categoryId).get();
		SubjectCategoryDTO dto = modelMapper.map(cat, SubjectCategoryDTO.class);
		return dto;
	}

}
