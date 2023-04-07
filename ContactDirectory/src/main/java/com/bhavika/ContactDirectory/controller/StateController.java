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

import com.bhavika.ContactDirectory.dao.impl.StateDAOImpl;
import com.bhavika.ContactDirectory.entity.State;
import com.bhavika.ContactDirectory.responses.Response;
import com.bhavika.ContactDirectory.responses.ResponseException;

@RestController
@RequestMapping("/cd")
public class StateController {
	
	@Autowired
	StateDAOImpl stateDAOImpl;
	
	@GetMapping("/states")
	private List<State> getStates() throws ResponseException {
		List<State> theStates = null;
		theStates = stateDAOImpl.getStates();
		if(theStates==null) {
			throw new ResponseException("No state Foiund");
		}
		return theStates;
	}
	
	@GetMapping("/states/{stateId}")
	private State getState(@PathVariable int stateId) throws ResponseException {
		State state = null;
		state = stateDAOImpl.getState(stateId);
		if(state==null) {
			throw new ResponseException("No state Foiund");
		}
		return state;
	}
	
	@PostMapping("/states")
	private Response addState(@RequestBody State state) throws ResponseException {
		int res = stateDAOImpl.addState(state);
		if (res <= 0) {
			throw new ResponseException("Bad Request!");
		} else {
			return new Response(HttpStatus.OK.value(), "Operation Done", System.currentTimeMillis());
		}
	}
	
	@PutMapping("/states")
	private Response updateState(@RequestBody State state) throws ResponseException {
		int res = stateDAOImpl.updateState(state);
		if (res <= 0) {
			throw new ResponseException("Bad Request!");
		} else {
			return new Response(HttpStatus.OK.value(), "Operation Done", System.currentTimeMillis());
		}
	}

	@DeleteMapping("/states/{stateId}")
	private Response deleteState(@PathVariable int stateId) throws ResponseException {
		int res = stateDAOImpl.deleteState(stateId);
		if (res <= 0) {
			throw new ResponseException("Bad Request!");
		} else {
			return new Response(HttpStatus.OK.value(), "Operation Done", System.currentTimeMillis());
		}
	}
}