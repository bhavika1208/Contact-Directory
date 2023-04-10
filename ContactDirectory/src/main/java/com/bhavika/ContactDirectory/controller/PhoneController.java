package com.bhavika.ContactDirectory.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bhavika.ContactDirectory.dao.impl.PhoneDAOImpl;
import com.bhavika.ContactDirectory.entity.Phone;
import com.bhavika.ContactDirectory.responses.Response;
import com.bhavika.ContactDirectory.responses.ResponseException;


@CrossOrigin
@RestController
@RequestMapping("/cd")
public class PhoneController {
	
	@Autowired
	PhoneDAOImpl phoneDAOImpl;
	
	@GetMapping("/phone/{phoneId}")
	private Phone getPhones(@PathVariable int phoneId) throws ResponseException {
		Phone phone = null;
		phone = phoneDAOImpl.getPhone(phoneId);
		if(phone == null) {
			throw new ResponseException("No value Found");
		}
		return phone;
	}

	@GetMapping("/phones/{contactId}")
	private List<Phone> getPhonesByContact(@PathVariable int contactId) throws ResponseException {
		List<Phone> thePhones = null;
		thePhones = phoneDAOImpl.getPhones(contactId);
		if(thePhones == null) {
			throw new ResponseException("No value Found");
		}
		return thePhones;
	}
	
	@PostMapping("/phones")
	private Response addphone(@RequestBody Phone theEmail) throws ResponseException {
		int res = phoneDAOImpl.addPhone(theEmail);
		if (res <= 0) {
			throw new ResponseException("Bad Request!");
		} else {
			return new Response(HttpStatus.OK.value(), "Operation Done", System.currentTimeMillis());
		}
	}
	
	@PutMapping("/phones")
	private Response updatePhone(@RequestBody Phone thePhone) throws ResponseException {
		int res = phoneDAOImpl.updatePhone(thePhone);
		if (res <= 0) {
			throw new ResponseException("Bad Request!");
		} else {
			return new Response(HttpStatus.OK.value(), "Operation Done", System.currentTimeMillis());
		}
	}
	
	@DeleteMapping("/phones/{phoneId}")
	private Response deletePhone(@PathVariable int phoneId) throws ResponseException {
		int res = phoneDAOImpl.deletePhone(phoneId);
		if (res <= 0) {
			throw new ResponseException("Bad Request!");
		} else {
			return new Response(HttpStatus.OK.value(), "Operation Done", System.currentTimeMillis());
		}
	}

}
