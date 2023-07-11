package com.stg.user.service;

import com.stg.user.entity.Permissions;

public interface PermissionService {
	public abstract Permissions createPermission(Permissions permission);
	
	public abstract Permissions getPermissionById(int permissionId);
}
