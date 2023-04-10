package com.bhavika.ContactDirectory.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.bhavika.ContactDirectory.DbConnection;
import com.bhavika.ContactDirectory.dao.ContactDAO;
import com.bhavika.ContactDirectory.entity.Address;
import com.bhavika.ContactDirectory.entity.Contact;
import com.bhavika.ContactDirectory.entity.Email;
import com.bhavika.ContactDirectory.entity.Phone;


@Component
public class ContactDAOImpl implements ContactDAO {

	public ContactDAOImpl() {
	}


	DbConnection temp = new DbConnection();
	Connection conn = temp.getConn();
	
	@Override
	public List<Contact> getContacts() {
		List<Contact> theContacts = new ArrayList<>();
		try {
			String query = "SELECT * FROM contact WHERE isActive = 1";
			PreparedStatement stmt = conn.prepareStatement(query);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				EmailDAOImpl emailDAOImpl = new EmailDAOImpl();
				PhoneDAOImpl phoneDAOImpl = new PhoneDAOImpl();
				AddressDAOImpl addressDAOImpl = new AddressDAOImpl();
				Contact contact = null;
				contact = new Contact();
				contact.setContactId(rs.getInt("contactId"));
				contact.setContactName(rs.getString("contactName"));
				contact.setFavourite(rs.getBoolean("isFavourite"));
				contact.setProfilePicture(rs.getString("profilePicture"));
				contact.setCreatedAt(rs.getTimestamp("createdAt"));
				contact.setModifiedAt(rs.getTimestamp("modifiedAt"));
				contact.setEmails(emailDAOImpl.getEmails(rs.getInt("contactId")));
				contact.setPhones(phoneDAOImpl.getPhones(rs.getInt("contactId")));
				contact.setAddresses(addressDAOImpl.getAddresses(rs.getInt("contactId")));

				theContacts.add(contact);
				System.out.println(contact);
			}
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return theContacts;
	}

