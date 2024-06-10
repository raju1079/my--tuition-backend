package com.snipe.myTuitionCenter.admin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.snipe.myTuitionCenter.admin.data.entity.ContactDetails;

@Repository
public interface ContactDetailsRepository extends JpaRepository<ContactDetails, Long>{
	
	ContactDetails findByEmailId(String emailId);
	ContactDetails findByPhoneNo(long phoneNo);
	boolean existsByPhoneNo(long phoneNo);

}

