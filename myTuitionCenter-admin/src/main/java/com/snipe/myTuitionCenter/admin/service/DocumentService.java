package com.snipe.myTuitionCenter.admin.service;

import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.snipe.myTuitionCenter.admin.data.dto.DocumentDTO;

@Service
public interface DocumentService {

	public void uploadDocument(String filePath, MultipartFile file, DocumentDTO dto);

	public Resource load(String filename, String userId);
}
