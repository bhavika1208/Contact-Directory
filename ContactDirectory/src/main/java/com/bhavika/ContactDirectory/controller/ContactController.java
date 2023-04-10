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

import com.bhavika.ContactDirectory.dao.impl.ContactDAOImpl;
import com.bhavika.ContactDirectory.entity.Contact;
import com.bhavika.ContactDirectory.responses.Response;
import com.bhavika.ContactDirectory.responses.ResponseException;

@CrossOrigin
@RestController
@RequestMapping("/cd")
public class ContactController {

	@Autowired
	ContactDAOImpl contactDAOImpl;
	
	@GetMapping("/contacts")
	private List<Contact> getContacts()  throws ResponseException {
		List<Contact> theContacts = null;
		theContacts = contactDAOImpl.getContacts();
		if(theContacts == null) {
			throw new ResponseException("Not found!");
		}
		return theContacts;
	}
	
	@GetMapping("/contacts/{contactId}")
	private Contact getContactById(@PathVariable int contactId)  throws ResponseException {
		Contact contact = null;
		contact = contactDAOImpl.getContactById(contactId);
		if(contact == null) {
			throw new ResponseException("Not found!");
		}
		return contact;	
	}
	
	@GetMapping("/contacts/user/{userId}")
	private List<Contact> getContactByUser(@PathVariable int userId)  throws ResponseException {
		List<Contact> theContacts = null;
		theContacts = contactDAOImpl.getContactsByUser(userId);
		if(theContacts == null) {
			throw new ResponseException("Not found!");
		}
		return theContacts;
	}
	
	@PostMapping("/contacts")
	private Response addContact(@RequestBody Contact contact)  throws ResponseException {
		int res = contactDAOImpl.addContact(contact);
		if (res <= 0) {
			throw new ResponseException("Bad Request!");
		} else {
			return new Response(HttpStatus.OK.value(), "Operation Done", System.currentTimeMillis());
		}
	}

	@PutMapping("/contacts")
	private Response updateContact(@RequestBody Contact merchant)  throws ResponseException {
		int res = contactDAOImpl.updateContact(merchant);
		if (res <= 0) {
			throw new ResponseException("Bad Request!");
		} else {
			return new Response(HttpStatus.OK.value(), "Operation Done", System.currentTimeMillis());
		}
	}

	@DeleteMapping("/contacts/{contactId}")
	private Response deleteContact(@PathVariable int contactId)  throws ResponseException {
		int res = contactDAOImpl.deleteContact(contactId);
		if (res <= 0) {
			throw new ResponseException("Bad Request!");
		} else {
			return new Response(HttpStatus.OK.value(), "Operation Done", System.currentTimeMillis());
		}
	}
	
	@PutMapping("/contacts/favourite/{contactId}")
	private Response toggleFavourite(@PathVariable int contactId) throws ResponseException{
		int res = contactDAOImpl.toggleFav(contactId);
		if (res <= 0) {
			throw new ResponseException("Bad Request!");
		} else {
			return new Response(HttpStatus.OK.value(), "Operation Done", System.currentTimeMillis());
		}
	}
}
