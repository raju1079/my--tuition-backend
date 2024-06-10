package com.snipe.myTuitionCenter.admin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.snipe.myTuitionCenter.admin.data.entity.LoginCredential;

@Repository
public interface AuthRepository extends JpaRepository<LoginCredential, String> {

}
