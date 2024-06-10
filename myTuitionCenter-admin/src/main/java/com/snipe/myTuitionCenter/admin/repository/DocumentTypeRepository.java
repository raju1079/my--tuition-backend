package com.snipe.myTuitionCenter.admin.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.snipe.myTuitionCenter.admin.data.entity.DocumentType;

public interface DocumentTypeRepository extends JpaRepository<DocumentType, Long>{

	DocumentType findByName(String name);

}
