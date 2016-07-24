package com.lesaas.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.lesaas.dao.RoleDao;
import com.lesaas.model.Role;



@Service
@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)//所有方法不加事物
public class RoleService {
	@Autowired
	private RoleDao roleDao;


	public List<Role> listAllRoles() {
		return roleDao.listAllRoles();
	}
	
	public void deleteRoleById(int roleId) {
		roleDao.deleteRoleById(roleId);
	}

	public Role getRoleById(int roleId) {
		return roleDao.getRoleById(roleId);
	}

	public boolean insertRole(Role role) {
		if(roleDao.getCountByName(role)>0)
			return false;
		else{
			roleDao.insertRole(role);
			return true;
		}
	}

	public boolean updateRoleBaseInfo(Role role) {
		if(roleDao.getCountByName(role)>0)
			return false;
		else{
			roleDao.updateRoleBaseInfo(role);
			return true;
		}
	}
	
	public void updateRoleRights(Role role) {
		roleDao.updateRoleRights(role);
	}

	
}
