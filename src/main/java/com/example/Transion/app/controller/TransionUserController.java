package com.example.Transion.app.controller;

import java.util.ArrayList;
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
import com.example.Transion.app.model.TransionUser;
import com.example.Transion.app.repository.AddressRepository;
import com.example.Transion.app.service.TransionUserService;

@RestController
@RequestMapping(value = "/api/transionUser")
public class TransionUserController {

	@Autowired
	TransionUserService transionUserService;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<TransionUser>> getTransionUsers() {
		List<TransionUser> transionUsers = transionUserService.findAll();
		if (transionUsers.isEmpty()) {
			return new ResponseEntity<List<TransionUser>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<TransionUser>>(transionUserService.findAll(), HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<TransionUser> getTransionUserById(@PathVariable("id") ObjectId id) {
		if (StringUtils.isEmpty(id)) {
			return new ResponseEntity<TransionUser>(HttpStatus.PRECONDITION_FAILED);
		}

		Optional<TransionUser> transionUser = transionUserService.findOne(id);
		if (transionUser.isPresent()) {
			return new ResponseEntity<TransionUser>(transionUser.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<TransionUser>(HttpStatus.NOT_FOUND);
		}
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<TransionUser> saveTransionUser(@RequestBody TransionUser user) {
		if (user == null) {
			return new ResponseEntity<TransionUser>(HttpStatus.PRECONDITION_FAILED);
		}

		user.setPassword(transionUserService.passwordEncrypt(user.getPassword()));
		return new ResponseEntity<TransionUser>(transionUserService.save(user), HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/save", method = RequestMethod.GET)
	public ResponseEntity<TransionUser> save() {
		List<String> li = new ArrayList<>();
		li.add("admin");
		Address address = new Address("Srbija", "Novi Sad", "Bulevar", 1, "24000");
		address = transionUserService.saveAddress(address);
		TransionUser user = new TransionUser("Petar", "Petrovic", "petar.petrovic", transionUserService.passwordEncrypt("admin"), "0802993880018", 
				"pera@gmail.com", "1122233", address, li);

		return new ResponseEntity<TransionUser>(transionUserService.save(user), HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/country", method = RequestMethod.GET)
	public ResponseEntity<List<TransionUser>> findCountry() {

		return new ResponseEntity<List<TransionUser>>(transionUserService.findByCountry("Srbija"), HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<TransionUser> updateTransionUser(@RequestBody TransionUser user) {
		if (user == null) {
			return new ResponseEntity<TransionUser>(HttpStatus.PRECONDITION_FAILED);
		}

		return new ResponseEntity<TransionUser>(transionUserService.save(user), HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<TransionUser> deleteTransionUser(@PathVariable("id") ObjectId id) {
		if (StringUtils.isEmpty(id)) {
			return new ResponseEntity<TransionUser>(HttpStatus.PRECONDITION_FAILED);
		}

		transionUserService.delete(id);
		return new ResponseEntity<TransionUser>(HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.DELETE)
	public ResponseEntity<TransionUser> deleteAllTransionUsers() {
		transionUserService.deleteAll();
		return new ResponseEntity<TransionUser>(HttpStatus.OK);
	}

	@RequestMapping(value = "/findemail", method = RequestMethod.GET, params = { "username" })
	public ResponseEntity<TransionUser> findByEmail(@RequestParam(value = "username") String username){
		return new ResponseEntity<TransionUser>(transionUserService.findByUsername(username), HttpStatus.OK);
	}
}