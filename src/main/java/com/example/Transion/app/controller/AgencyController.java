package com.example.Transion.app.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.Transion.app.model.Address;
import com.example.Transion.app.model.Agency;
import com.example.Transion.app.repository.AddressRepository;
import com.example.Transion.app.service.impl.AgencyServiceImpl;

@RestController
@RequestMapping(value = "/api/agency")
public class AgencyController {

	@Autowired
	AgencyServiceImpl agencyService;
	 
	@Autowired
	AddressRepository aRepository;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Agency>> getAgencies() {
		List<Agency> agencies = agencyService.findAll();
		if (agencies.isEmpty()) {
			return new ResponseEntity<List<Agency>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Agency>>(agencyService.findAll(), HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Agency> getAgencyById(@PathVariable("id") ObjectId id) {
		if (StringUtils.isEmpty(id)) {
			return new ResponseEntity<Agency>(HttpStatus.PRECONDITION_FAILED);
		}

		Optional<Agency> agency = agencyService.findOne(id);
		if (agency.isPresent()) {
			return new ResponseEntity<Agency>(agency.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<Agency>(HttpStatus.NOT_FOUND);
		}
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Agency> saveAgency(@RequestBody Agency agency) {
		if (agency == null) {
			return new ResponseEntity<Agency>(HttpStatus.PRECONDITION_FAILED);
		}

		return new ResponseEntity<Agency>(agencyService.save(agency), HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/save", method = RequestMethod.GET)
	public ResponseEntity<Agency> save() {
		Address address = new Address("Srbija", "Novi Sad", "Bulevar", 1, "24000");
		address = aRepository.save(address);
		Agency agency = new Agency("Hello", "Hello World!!", "123", "1234", "123-12323-123", "asdas", new Date(), address, "123123", "example@example.com");
		return new ResponseEntity<Agency>(agencyService.save(agency), HttpStatus.CREATED);
	}
	

	@RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Agency> updateAgency(@RequestBody Agency agency) {
		if (agency == null) {
			return new ResponseEntity<Agency>(HttpStatus.PRECONDITION_FAILED);
		}

		return new ResponseEntity<Agency>(agencyService.save(agency), HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Agency> deleteAgency(@PathVariable("id") ObjectId id) {
		if (StringUtils.isEmpty(id)) {
			return new ResponseEntity<Agency>(HttpStatus.PRECONDITION_FAILED);
		}

		agencyService.delete(id);
		return new ResponseEntity<Agency>(HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.DELETE)
	public ResponseEntity<Agency> deleteAllAgencies() {
		agencyService.deleteAll();
		return new ResponseEntity<Agency>(HttpStatus.OK);
	}
}
