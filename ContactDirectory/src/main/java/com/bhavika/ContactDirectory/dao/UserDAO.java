package com.bhavika.ContactDirectory.dao;

import java.util.List;

import com.bhavika.ContactDirectory.entity.User;


public interface UserDAO {
	
	public List<User> getUsers();
	
	public User getUserById(int UserId);
	
//	to use in registration
	public boolean findUserByEmail(String userEmail);

// 	same as add user (call findUserByEmail in this)
	public String RegisterUser(User theUser);
	
	public boolean LoginUser(User theUser);
	
	public int updateUser(User theUser);
	
	public int deleteUser(int UserId);

}
