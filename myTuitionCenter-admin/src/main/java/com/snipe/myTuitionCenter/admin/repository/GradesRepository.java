package com.snipe.myTuitionCenter.admin.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.snipe.myTuitionCenter.admin.data.entity.Grades;

@Repository
public interface GradesRepository extends JpaRepository<Grades,Long>{

	@Query(value="select * from Grades where grade_name like %:gradeName% or grade_id = :gradeId or creation_date = :createdDate", nativeQuery = true)
	List<Grades> findByGradeDto(@Param("gradeId") long gradeId, @Param("gradeName") String gradeName, @Param("createdDate") LocalDate createdDate);

	List<Grades> findByGradeId(long gradeId);

	List<Grades> findBycreationDate(LocalDate creationDate);

	List<Grades> findByName(String gradeName);

}
