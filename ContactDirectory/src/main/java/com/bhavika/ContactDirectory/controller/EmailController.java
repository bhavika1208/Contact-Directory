package com.bhavika.ContactDirectory.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bhavika.ContactDirectory.dao.impl.EmailDAOImpl;
import com.bhavika.ContactDirectory.dao.impl.UserDAOImpl;
import com.bhavika.ContactDirectory.entity.Email;
import com.bhavika.ContactDirectory.entity.User;
import com.bhavika.ContactDirectory.responses.Response;
import com.bhavika.ContactDirectory.responses.ResponseException;

@RestController
@RequestMapping("/cd")
public class EmailController {
	
	@Autowired
	EmailDAOImpl emailDAOImpl;
	
	@GetMapping("/email/{emailId}")
	private Email getEmails(@PathVariable int emailId) throws ResponseException {
		Email email = null;
		email = emailDAOImpl.getEmail(emailId);
		if(email == null) {
			throw new ResponseException("No value Found");
		}
		return email;
	}

	@GetMapping("/emails/{contactId}")
	private List<Email> getEmailsByContact(@PathVariable int contactId) throws ResponseException{
		List<Email> theEmails = null;
		theEmails = emailDAOImpl.getEmails(contactId);
		if(theEmails == null) {
			throw new ResponseException("No value Found");
		}
		return theEmails;
	}
	
	@PostMapping("/emails")
	private Response addEmail(@RequestBody Email theEmail) throws ResponseException{
		int res = emailDAOImpl.addEmail(theEmail);
		if (res <= 0) {
			throw new ResponseException("Bad Request!");
		} else {
			return new Response(HttpStatus.OK.value(), "Operation Done", System.currentTimeMillis());
		}
	}
	
	@PutMapping("/emails")
	private Response updateEmail(@RequestBody Email theEmail) throws ResponseException{
		int res = emailDAOImpl.updateEmail(theEmail);
		if (res <= 0) {
			throw new ResponseException("Bad Request!");
		} else {
			return new Response(HttpStatus.OK.value(), "Operation Done", System.currentTimeMillis());
		}
	}
	
	@DeleteMapping("/emails/{emailId}")
	private Response deleteEmail(@PathVariable int emailId) throws ResponseException{
		int res = emailDAOImpl.deleteEmail(emailId);
		if (res <= 0) {
			throw new ResponseException("Bad Request!");
		} else {
			return new Response(HttpStatus.OK.value(), "Operation Done", System.currentTimeMillis());
		}
	}

}