	@Override
	public Contact getContactById(int contactId) {
		Contact contact = null;
		try {
			String query = "SELECT * FROM contact WHERE contactId = ? AND isActive = 1";
			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.setInt(1, contactId);
			ResultSet rs = stmt.executeQuery();
			if(rs.next()) {
				EmailDAOImpl emailDAOImpl = new EmailDAOImpl();
				PhoneDAOImpl phoneDAOImpl = new PhoneDAOImpl();
				AddressDAOImpl addressDAOImpl = new AddressDAOImpl();
				contact = new Contact();
				contact.setContactId(rs.getInt("contactId"));
				contact.setContactName(rs.getString("contactName"));
				contact.setFavourite(rs.getBoolean("isFavourite"));
				contact.setProfilePicture(rs.getString("profilePicture"));
				contact.setCreatedAt(rs.getTimestamp("createdAt"));
				contact.setModifiedAt(rs.getTimestamp("modifiedAt"));
				contact.setEmails(emailDAOImpl.getEmails(rs.getInt("contactId")));
				contact.setPhones(phoneDAOImpl.getPhones(rs.getInt("contactId")));
				contact.setAddresses(addressDAOImpl.getAddresses(rs.getInt("contactId")));
				return contact;
			}
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return null;
	}

	@Override
	public List<Contact> getContactsByUser(int userId) {
		List<Contact> theContacts = new ArrayList<>();
		try {
			String query = "SELECT * FROM contact WHERE userId = ? AND isActive = 1";
			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.setInt(1, userId);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				EmailDAOImpl emailDAOImpl = new EmailDAOImpl();
				PhoneDAOImpl phoneDAOImpl = new PhoneDAOImpl();
				AddressDAOImpl addressDAOImpl = new AddressDAOImpl();
				Contact contact = null;
				contact = new Contact();
				contact.setContactId(rs.getInt("contactId"));
				contact.setContactName(rs.getString("contactName"));
				contact.setFavourite(rs.getBoolean("isFavourite"));
				contact.setProfilePicture(rs.getString("profilePicture"));
				contact.setCreatedAt(rs.getTimestamp("createdAt"));
				contact.setModifiedAt(rs.getTimestamp("modifiedAt"));
				contact.setEmails(emailDAOImpl.getEmails(rs.getInt("contactId")));
				contact.setPhones(phoneDAOImpl.getPhones(rs.getInt("contactId")));
				contact.setAddresses(addressDAOImpl.getAddresses(rs.getInt("contactId")));
				
				theContacts.add(contact);
			}
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return theContacts;
	}

	@Override
	public int addContact(Contact theContact) {
		try {
			String query = "INSERT INTO contact (contactName, isFavourite, userId, isActive, profilePicture, createdAt, modifiedAt) VALUES (?, ?, ?, ?, ?, ? ,?)";
			PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, theContact.getContactName());
			stmt.setBoolean(2, theContact.getFavourite());
			stmt.setInt(3, theContact.getUserId());
			stmt.setBoolean(4, true);
			stmt.setString(5,  theContact.getProfilePicture());
			stmt.setTimestamp(6, new Timestamp(System.currentTimeMillis()));
			stmt.setTimestamp(7, new Timestamp(System.currentTimeMillis()));
		
			int a = stmt.executeUpdate();
			ResultSet rs = stmt.getGeneratedKeys();
			if (rs.next()) {
				int contactId = rs.getInt(1);
				EmailDAOImpl emailDAOImpl = new EmailDAOImpl();
				PhoneDAOImpl phoneDAOImpl = new PhoneDAOImpl();
				AddressDAOImpl addressDAOImpl = new AddressDAOImpl();
				List<Email> theEmails = theContact.getEmails();
				List<Phone> thePhones = theContact.getPhones();
				List<Address> theAddress = theContact.getAddresses();
				for (Email email : theEmails) {
					email.setContactId(contactId);
					emailDAOImpl.addEmail(email);
				}
				for (Phone phone : thePhones){
					phone.setContactId(contactId);
					phoneDAOImpl.addPhone(phone);
				}
				for (Address address : theAddress) {
					address.setContactId(contactId);
					addressDAOImpl.addAddress(address);
				}
			}
			System.out.println(a);
			return a;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return 0;
	}

	@Override
	public int updateContact(Contact theContact) {
		try {
			String query = "UPDATE contact SET contactName = ?, isFavourite = ?, profilePicture = ?, modifiedAt = ? WHERE contactId = ?";
			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.setString(1, theContact.getContactName());
			stmt.setBoolean(2, theContact.getFavourite());
			stmt.setString(3, theContact.getProfilePicture());
			stmt.setTimestamp(4, new Timestamp(System.currentTimeMillis()));
			stmt.setInt(5, theContact.getContactId());
			
			int a = stmt.executeUpdate();
			
			return a;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return 0;
	}

	@Override
	public int deleteContact(int contactId) {
		try {
			String query = "UPDATE contact SET isActive = ? WHERE contactId = ?";
			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.setBoolean(1, false);
			stmt.setInt(2, contactId);
			int a = stmt.executeUpdate();			
			
			EmailDAOImpl emailDAOImpl = new EmailDAOImpl();
			PhoneDAOImpl phoneDAOImpl = new PhoneDAOImpl();
			AddressDAOImpl addressDAOImpl = new AddressDAOImpl();
			List<Email> theEmails = emailDAOImpl.getEmails(contactId);
			List<Phone> thePhones = phoneDAOImpl.getPhones(contactId);
			List<Address> theAddress = addressDAOImpl.getAddresses(contactId);
			for (Email email : theEmails) {
				emailDAOImpl.deleteEmail(email.getEmailId());
			}
			for (Phone phone : thePhones){
				phoneDAOImpl.deletePhone(phone.getPhoneId());
			}
			for (Address address : theAddress) {
				addressDAOImpl.deleteAddress(address.getAddressId());
			}
			
			return a;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return 0;
	}
	
	@Override
	public int toggleFav(int contactId){
		
		try{
			String query = "UPDATE contact SET isFavourite = NOT isFavourite WHERE contactId = ?";
			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.setInt(1, contactId);
			return stmt.executeUpdate();
		}catch (Exception e) {
		}
		return 0;
	}

}
