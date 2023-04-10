package com.bhavika.ContactDirectory.dao;

import java.util.List;

import com.bhavika.ContactDirectory.entity.Contact;

public interface ContactDAO{

	public List<Contact> getContacts();
	
	public Contact getContactById(int contactId);
	
	public List<Contact> getContactsByUser(int userId);
	
	public int addContact(Contact theContact);
	
	public int updateContact(Contact theContact);
	
	public int deleteContact(int contactId);
	
	public int toggleFav(int contactId);
	
}