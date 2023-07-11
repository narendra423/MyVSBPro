package com.stg.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stg.user.entity.Permissions;
import com.stg.user.repository.PermissionRepository;

@Service
public class PermissionServiceImpl implements PermissionService {
	
	@Autowired
	private PermissionRepository permissionRepo;

	@Override
	public Permissions createPermission(Permissions permission) {
		
		return permissionRepo.save(permission);
	}

	@Override
	public Permissions getPermissionById(int permissionId) {
		
		return permissionRepo.findById(permissionId).get();
	}

}
