package com.lesaas.mapper;

import java.util.List;

import com.lesaas.model.Role;


public interface RoleMapper {

	List<Role> listAllRoles();
	
	Role getRoleById(int roleId);
	
	void insertRole(Role role);
	
	void updateRoleBaseInfo(Role role);
	
	void deleteRoleById(int roleId);
	
	int getCountByName(Role role);
	
	void updateRoleRights(Role role);
}
