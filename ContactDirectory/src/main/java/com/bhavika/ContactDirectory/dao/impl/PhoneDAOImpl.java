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
import com.bhavika.ContactDirectory.dao.PhoneDAO;
import com.bhavika.ContactDirectory.entity.Email;
import com.bhavika.ContactDirectory.entity.Phone;

@Component
public class PhoneDAOImpl implements PhoneDAO {

	public PhoneDAOImpl() {
	}
	
	DbConnection DbCon = new DbConnection();
	Connection conn = DbCon.getConn();

	
	@Override
	public Phone getPhone(int phoneId) {
		Phone phone = null;
		try {
			String query = "SELECT * FROM phone WHERE phoneId = ? AND isActive = 1";
			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.setInt(1, phoneId);
			ResultSet resultSet = stmt.executeQuery();
			if (resultSet.next()) {
				phone = new Phone();
				phone.setPhoneId(resultSet.getInt("phoneId"));
				phone.setContactId(resultSet.getInt("contactId"));
				phone.setPhoneType(resultSet.getString("phoneType"));
				phone.setPhoneNo(resultSet.getString("phoneNo"));
				phone.setCreatedAt(resultSet.getTimestamp("createdAt"));
				phone.setModifiedAt(resultSet.getTimestamp("modifiedAt"));
				return phone;
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return phone;
	}

	@Override
	public List<Phone> getPhones(int contactId) {
		List<Phone> thePhones = new ArrayList<>();
		try {
			String query = "SELECT * FROM phone WHERE contactId = ? AND isActive = 1";
			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.setInt(1, contactId);
			ResultSet resultSet = stmt.executeQuery();
			while (resultSet.next()) {
				Phone phone = new Phone();
				phone.setPhoneId(resultSet.getInt("phoneId"));
				phone.setContactId(resultSet.getInt("contactId"));
				phone.setPhoneType(resultSet.getString("phoneType"));
				phone.setPhoneNo(resultSet.getString("phoneNo"));
				phone.setCreatedAt(resultSet.getTimestamp("createdAt"));
				phone.setModifiedAt(resultSet.getTimestamp("modifiedAt"));
			
				thePhones.add(phone);
			}
			return thePhones;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return thePhones;
	}

	@Override
	public int addPhone(Phone phone) {
		try {
			String query = "INSERT INTO phone (phoneType, phoneNo, contactId, isActive, createdAt, modifiedAt) VALUES (?, ?, ?, ?, ?, ?)";
			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.setString(1, phone.getPhoneType());
			stmt.setString(2, phone.getPhoneNo());
			stmt.setInt(3, phone.getContactId());
			stmt.setBoolean(4, true);
			stmt.setTimestamp(5, new Timestamp(System.currentTimeMillis()));
			stmt.setTimestamp(6, new Timestamp(System.currentTimeMillis()));
			
			int a = stmt.executeUpdate();
			return a;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return 0;
	}

	@Override
	public int updatePhone(Phone phone) {
		try {
			String query = "UPDATE phone SET phoneType = ?, phoneNo = ?, modifiedAt = ? WHERE phoneId = ?";
			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.setString(1, phone.getPhoneType());
			stmt.setString(2, phone.getPhoneNo());
			stmt.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
			stmt.setInt(4, phone.getPhoneId());
			int a = stmt.executeUpdate();
			
			return a;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return 0;
	}

	@Override
	public int deletePhone(int phoneId) {
		try {
			String query = "UPDATE phone SET isActive = 0 WHERE phoneId = ?";
			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.setInt(1, phoneId);
			return stmt.executeUpdate();
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return 0;
	}

}
