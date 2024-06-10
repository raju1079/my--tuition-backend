package com.snipe.myTuitionCenter.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.snipe.myTuitionCenter.admin.common.CommonConstants;
import com.snipe.myTuitionCenter.admin.data.dto.RolesDTO;
import com.snipe.myTuitionCenter.admin.exception.RoleNotFoundException;
import com.snipe.myTuitionCenter.admin.service.RolesService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;




@Controller
@RequestMapping("/mytuitioncenter/admin")
@Tag(name = "Admin", description = "Role management APIs")
public class RolesController {

	@Autowired
	private RolesService roleService;
	
	@Operation(summary = "Add new Role", description = "Adding a new Role to the Database")
	@PostMapping("/role")
	public ResponseEntity<RolesDTO> addRole(@RequestBody RolesDTO role) {
		
	 return new ResponseEntity<RolesDTO>(roleService.addRoles(role), HttpStatus.CREATED);
	 
	}
	
	@Operation(summary = "Get all Roles", description = "Retrieves all roles from the database.")
	@GetMapping("/role")
	public ResponseEntity<List<RolesDTO>> getAllRoles() {
		return new ResponseEntity<List<RolesDTO>>(roleService.getAllRoles(), HttpStatus.OK);
	}
	
	@Operation(summary = "Update a specific Role", description = "Update the exsisting role in database")
	@PutMapping("/role/{id}")
	public ResponseEntity<RolesDTO> updateRole(@PathVariable String id, @RequestBody RolesDTO role) {
		return new ResponseEntity<RolesDTO>(roleService.updateRoles(role,id), HttpStatus.OK);
	}
	
	@Operation(summary = "Delete a Role", description = "Deactivate a role from Database(soft delete)")
	@PutMapping("/deactivateRole/{id}")
	public ResponseEntity<RolesDTO> deactivateRole(@PathVariable String id) {
		try {
			return new ResponseEntity<RolesDTO>(roleService.deactivateRole(id), HttpStatus.OK);
		}catch (RoleNotFoundException e) {
			throw new RoleNotFoundException(CommonConstants.ROLENOTAVAILABLE.getValue());
		}
	}
	
}
