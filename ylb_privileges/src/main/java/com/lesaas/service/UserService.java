package com.lesaas.service;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.lesaas.dao.UserDao;
import com.lesaas.model.User;




@Service
@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)//所有方法不加事物
public class UserService {
	
	@Autowired
	private UserDao userDao;
	
	public User getUserByNameAndPwd(String username,String password){
		User user = new User();
		user.setLoginname(username);
		user.setPassword(password);
		return userDao.getUserInfo(user);
	}

	public List<Object[]> getUserAndRoleById(Integer userId) {
		return userDao.getUserAndRoleById(userId);
	}

	public List<User> listPageUser(User user) {
		return userDao.listPageUser(user);
	}

	public boolean insertUser(User user) {
		int count = userDao.getCountByName(user.getLoginname());
		if(count>0){
			return false;
		}else{
			user.setStatus(0);
			userDao.insertUser(user);
			return true;
		}
	}

	public void updateUserBaseInfo(User user) {
		userDao.updateUserBaseInfo(user);
	}

	public User getUserById(int userId) {
		return userDao.getUserById(userId);
	}

	public void deleteUser(int userId) {
		userDao.deleteUser(userId);
	}


	
}
