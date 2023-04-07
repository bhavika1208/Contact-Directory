package com.bhavika.ContactDirectory.entity;

import java.sql.Timestamp;
import java.util.List;

public class User {

	private int userId;
	private String userName;
	private String userPassword;
	private String userEmailId;
	private String userAddress;
	private String userPhoneNo;
	private boolean isActive;
	private Timestamp createdAt;
	private Timestamp modifiedAt;
	
	private List<Contact> theContacts;
	
	public User() {

	}

	public User(int userId, String userName, String userPassword, String userEmailId, String userAddress,
			String userPhoneNo, boolean isActive, Timestamp createdAt, Timestamp modifiedAt,
			List<Contact> theContacts) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.userPassword = userPassword;
		this.userEmailId = userEmailId;
		this.userAddress = userAddress;
		this.userPhoneNo = userPhoneNo;
		this.isActive = isActive;
		this.createdAt = createdAt;
		this.modifiedAt = modifiedAt;
		this.theContacts = theContacts;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getUserEmailId() {
		return userEmailId;
	}

	public void setUserEmailId(String userEmailId) {
		this.userEmailId = userEmailId;
	}

	public String getUserAddress() {
		return userAddress;
	}

	public void setUserAddress(String userAddress) {
		this.userAddress = userAddress;
	}

	public String getUserPhoneNo() {
		return userPhoneNo;
	}

	public void setUserPhoneNo(String userPhoneNo) {
		this.userPhoneNo = userPhoneNo;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public Timestamp getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	public Timestamp getModifiedAt() {
		return modifiedAt;
	}

	public void setModifiedAt(Timestamp modifiedAt) {
		this.modifiedAt= modifiedAt;
	}

	public List<Contact> getTheContacts() {
		return theContacts;
	}

	public void setTheContacts(List<Contact> theContacts) {
		this.theContacts = theContacts;
	}
	
}
