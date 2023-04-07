package com.bhavika.ContactDirectory.dao;

import java.util.List;

import com.bhavika.ContactDirectory.entity.Phone;

public interface PhoneDAO {
	
	public Phone getPhone(int phoneId);

	public List<Phone> getPhones(int contactId);

	public int addPhone(Phone phone);

	public int updatePhone(Phone phone);

	public int deletePhone(int phoneId);

}
