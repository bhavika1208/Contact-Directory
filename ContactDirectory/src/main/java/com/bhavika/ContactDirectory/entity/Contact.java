package com.bhavika.ContactDirectory.entity;

import java.sql.Timestamp;
import java.util.List;

public class Contact {

	private int contactId;
	private String contactName;
	private boolean isFavourite;
	private boolean isActive;
	private Timestamp createdAt;
	private Timestamp modifiedAt;
	private String profilePicture;
	private List<Email> emails;
	private List<Phone> phones;
	private List<Address> addresses;
	private int userId;

	public Contact() {
	}

	public Contact(int contactId, String contactName, boolean isFavourite, boolean isActive, Timestamp createdAt,
			Timestamp modifiedAt, String profilePicture, List<Email> emails, List<Phone> phones,
			List<Address> addresses, int userId) {
		super();
		this.contactId = contactId;
		this.contactName = contactName;
		this.isFavourite = isFavourite;
		this.isActive = isActive;
		this.createdAt = createdAt;
		this.modifiedAt = modifiedAt;
		this.profilePicture = profilePicture;
		this.emails = emails;
		this.phones = phones;
		this.addresses = addresses;
		this.userId = userId;
	}

	public int getContactId() {
		return contactId;
	}

	public void setContactId(int contactId) {
		this.contactId = contactId;
	}

	public String getContactName() {
		return contactName;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	public boolean getFavourite() {
		return isFavourite;
	}

	public void setFavourite(boolean isFavourite) {
		this.isFavourite = isFavourite;
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

	public String getProfilePicture() {
		return profilePicture;
	}

	public void setProfilePicture(String profilePicture) {
		this.profilePicture = profilePicture;
	}

	public List<Email> getEmails() {
		return emails;
	}

	public void setEmails(List<Email> emails) {
		this.emails = emails;
	}

	public List<Phone> getPhones() {
		return phones;
	}

	public void setPhones(List<Phone> phones) {
		this.phones = phones;
	}

	public List<Address> getAddresses() {
		return addresses;
	}

	public void setAddresses(List<Address> addresses) {
		this.addresses = addresses;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}	
}
