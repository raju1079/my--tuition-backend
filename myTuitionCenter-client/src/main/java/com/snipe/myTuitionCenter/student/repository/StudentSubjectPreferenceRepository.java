package com.snipe.myTuitionCenter.student.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.snipe.myTuitionCenter.data.entity.StudentSubjectPreference;

@Repository
public interface StudentSubjectPreferenceRepository extends JpaRepository<StudentSubjectPreference,Long> {
	
	@Query(value="select * from student_subject_preference where student_id=?1",nativeQuery = true)
	public Optional<List<StudentSubjectPreference>> findByStudentId(String studentId);
	@Query(value="select * from student_subject_preference where category_id=?1",nativeQuery = true)
	public StudentSubjectPreference findByCategoryId(long categoryId);
}
