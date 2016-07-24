package com.lesaas.dao;

import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import com.lesaas.model.Role;
import com.lesaas.model.User;

@Repository
public class RoleDao extends BaseDao<Role>{
	

	protected Logger log = Logger.getLogger(RoleDao.class.getName());

	
	public User getUserInfo(User user){
		List<User> list = find("from User where loginname='"+user.getLoginname()+"' and password='"+user.getPassword()+"'");
		if(list.size()>0){
			return list.get(0);
		}else{
			return null;
		}
	}


	public List<Object[]> getUserAndRoleById(Integer userId) {
			String sql = "select u.user_id,u.username,u.loginname,u.password,r.rights " 
					+"from bas_user u left join bas_role r on u.role_id=r.role_id "
					+"where u.status=0 and u.user_id=?";
		List<Object[]> results = this.createSQLQuery(sql,userId).list();
		return results;
	}
	
	public List<Role> listAllRoles(){
		List<Role> list = find("from Role where 1=1");	
		return list;
	}
	
	
	public void insertRole(Role role){
		this.batchExecuteSql("insert into bas_role (role_name) values ('"+role.getRoleName()+"')");
	}
	
	public void updateRoleBaseInfo(Role role){
		this.batchExecuteSql("update bas_role set role_name='"+role.getRoleName()+"' where role_id="+role.getRoleId());
	}
	
	public void deleteRoleById(Integer roleId){
		this.batchExecuteSql("delete from bas_role where role_id="+roleId);
	}


	public int getCountByName(Role role) {
		List<Role> list = find("from Role where role_name='"+role.getRoleName()+"'");
		if(list.size()>0){
			return list.size();
		}else{
			return 0;
		}
	}


	public Role getRoleById(int roleId) {
		Role role = this.findUnique("from Role where role_id=?",roleId);
		return role;
	}


	public void updateRoleRights(Role role) {
		this.batchExecuteSql("update bas_role set rights='"+role.getRights()+"' where role_id="+role.getRoleId());
	}

	
}
