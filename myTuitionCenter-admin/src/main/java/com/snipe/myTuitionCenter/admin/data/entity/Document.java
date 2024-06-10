package com.snipe.myTuitionCenter.admin.data.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="document")
public class Document {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="document_id")
	private long documentId;
	
	@JoinColumn(name="user_id", referencedColumnName = "user_id")
	@ManyToOne
	private UserDetails userId;
	
	@Column(name="doc_name")
	private String docName;
	
	@JoinColumn(name="document_type", referencedColumnName = "doc_type_id")
	@ManyToOne
	private DocumentType documentType;
	
	@Column(name="description")
	private String description;
	
	@Column(name="path")
	private String path;
	
	@Column(name="created_date")
	private LocalDate createdDate;
	
	@Column(name="modified_date")
	private LocalDate modifiedDate;
}
