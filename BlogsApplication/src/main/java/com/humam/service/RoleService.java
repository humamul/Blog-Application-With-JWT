package com.humam.service;

import com.humam.io.RoleDto;
import com.humam.model.Role;
import com.humam.model.User;

public interface RoleService {

	Role addRole(RoleDto roleDto);
	
	User addRoleToUser(Integer userId, Integer roleId);
	
}
