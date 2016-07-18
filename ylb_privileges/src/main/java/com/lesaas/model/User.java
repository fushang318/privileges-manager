package com.lesaas.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "bas_user")
public class User {
	private Integer userId;
	private String loginname;
	private String username;
	private String password;
	private String rights;
	private Integer status;
	private Integer roleId;
	
	private Role role;
	
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getRights() {
		return rights;
	}
	public void setRights(String rights) {
		this.rights = rights;
	}
	public String getLoginname() {
		return loginname;
	}
	public void setLoginname(String loginname) {
		this.loginname = loginname;
	}
	public Integer getRoleId() {
		return roleId;
	}
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
	public Role getRole() {
		// TODO Auto-generated method stub
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	
}
