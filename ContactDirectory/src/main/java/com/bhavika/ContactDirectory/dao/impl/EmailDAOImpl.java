package com.bhavika.ContactDirectory.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;

import com.bhavika.ContactDirectory.DbConnection;
import com.bhavika.ContactDirectory.dao.EmailDAO;
import com.bhavika.ContactDirectory.entity.Email;

@Controller
public class EmailDAOImpl implements EmailDAO {

	public EmailDAOImpl() {
	}
	
	DbConnection DbCon = new DbConnection();
	Connection conn = DbCon.getConn();

	@Override
	public Email getEmail(int emailId) {
		Email email = null;
		try {
			String query = "SELECT * FROM email WHERE emailId = ? AND isActive = 1";
			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.setInt(1, emailId);
			ResultSet resultSet = stmt.executeQuery();
			if (resultSet.next()) {
				email = new Email();
				email.setEmailId(resultSet.getInt("emailId"));
				email.setContactId(resultSet.getInt("contactId"));
				email.setEmailType(resultSet.getString("emailType"));
				email.setEmail(resultSet.getString("email"));
				email.setCreatedAt(resultSet.getTimestamp("createdAt"));
				email.setModifiedAt(resultSet.getTimestamp("modifiedAt"));
				email.setActive(resultSet.getBoolean("isActive"));
				return email;
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return email;
	}

	@Override
	public List<Email> getEmails(int contactId) {

		List<Email> theEmails = new ArrayList<>();
		try {
			String query = "SELECT * FROM email WHERE contactId = ? AND isActive = 1";
			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.setInt(1, contactId);
			ResultSet resultSet = stmt.executeQuery();
			while (resultSet.next()) {
				Email email = new Email();
				email.setEmailId(resultSet.getInt("emailId"));
				email.setContactId(resultSet.getInt("contactId"));
				email.setEmailType(resultSet.getString("emailType"));
				email.setEmail(resultSet.getString("email"));
				email.setCreatedAt(resultSet.getTimestamp("createdAt"));
				email.setModifiedAt(resultSet.getTimestamp("modifiedAt"));
				email.setActive(resultSet.getBoolean("isActive"));
			
				theEmails.add(email);
			}
			return theEmails;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return theEmails;
		
	}

	@Override
	public int addEmail(Email email) {
		try {
			String query = "INSERT INTO email (emailType, email, contactId, isActive, createdAt, modifiedAt) VALUES (?, ?, ?, ?, ?, ?)";
			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.setString(1, email.getEmailType());
			stmt.setString(2, email.getEmail());
			stmt.setInt(3, email.getContactId());
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
	public int updateEmail(Email email) {
		try {
			String query = "UPDATE email SET emailType = ?, email = ?, modifiedAt = ? WHERE emailId = ?";
			PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, email.getEmailType());
			stmt.setString(2, email.getEmail());
			stmt.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
			stmt.setInt(4, email.getEmailId());
			int a = stmt.executeUpdate();
			ResultSet rs = stmt.getGeneratedKeys();
			if (rs.next()) {
				System.out.println(rs.getInt(1));
			} else {
				System.out.println("this");
			}
			return a;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return 0;
	}

	@Override
	public int deleteEmail(int emailId) {
		try {
			String query = "UPDATE email SET isActive = 0 WHERE emailId = ?";
			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.setInt(1, emailId);
			return stmt.executeUpdate();
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return 0;
	}
}
