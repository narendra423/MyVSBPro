package com.stg.user.service;

import com.stg.user.entity.Roles;

public interface RoleService {
	public abstract Roles createRole(Roles role);
	
	public abstract Roles getRoleById(int roleId);
}
