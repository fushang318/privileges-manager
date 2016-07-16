package com.lesaas.model;

import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table(name = "bas_role")
public class Role {
	private Integer roleId;
	private String roleName;
	private String rights;
	public Integer getRoleId() {
		return roleId;
	}
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public String getRights() {
		return rights;
	}
	public void setRights(String rights) {
		this.rights = rights;
	}
	
}
