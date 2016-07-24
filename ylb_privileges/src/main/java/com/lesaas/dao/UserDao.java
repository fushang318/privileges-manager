package com.lesaas.dao;

import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import com.lesaas.model.User;

@Repository
public class UserDao extends BaseDao<User>{
	

	protected Logger log = Logger.getLogger(UserDao.class.getName());

	
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
	
	public List<User> listPageUser(User user){
		String sql="select u.user_id,u.username,u.loginname,u.password,r.role_id,r.role_name "
		+"from bas_user u left join bas_role r on u.role_id=r.role_id "
		+"where u.status=0 ";
		if(user.getLoginname()!=null && !user.getLoginname().equals(""))
			sql=sql+"and u.loginname like %"+user.getLoginname()+"%";
		List<Object[]> results = this.createSQLQuery(sql).list();;
		List<User> UserList = new ArrayList<User>(results.size());
		for(Object[] rs : results){
					User pr =new User();
					pr.setUserId(rs[0]==null?null:Integer.valueOf(rs[0].toString()));
					pr.setUsername(rs[1]==null?null:rs[1].toString());
					pr.setLoginname(rs[2]==null?null:rs[2].toString());
					pr.setPassword(rs[3]==null?null:rs[3].toString());
					pr.setRoleId(rs[4]==null?null:Integer.valueOf(rs[4].toString()));
					pr.setRolename(rs[5]==null?null:rs[5].toString());
					UserList.add(pr);
		}	
		return UserList;
	}
	
	
	public void insertUser(User user){
		this.save(user);
	}
	
	public void updateUserBaseInfo(User user){
		String sql="update bas_user set loginname='"+user.getLoginname()+"',username='"+user.getUsername()+"',role_id="+user.getRoleId();
		if(user.getPassword()!=null && !user.getPassword().equals("")){
			sql+=",password="+user.getPassword();
		}
		sql+=" where user_id="+user.getUserId();
		this.batchExecuteSql(sql);
	}
	
	public void deleteUser(Integer userId){
		this.batchExecuteSql("delete from bas_user where user_id="+userId);
	}


	public int getCountByName(String loginname) {
		List<User> list = find("from User where loginname='"+loginname+"'");
		if(list.size()>0){
			return list.size();
		}else{
			return 0;
		}
	}


	public User getUserById(int userId) {
		User user = this.findUnique("from User where user_id=?",userId);
		return user;
	}

	
}
