package com.lesaas.mapper;

import java.util.List;

import com.lesaas.model.User;




public interface UserMapper  {
	
	User getUserInfo(User user);

	User getUserAndRoleById(Integer userId);

	List<User> listPageUser(User user);

	void insertUser(User user);

	Object updateUserBaseInfo(User user);

	User getUserById(int userId);

	void deleteUser(int userId);

	int getCountByName(String loginname);
}
