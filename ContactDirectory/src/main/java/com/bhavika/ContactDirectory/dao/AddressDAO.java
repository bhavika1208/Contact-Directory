package com.bhavika.ContactDirectory.dao;

import java.util.List;

import com.bhavika.ContactDirectory.entity.Address;

public interface AddressDAO {

	public Address getAddress(int addressId);

	public List<Address> getAddresses(int contactId);

	public int addAddress(Address address);

	public int updateAddress(Address address);

	public int deleteAddress(int addressId);
	
}
