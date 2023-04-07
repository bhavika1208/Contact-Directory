package com.bhavika.ContactDirectory.dao;

import java.util.List;

import org.apache.catalina.connector.Response;
import com.bhavika.ContactDirectory.entity.City;

public interface CityDAO {
	
	public List<City> getCities();

	public List<City> getCities(int stateId);
	
	public City getCityById(int cityId);

	public int addCity(City city);


}
