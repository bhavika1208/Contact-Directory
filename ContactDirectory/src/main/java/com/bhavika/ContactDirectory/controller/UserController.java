package com.bhavika.ContactDirectory.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bhavika.ContactDirectory.dao.impl.UserDAOImpl;
import com.bhavika.ContactDirectory.entity.User;
import com.bhavika.ContactDirectory.responses.Response;
import com.bhavika.ContactDirectory.responses.ResponseException;

@CrossOrigin
@RestController
@RequestMapping("/cd")
public class UserController {

	@Autowired
	UserDAOImpl userDAOImpl;
	
	@GetMapping("/users")
	private List<User> getUsers() throws ResponseException {
		List<User> theUsers = null;
		theUsers = userDAOImpl.getUsers();
		if(theUsers == null) {
			throw new ResponseException("No users found!");
		}
		return theUsers;
	}
	
	
	@GetMapping("/users/{userId}")
	private User getUser(@PathVariable int userId) throws ResponseException {
		User user = null;
		user = userDAOImpl.getUserById(userId);
		if(user == null) {
			throw new ResponseException("No user found!");
		}
		return user;
	}
	
//	@GetMapping("/users/user/{userEmail}")
//	private String getUserByEmail(@PathVariable String userEmail){
//		if(userDAOImpl.findUserByEmail(userEmail)) {
//			return "Email Alreay exist";			
//		}
//		return "Email Does not exist";	
//	}
	
	@PostMapping("/users")
	private String registerUser(@RequestBody User user) throws ResponseException {
		String msgString = userDAOImpl.RegisterUser(user);
		return msgString;
	}
	
	@GetMapping("/users/user")
	private String loginUser(@RequestBody User user) throws ResponseException {
		if(userDAOImpl.LoginUser(user)) {
			return "Logged in Successfully";			
		}
		return "Enter Correct Credientials";	
	}
	
	@PutMapping("/users")
	private Response updateUser(@RequestBody User user) throws ResponseException {
		int res = userDAOImpl.updateUser(user);
		if (res <= 0) {
			throw new ResponseException("Bad Request!");
		} else {
			return new Response(HttpStatus.OK.value(), "Updated successfully", System.currentTimeMillis());
		}	
	}
	
	@DeleteMapping("/users/{userId}")
	private Response deleteUser(@PathVariable int userId) throws ResponseException {
		int res = userDAOImpl.deleteUser(userId);
		if (res <= 0) {
			throw new ResponseException("Bad Request!");
		} else {
			return new Response(HttpStatus.OK.value(), "Deleted Successfully", System.currentTimeMillis());
		}
	}
	
}
