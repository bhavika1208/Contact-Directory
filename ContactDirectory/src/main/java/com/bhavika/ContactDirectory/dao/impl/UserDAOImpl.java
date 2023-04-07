package com.bhavika.ContactDirectory.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.bhavika.ContactDirectory.DbConnection;
import com.bhavika.ContactDirectory.dao.UserDAO;
import com.bhavika.ContactDirectory.entity.Contact;
import com.bhavika.ContactDirectory.entity.State;
import com.bhavika.ContactDirectory.entity.User;
import com.zaxxer.hikari.util.ClockSource.MillisecondClockSource;

@Component
public class UserDAOImpl implements UserDAO{
	
	public UserDAOImpl() {
		
	}

	
	DbConnection DbCon = new DbConnection();
	Connection conn = DbCon.getConn();

	@Override
	public List<User> getUsers() {
		List<User> theUsers = new ArrayList<>();
		ContactDAOImpl contactDAOImpl = new ContactDAOImpl();

		try {
			String query = "SELECT * FROM users WHERE isActive = 1";
			PreparedStatement stmt = conn.prepareStatement(query);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				User user = null;
				user = new User();
				user.setUserId(rs.getInt("userId"));
				user.setUserName(rs.getString("userName"));
				user.setUserEmailId(rs.getString("userEmail"));
				user.setUserPassword(rs.getString("userPassword"));
				user.setUserPhoneNo(rs.getString("userPhoneNo"));
				user.setUserAddress(rs.getString("userAddress"));
				user.setCreatedAt(rs.getTimestamp("createdAt"));
				user.setModifiedAt(rs.getTimestamp("modifiedAt"));
				user.setActive(rs.getBoolean("isActive"));
				
				List<Contact> theContacts = null;
				theContacts = contactDAOImpl.getContactsByUser(rs.getInt("userId"));
				user.setTheContacts(theContacts);
				
				theUsers.add(user);
				System.out.println(user);
			}
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return theUsers;
	}

	@Override
	public User getUserById(int UserId) {
		User user =null;
		
		ContactDAOImpl contactDAOImpl = new ContactDAOImpl();
		
		try {
			String query = "SELECT * FROM users WHERE userId = ? AND isActive = 1";
			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.setInt(1, UserId);
			ResultSet rs = stmt.executeQuery();
			if(rs.next()) {
				user = new User();
				user.setUserId(rs.getInt("userId"));
				user.setUserName(rs.getString("userName"));
				user.setUserEmailId(rs.getString("userEmail"));
				user.setUserPassword(rs.getString("userPassword"));
				user.setUserPhoneNo(rs.getString("userPhoneNo"));
				user.setUserAddress(rs.getString("userAddress"));
				user.setCreatedAt(rs.getTimestamp("createdAt"));
				user.setModifiedAt(rs.getTimestamp("modifiedAt"));
				user.setActive(rs.getBoolean("isActive"));
				
				List<Contact> theContacts = null;
				theContacts = contactDAOImpl.getContactsByUser(UserId);
				user.setTheContacts(theContacts);
				return user;
			}
			
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return user;
	}

	@Override
	public boolean findUserByEmail(String userEmail) {
		try {
			String query = "SELECT * FROM users WHERE userEmail = ?";
			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.setString(1, userEmail);
			ResultSet rs = stmt.executeQuery();
			if(rs.next()) {
				return true;
			}
			
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return false;
	}

	@Override
	public String RegisterUser(User user) {

		user.getUserEmailId();
		try {
			boolean a =  findUserByEmail(user.getUserEmailId());
			if(a) {
				return "Email Already Exists";
			}else {
				try {
					String query = "INSERT INTO users (userName, userPassword, userEmail, userPhoneNo, userAddress, createdAt, modifiedAt) VALUES (?, ?, ?, ?, ?, ?, ?)";
					PreparedStatement stmt = conn.prepareStatement(query);
					stmt.setString(1, user.getUserName());
					stmt.setString(2, user.getUserPassword());
					stmt.setString(3, user.getUserEmailId());
					stmt.setString(4, user.getUserPhoneNo());
					stmt.setString(5, user.getUserAddress());
					stmt.setTimestamp(6, new Timestamp(System.currentTimeMillis()));
					stmt.setTimestamp(7, new Timestamp(System.currentTimeMillis()));
					
					int rs = stmt.executeUpdate();
					System.out.println(rs);
					return "User added Successfully";
				}catch (Exception e) {
					System.out.println(e.getMessage());
				}
			}
			
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return "Some Problem Occured in Registration";
		
	}

	@Override
	public boolean LoginUser(User theUser) {
		
		try{
			String query = "SELECT * FROM users WHERE userEmail = ? AND userPassword = ?";
			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.setString(1, theUser.getUserEmailId());
			stmt.setString(2, theUser.getUserPassword());
			ResultSet rs = stmt.executeQuery();
			if(rs.next()) {
				return true;
			}
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return false;
	}

	@Override
	public int updateUser(User theUser) {

		try {
			String query = "UPDATE users SET username = ?, userPassword = ?, userEmail = ?, userPhoneNo = ?, userAddress = ?, modifiedAt = ?  WHERE userId = ?";
			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.setString(1, theUser.getUserName());
			stmt.setString(2, theUser.getUserPassword());
			stmt.setString(3, theUser.getUserEmailId());
			stmt.setString(4, theUser.getUserPhoneNo());
			stmt.setString(5, theUser.getUserAddress());
			stmt.setTimestamp(6,new Timestamp(System.currentTimeMillis()));
			stmt.setInt(7, theUser.getUserId());
			return stmt.executeUpdate();
			
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return 0;
	}
	
	@Override
	public int deleteUser(int UserId) {
		try {
			String query = "UPDATE users SET isActive = 0 WHERE userId = ?";
			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.setInt(1, UserId);
			return stmt.executeUpdate();
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return 0;
	}

}
