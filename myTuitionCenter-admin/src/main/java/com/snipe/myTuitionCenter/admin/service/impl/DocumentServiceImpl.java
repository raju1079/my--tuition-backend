package com.snipe.myTuitionCenter.admin.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.snipe.myTuitionCenter.admin.common.FileStorageProperties;
import com.snipe.myTuitionCenter.admin.data.dto.DocumentDTO;
import com.snipe.myTuitionCenter.admin.data.entity.Document;
import com.snipe.myTuitionCenter.admin.repository.DocumentRepository;
import com.snipe.myTuitionCenter.admin.service.DocumentService;

@Service
public class DocumentServiceImpl implements DocumentService{
	
	@Autowired
	DocumentRepository docRepo;
	
	@Autowired
	ModelMapper modelMapper;
	
	@Autowired
	FileStorageProperties fileStorageProperties;
	
	
	@Override
	public void uploadDocument(String filePath, MultipartFile file, DocumentDTO dto) {
		
		Path root = Paths.get(filePath+"/"+dto.getUserId());
		
		 try {
		      Files.createDirectories(root);
		    } catch (IOException e) {
		      throw new RuntimeException("Could not initialize folder for upload!");
		    }
		 save(file,root);
		 
		 Document docentity = modelMapper.map(dto,Document.class);
		 docentity.setPath(filePath+"/"+dto.getUserId());
		 docentity.setCreatedDate(LocalDate.now());
		 docentity.setModifiedDate(LocalDate.now());
		 docRepo.save(docentity); 
	}

	public void save(MultipartFile file, Path root) {
		try {
			Files.copy(file.getInputStream(), root.resolve(file.getOriginalFilename()));
		} catch (Exception e) {
			if (e instanceof FileAlreadyExistsException) {
				throw new RuntimeException("A file of that name already exists.");
			}

			throw new RuntimeException(e.getMessage());
		}
	}

	@Override
	public Resource load(String filename, String userId) {
		
		Document doc = docRepo.findByUserIdAndFileName(filename, userId);
		String filePath = doc.getPath();
		
		 File file = new File(filePath+"/"+filename);
		 InputStreamResource resource = null;
		try {
			resource = new InputStreamResource(new FileInputStream(file));
			 
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return resource;
			
	}

}
