package com.snipe.myTuitionCenter.admin.data.dto;

import com.snipe.myTuitionCenter.admin.data.entity.DocumentType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DocumentDTO {

	private String userId;
	private String docName;
	private DocumentType documentType;
	private String description;
}
