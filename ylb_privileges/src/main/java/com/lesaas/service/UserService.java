package com.lesaas.service;


import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.lesaas.mapper.UserMapper;
import com.lesaas.model.User;




@Service
@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)//所有方法不加事物
public class UserService {
	
	private UserMapper userMapper;
	
	public UserMapper getUserMapper() {
		return userMapper;
	}

	public void setUserMapper(UserMapper userMapper) {
		this.userMapper = userMapper;
	}
	public User getUserByNameAndPwd(String username,String password){
		User user = new User();
		user.setLoginname(username);
		user.setPassword(password);
		return userMapper.getUserInfo(user);
	}

	public User getUserAndRoleById(Integer userId) {
		// TODO Auto-generated method stub
		return userMapper.getUserAndRoleById(userId);
	}

	public List<User> listPageUser(User user) {
		// TODO Auto-generated method stub
		return userMapper.listPageUser(user);
	}

	public boolean insertUser(User user) {
		int count = userMapper.getCountByName(user.getLoginname());
		if(count>0){
			return false;
		}else{
			userMapper.insertUser(user);
			return true;
		}
	}

	public void updateUserBaseInfo(User user) {
		 userMapper.updateUserBaseInfo(user);
	}

	public User getUserById(int userId) {
		return userMapper.getUserById(userId);
	}

	public void deleteUser(int userId) {
		userMapper.deleteUser(userId);
	}


	
}
