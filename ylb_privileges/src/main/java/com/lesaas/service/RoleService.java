package com.lesaas.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.lesaas.mapper.RoleMapper;
import com.lesaas.model.Role;



@Service
@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)//所有方法不加事物
public class RoleService {
	private RoleMapper roleMapper;

	public RoleMapper getRoleMapper() {
		return roleMapper;
	}

	public void setRoleMapper(RoleMapper roleMapper) {
		this.roleMapper = roleMapper;
	}

	public List<Role> listAllRoles() {
		return roleMapper.listAllRoles();
	}
	
	public void deleteRoleById(int roleId) {
		roleMapper.deleteRoleById(roleId);
	}

	public Role getRoleById(int roleId) {
		return roleMapper.getRoleById(roleId);
	}

	public boolean insertRole(Role role) {
		if(roleMapper.getCountByName(role)>0)
			return false;
		else{
			roleMapper.insertRole(role);
			return true;
		}
	}

	public boolean updateRoleBaseInfo(Role role) {
		if(roleMapper.getCountByName(role)>0)
			return false;
		else{
			roleMapper.updateRoleBaseInfo(role);
			return true;
		}
	}
	
	public void updateRoleRights(Role role) {
		roleMapper.updateRoleRights(role);
	}

	
}
