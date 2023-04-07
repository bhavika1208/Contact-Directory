package com.bhavika.ContactDirectory.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.catalina.connector.Response;
import org.springframework.stereotype.Component;

import com.bhavika.ContactDirectory.DbConnection;
import com.bhavika.ContactDirectory.dao.CityDAO;
import com.bhavika.ContactDirectory.entity.City;
import com.bhavika.ContactDirectory.entity.State;

@Component
public class CityDAOImpl implements CityDAO {

	DbConnection DbCon = new DbConnection();
	Connection conn = DbCon.getConn();	
	
	public CityDAOImpl(){
		
	}
	
	@Override
	public List<City> getCities() {
		List<City> theCities = new ArrayList<>();
		try {
			String query = "SELECT * FROM city";
			PreparedStatement stmt = conn.prepareStatement(query);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				City city = new City();
				city.setCityId(rs.getInt("cityId"));
				city.setCityName(rs.getString("cityName"));
				city.setInState(rs.getInt("cityStateId"));
				theCities.add(city);
			}
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return theCities;
	}

	@Override
	public List<City> getCities(int stateId) {
		List<City> theCities = new ArrayList<>();
		try {
			String query = "SELECT * FROM city where cityStateId = ?";
			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.setInt(1, stateId);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				City city = new City();
				city.setCityId(rs.getInt("cityId"));
				city.setCityName(rs.getString("cityName"));
				city.setInState(rs.getInt("cityStateId"));
				theCities.add(city);
			}
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return theCities;		
	}

	@Override
	public int addCity(City city) {
		try {
			String query = "INSERT INTO city (cityName, cityStateId) VALUES (?, ?)";
			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.setString(1, city.getCityName());
			stmt.setInt(2, city.getInState());
			int a = stmt.executeUpdate();
			return a;
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return 0;
	}


	@Override
	public City getCityById(int cityId) {

		City city = null;
		try {
			String query = "SELECT * FROM city where cityId = ?";
			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.setInt(1, cityId);
			ResultSet rs = stmt.executeQuery();
			if(rs.next()) {
				city = new City();
				city.setCityName(rs.getString("cityName"));
				city.setCityId(rs.getInt("cityId"));
				city.setInState(rs.getInt("cityStateId"));
				return city;
			}
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return city;
	}

}
