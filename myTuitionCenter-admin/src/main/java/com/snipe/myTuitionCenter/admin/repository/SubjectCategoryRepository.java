package com.snipe.myTuitionCenter.admin.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.snipe.myTuitionCenter.admin.data.entity.SubjectCategory;

public interface SubjectCategoryRepository extends JpaRepository<SubjectCategory, Long>{

	@Query(value="select * from subject_category where grade_grade_id in (select grade_id from grades where grade_name like %:searchString% or description like %:searchString%) or subject_subject_id in(select subject_id from  subjects where subject_name like %:searchString%)", nativeQuery = true)
	List<SubjectCategory> findBySubjectNameOrGradeName(@Param("searchString") String searchString);

}
