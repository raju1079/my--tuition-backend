package com.snipe.myTuitionCenter.admin.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.snipe.myTuitionCenter.admin.common.CommonConstants;
import com.snipe.myTuitionCenter.admin.config.CustomIdGenerator;
import com.snipe.myTuitionCenter.admin.data.dto.RolesDTO;
import com.snipe.myTuitionCenter.admin.data.entity.Roles;
import com.snipe.myTuitionCenter.admin.exception.RoleAlreadyExsistException;
import com.snipe.myTuitionCenter.admin.exception.RoleNotFoundException;
import com.snipe.myTuitionCenter.admin.repository.RolesRepository;
import com.snipe.myTuitionCenter.admin.service.RolesService;

@Service
public class RolesServiceImpl implements RolesService{
	
	@Autowired
	private RolesRepository rolesRepository;
	
	 @Autowired
	 private CustomIdGenerator customIdGenerator;
	 
	 @Autowired
	 private ModelMapper modelMapper;

	@Override
	public List<RolesDTO> getAllRoles() {
		List<Roles> allRoles = rolesRepository.findAll();
		
		List<RolesDTO> allrolesDTO = allRoles.stream()
                .map(source -> new RolesDTO(source.getRoleId(), source.getRoleName(), source.isActive(), source.getCreationDate(),null))
                .collect(Collectors.toList());
		
		return allrolesDTO;
	}

	@Override
	public RolesDTO addRoles(RolesDTO roleDTO) {
		Roles role = rolesRepository.findByRoleName(roleDTO.getRoleName());
		if(role == null) {
			if(!roleDTO.isActive()) {
				roleDTO.setActive(true);
			}
			role = modelMapper.map(roleDTO, Roles.class);
			role.setRoleId(customIdGenerator.generateId(CommonConstants.ROLE.getValue()));
			role = rolesRepository.save(role); 
			RolesDTO roleDTOResponse = modelMapper.map(role, RolesDTO.class);
			roleDTOResponse.setStatus(CommonConstants.CREATED.getValue());
			return roleDTOResponse;
		}else {
			throw new RoleAlreadyExsistException(CommonConstants.ROLE_EXSIST.getValue());
		}
		
		
	}

	@Override
	public RolesDTO updateRoles(RolesDTO roleDTO, String id) {
		if(rolesRepository.existsById(id)) {
			Roles role = modelMapper.map(roleDTO, Roles.class);
			role = rolesRepository.save(role); 
			RolesDTO roleDTOResponse = modelMapper.map(role, RolesDTO.class);
			roleDTOResponse.setStatus(CommonConstants.UPDATED.getValue());
			return roleDTOResponse;
		}else
		{
			throw new RoleNotFoundException(CommonConstants.ROLENOTAVAILABLE.getValue());
		}
	}

	@Override
	public RolesDTO deactivateRole(String id)throws  RoleNotFoundException{
		if(rolesRepository.existsById(id)) {
			Roles role = rolesRepository.findById(id).get();
			role.setActive(false);
			role = rolesRepository.save(role); 
			RolesDTO roleDTOResponse = modelMapper.map(role, RolesDTO.class);
			roleDTOResponse.setStatus(CommonConstants.DEACTIVATED.getValue());
			return roleDTOResponse;
		}else
		{
			throw new RoleNotFoundException(CommonConstants.ROLENOTAVAILABLE.getValue());
		}
	}
}
