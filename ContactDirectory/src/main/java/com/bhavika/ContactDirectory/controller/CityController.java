package com.bhavika.ContactDirectory.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bhavika.ContactDirectory.dao.impl.CityDAOImpl;
import com.bhavika.ContactDirectory.entity.City;
import com.bhavika.ContactDirectory.responses.Response;
import com.bhavika.ContactDirectory.responses.ResponseException;

@RestController
@RequestMapping("/cd")
public class CityController {
	
	@Autowired
	CityDAOImpl cityDAOImpl;
	
	@GetMapping("/cities")
	private List<City> getAllCities()  throws ResponseException{
		List<City> theCities = null;
		theCities = cityDAOImpl.getCities();
		if (theCities == null) {
			throw new ResponseException("Not found!");
		}
		return theCities;
	}

	@GetMapping("/cities/{inState}")
	private List<City> getCities(@PathVariable int inState) throws ResponseException{
		List<City> theCities = null;
		theCities = cityDAOImpl.getCities(inState);
		if (theCities == null) {
			throw new ResponseException("Not found!");
		}
		return theCities;
	}
	
	@GetMapping("/cities/city/{cityId}")
	private City getCity(@PathVariable int cityId)  throws ResponseException{
		City city = null;
		city = cityDAOImpl.getCityById(cityId);
		return city;
	}
	
	@PostMapping("/cities")
	private Response getCity(@RequestBody City city)  throws ResponseException{
		int res = cityDAOImpl.addCity(city);
		if (res <= 0) {
			throw new ResponseException("Bad Request!");
		} else {
			return new Response(HttpStatus.OK.value(), "Operation Done", System.currentTimeMillis());
		}
	}

}
