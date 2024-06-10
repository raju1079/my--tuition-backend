package com.snipe.myTuitionCenter.auth.repository;


import com.snipe.myTuitionCenter.auth.data.entity.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDetailsRepository extends JpaRepository<UserDetails, String> {

    @Query(value="select * from user_details where student_id=?1", nativeQuery = true)
    UserDetails findbyStudentId(String studentId);
    
    @Query(value="select * from user_details where tutor_id=?1", nativeQuery = true)
    UserDetails findbyTutorId(String tutorId);

}