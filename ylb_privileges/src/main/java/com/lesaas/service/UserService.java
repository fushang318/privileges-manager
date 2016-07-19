package com.lesaas.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.lesaas.mapper.UserMapper;
import com.lesaas.model.User;




@Service
@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)//所有方法不加事物
public class UserService {
	
	private UserMapper userMapper;
	
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

	public UserMapper getUserMapper() {
		return userMapper;
	}

	public void setUserMapper(UserMapper userMapper) {
		this.userMapper = userMapper;
	}
	
}
