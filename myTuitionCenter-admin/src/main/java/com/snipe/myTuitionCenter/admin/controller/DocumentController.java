package com.snipe.myTuitionCenter.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.snipe.myTuitionCenter.admin.common.FileStorageProperties;
import com.snipe.myTuitionCenter.admin.data.dto.DocumentDTO;
import com.snipe.myTuitionCenter.admin.data.entity.DocumentType;
import com.snipe.myTuitionCenter.admin.repository.DocumentTypeRepository;
import com.snipe.myTuitionCenter.admin.service.DocumentService;

@RestController
public class DocumentController {
	
	@Autowired
	DocumentService documentService;
	
	@Autowired
	FileStorageProperties fileStorageProperties;
	
	@Autowired
	DocumentTypeRepository docTypeRepo;
	
	@PostMapping(value="/api/upload-document", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
	public ResponseEntity<String> uploadDocument(@RequestPart("file") MultipartFile file, @RequestParam String userId, @RequestParam String docType) {
		
		System.out.println("In controller");
		String filePath = fileStorageProperties.getUploadDir();
		System.out.println(file.getOriginalFilename());
	
		DocumentDTO dto = new DocumentDTO();
		
		DocumentType dt = docTypeRepo.findByName(docType);
		
		dto.setUserId(userId);
		dto.setDocName(file.getOriginalFilename());
		dto.setDocumentType(dt);
		
		documentService.uploadDocument(filePath,file,dto);
		
		return ResponseEntity.status(HttpStatus.CREATED).body("File Uploaded successfully");
	}
	
	@GetMapping("/files/{filename:.+}")
	  @ResponseBody
	  public ResponseEntity<Resource> getFile(@PathVariable String filename, @RequestParam String userId) {
	    Resource resource = documentService.load(filename,userId);
	    HttpHeaders headers = new HttpHeaders();
        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
        headers.add("Pragma", "no-cache");
        headers.add("Expires", "0");
        
        return ResponseEntity.ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(resource);
    }
}
