package com.snipe.myTuitionCenter.admin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.snipe.myTuitionCenter.admin.data.entity.UserDetails;

@Repository
public interface UserDetailsRepository extends JpaRepository<UserDetails, String>{
	
	@Query(value = "SELECT * FROM user_details ud WHERE ud.contact_id = :contactId", nativeQuery = true)
	UserDetails findByContactId(@Param("contactId") long contactId);
	
	UserDetails findByUserName(String userName);

	boolean existsByUserName(String userName);

}