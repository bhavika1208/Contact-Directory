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
import com.bhavika.ContactDirectory.dao.AddressDAO;
import com.bhavika.ContactDirectory.entity.Address;

@Component
public class AddressDAOImpl implements AddressDAO {

	AddressDAOImpl addressDAOImpl;
	
	DbConnection temp = new DbConnection();
	Connection conn = temp.getConn();
	
	@Override
	public Address getAddress(int addressId) {
		Address address = null;
        try {
            String query = "SELECT * FROM address WHERE addressId = ? AND isActive = 1";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, addressId);
            ResultSet resultSet = stmt.executeQuery();
            if (resultSet.next()) {
                address = new Address();
                address.setAddressId(resultSet.getInt("addressId"));
                address.setContactId(resultSet.getInt("contactId"));
                address.setAddressType(resultSet.getString("addressType"));
                address.setAddress(resultSet.getString("address"));
                address.setAddressCity(resultSet.getInt("addressCity"));
                address.setAddressState(resultSet.getInt("addressState"));
                address.setAddressPincode(resultSet.getInt("addressPincode"));
                address.setActive(resultSet.getBoolean("isActive"));
                address.setCreatedAt(resultSet.getTimestamp("createdAt"));
                address.setModifiedAt(resultSet.getTimestamp("modifiedAt"));

                address.setAddressCity(addressId);
            }
            return address;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return address;
	}

	@Override
	public List<Address> getAddresses(int contactId) {
		  List<Address> theAddresses = new ArrayList<>();
	        try {
	            String query = "SELECT * FROM address WHERE contactId = ? AND isActive = 1";
	            PreparedStatement stmt = conn.prepareStatement(query);
	            stmt.setInt(1, contactId);
	            ResultSet resultSet = stmt.executeQuery();
	            while (resultSet.next()) {
	                Address address = new Address();
	                
	                address.setAddressId(resultSet.getInt("addressId"));
	                address.setContactId(resultSet.getInt("contactId"));
	                address.setAddressType(resultSet.getString("addressType"));
	                address.setAddress(resultSet.getString("address"));
	                address.setAddressCity(resultSet.getInt("addressCity"));
	                address.setAddressState(resultSet.getInt("addressState"));
	                address.setAddressPincode(resultSet.getInt("addressPincode"));
	                address.setActive(resultSet.getBoolean("isActive"));
	                address.setCreatedAt(resultSet.getTimestamp("createdAt"));
	                address.setModifiedAt(resultSet.getTimestamp("modifiedAt"));
	                
	                theAddresses.add(address);
	            }
	            return theAddresses;
	        } catch (Exception e) {
	            System.out.println(e.getMessage());
	        }
	        return theAddresses;
	}

	@Override
	public int addAddress(Address address) {
		   try {
	            String query = "INSERT INTO address (contactId, addressType, address, addressCity, addressState, addressPincode, isActive, createdAt, modifiedAt) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
	            PreparedStatement stmt = conn.prepareStatement(query);
	            stmt.setInt(1, address.getContactId());
	            stmt.setString(2, address.getAddressType());
	            stmt.setString(3, address.getAddress());
	            stmt.setInt(4, address.getAddressCity());
	            stmt.setInt(5, address.getAddressState());
	            stmt.setLong(6, address.getAddressPincode());
	            stmt.setBoolean(7, true);
	            stmt.setTimestamp(8, new Timestamp(System.currentTimeMillis()));
	            stmt.setTimestamp(9, new Timestamp(System.currentTimeMillis()));
	            
	            int a = stmt.executeUpdate();
	            return a;
	        } catch (Exception e) {
	            System.out.println(e.getMessage());
	        }
	        return 0;
	}

	@Override
	public int updateAddress(Address address) {
		try {
            String query = "UPDATE address SET addressType = ?, address = ?, addressCity = ?, addressState = ?, addressPincode= ?, modifiedAt = ? WHERE addressId = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, address.getAddressType());
            stmt.setString(2, address.getAddress());
            stmt.setInt(3, address.getAddressCity());
            stmt.setInt(4, address.getAddressState());
            stmt.setLong(5, address.getAddressPincode());
            stmt.setTimestamp(6, new Timestamp(System.currentTimeMillis()));
            stmt.setInt(7, address.getAddressId());
            int a = stmt.executeUpdate();
             return a;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return 0;
	}

	@Override
	public int deleteAddress(int addressId) {
		try {
            String query = "UPDATE address SET isActive = ? WHERE addressId = ?";
            PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            stmt.setBoolean(1, false);
            stmt.setInt(2, addressId);
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
}
