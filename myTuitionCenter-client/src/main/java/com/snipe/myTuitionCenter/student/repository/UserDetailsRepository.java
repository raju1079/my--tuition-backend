package com.snipe.myTuitionCenter.student.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.snipe.myTuitionCenter.data.entity.UserDetails;

@Repository
public interface UserDetailsRepository extends JpaRepository<UserDetails, String> {
	
	@Query(value="select * from user_details where student_id=?1",nativeQuery = true)
	public Optional<UserDetails> findByStudentId(String studentId);
	

}