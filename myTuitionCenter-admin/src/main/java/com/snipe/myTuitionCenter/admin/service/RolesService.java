package com.snipe.myTuitionCenter.admin.service;

import java.util.List;

import com.snipe.myTuitionCenter.admin.data.dto.RolesDTO;

public interface RolesService {
	
	List<RolesDTO> getAllRoles();

	RolesDTO addRoles(RolesDTO role);
	  
	RolesDTO updateRoles(RolesDTO role, String id);
	 
	RolesDTO deactivateRole(String id);
}
