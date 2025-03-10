package com.snipe.myTuitionCenter.admin.data.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="document_type")
public class DocumentType {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="doc_type_id")
	private long docTypeId;
	
	@Column(name="name")
	private String name;
	

}
