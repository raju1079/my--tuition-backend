package com.snipe.myTuitionCenter.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.snipe.myTuitionCenter.auth.data.entity.LoginCredential;

@Repository
public interface AuthRepository extends JpaRepository<LoginCredential, String> {

	LoginCredential findByEmailId(String emailId);
	
	LoginCredential findByResetPasswordToken(String token);

	LoginCredential findByLoginId(String login_id);

}
