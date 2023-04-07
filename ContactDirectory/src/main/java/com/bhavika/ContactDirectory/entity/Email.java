package com.bhavika.ContactDirectory.entity;

import java.sql.Timestamp;

public class Email {

	private int emailId;
	private String emailType;
	private String email;
	private int contactId;
	private boolean isActive;
	private Timestamp createdAt;
	private Timestamp modifiedAt;

	public Email() {
	}

	public Email(int emailId, String emailType, String email, int contactId, boolean isActive, Timestamp createdAt,
			Timestamp modifiedAt) {
		super();
		this.emailId = emailId;
		this.emailType = emailType;
		this.email = email;
		this.contactId = contactId;
		this.isActive = isActive;
		this.createdAt = createdAt;
		this.modifiedAt = modifiedAt;
	}

	public int getContactId() {
		return contactId;
	}

	public void setContactId(int contactId) {
		this.contactId = contactId;
	}

	public int getEmailId() {
		return emailId;
	}

	public void setEmailId(int emailId) {
		this.emailId = emailId;
	}

	public String getEmailType() {
		return emailType;
	}

	public void setEmailType(String emailType) {
		this.emailType = emailType;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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
