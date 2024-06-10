package com.snipe.myTuitionCenter.admin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.snipe.myTuitionCenter.admin.data.entity.Roles;

@Repository
public interface RolesRepository extends JpaRepository<Roles, String>{
	
	Roles findByRoleName(String roleName);

}

