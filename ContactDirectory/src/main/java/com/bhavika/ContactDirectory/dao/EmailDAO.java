package com.bhavika.ContactDirectory.dao;

import java.util.List;

import com.bhavika.ContactDirectory.entity.Email;

public interface EmailDAO {
	
	public Email getEmail(int emailId);

	public List<Email> getEmails(int contactId);

	public int addEmail(Email email);

	public int updateEmail(Email email);

	public int deleteEmail(int emailId);

}
