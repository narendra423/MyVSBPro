package com.stg.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stg.user.entity.Roles;
import com.stg.user.repository.RoleRepository;

@Service
public class RoleServiceImpl implements RoleService {
	
	@Autowired
	private RoleRepository roleRepo;

	@Override
	public Roles createRole(Roles role) {
		
		return roleRepo.save(role);
	}

	@Override
	public Roles getRoleById(int roleId) {
		
		return roleRepo.findById(roleId).get();
	}

}
