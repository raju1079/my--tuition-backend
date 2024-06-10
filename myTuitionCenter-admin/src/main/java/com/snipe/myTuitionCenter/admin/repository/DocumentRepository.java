package com.snipe.myTuitionCenter.admin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.snipe.myTuitionCenter.admin.data.entity.Document;

public interface DocumentRepository extends JpaRepository<Document, Long> {

	@Query(value="select * from document where doc_name= :filename and user_id= :userId", nativeQuery = true)
	Document findByUserIdAndFileName(@Param("filename")String filename, @Param("userId") String userId);

}
