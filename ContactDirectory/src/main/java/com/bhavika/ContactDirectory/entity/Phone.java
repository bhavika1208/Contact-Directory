package com.bhavika.ContactDirectory.entity;

import java.sql.Timestamp;

public class Phone {
	
	private int phoneId;
	private int contactId;
	private String phoneType;
	private String phoneNo;
	private boolean isActive;	
	private Timestamp createdAt;
	private Timestamp modifiedAt;
	
	public Phone() {
	}

	public Phone(int phoneId, int contactId, String phoneType, String phoneNo, boolean isActive, Timestamp createdAt,
			Timestamp modifiedAt) {
		super();
		this.phoneId = phoneId;
		this.contactId = contactId;
		this.phoneType = phoneType;
		this.phoneNo = phoneNo;
		this.isActive = isActive;
		this.createdAt = createdAt;
		this.modifiedAt = modifiedAt;
	}

	public int getPhoneId() {
		return phoneId;
	}

	public void setPhoneId(int phoneId) {
		this.phoneId = phoneId;
	}

	public int getContactId() {
		return contactId;
	}

	public void setContactId(int contactId) {
		this.contactId = contactId;
	}

	public String getPhoneType() {
		return phoneType;
	}

	public void setPhoneType(String phoneType) {
		this.phoneType = phoneType;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
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
		this.modifiedAt = modifiedAt;
	}
	
	
}
