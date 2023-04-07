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

import com.bhavika.ContactDirectory.dao.impl.AddressDAOImpl;
import com.bhavika.ContactDirectory.entity.Address;
import com.bhavika.ContactDirectory.responses.Response;
import com.bhavika.ContactDirectory.responses.ResponseException;

@RestController
@RequestMapping("/cd")
public class AddressController {

    @Autowired
    AddressDAOImpl addressDAOImpl;

    @GetMapping("/address/{addressId}")
    private Address getAddress(@PathVariable int addressId) throws ResponseException{
        Address address = null;
        address = addressDAOImpl.getAddress(addressId);
        if (address == null) {
            throw new ResponseException("Not found!");
        }
        return address;

    }
    
    @GetMapping("/address/contact/{contactId}")
    private List<Address> getAddresses(@PathVariable int contactId) throws ResponseException{
        List<Address> theAddresses = null;
        theAddresses = addressDAOImpl.getAddresses(contactId);
        return theAddresses;

    }

    @PostMapping("/address")
    private Response addContact(@RequestBody Address address){
        int res = addressDAOImpl.addAddress(address);
        if (res <= 0) {
            throw new ResponseException("Bad Request!");
        } else {
            return new Response(HttpStatus.OK.value(), "Operation Done", System.currentTimeMillis(), true);
        }
    }

    @PutMapping("/address")
    private Response updateAddress(@RequestBody Address address) throws ResponseException{
        int res = addressDAOImpl.updateAddress(address);
        if (res <= 0) {
            throw new ResponseException("Bad Request!");
        } else {
            return new Response(HttpStatus.OK.value(), "Operation Done", System.currentTimeMillis());
        }
    }

    @DeleteMapping("/address/{addressId}")
    private Response deleteAddress(@PathVariable int addressId) throws ResponseException{
        int res = addressDAOImpl.deleteAddress(addressId);
        if (res <= 0) {
            throw new ResponseException("Bad Request!");
        } else {
            return new Response(HttpStatus.OK.value(), "Operation Done", System.currentTimeMillis());
        }
    }
    
}
