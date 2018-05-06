package com.example.Transion.app.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.mail.MessagingException;

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
import com.example.Transion.app.model.TransionUser;
import com.example.Transion.app.repository.AddressRepository;
import com.example.Transion.app.service.AgencyService;
import com.example.Transion.app.service.TransionUserService;

@RestController
@RequestMapping(value = "/api/transionUser")
public class TransionUserController {

	@Autowired
	TransionUserService transionUserService;
	
	@Autowired
	AgencyService aService;
	
	@Autowired
	AddressRepository addressRepository;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<TransionUser>> getTransionUsers() {
		return new ResponseEntity<List<TransionUser>>(transionUserService.findAll(), HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<TransionUser> getTransionUserById(@PathVariable("id") ObjectId id) {
		if (StringUtils.isEmpty(id)) {
			return new ResponseEntity<TransionUser>(HttpStatus.BAD_REQUEST);
		}

		Optional<TransionUser> transionUser = transionUserService.findOne(id);
		if (transionUser.isPresent()) {
			return new ResponseEntity<TransionUser>(transionUser.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<TransionUser>(HttpStatus.NOT_FOUND);
		}
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<TransionUser> saveTransionUser(@RequestBody TransionUser user) throws MessagingException {
		if (user == null) {
			return new ResponseEntity<TransionUser>(HttpStatus.BAD_REQUEST);
		}

		user.setPassword(transionUserService.passwordEncrypt(user.getPassword()));
		user.setAddress(addressRepository.save(user.getAddress()));
		user.setIsActivated(false);
		
		TransionUser savedUser = transionUserService.save(user);
		transionUserService.sendConfirmationEmail(savedUser);
		
		return new ResponseEntity<TransionUser>(savedUser, HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/save", method = RequestMethod.GET)
	public ResponseEntity<TransionUser> save() {
		List<String> li = new ArrayList<>();
		li.add("admin");
		Address address = new Address("Srbija", "Novi Sad", "Bulevar", 1, "24000");
		address = transionUserService.saveAddress(address);
		Agency agency = new Agency("Hello", "Hello World!!", "123", "1234", "123-12323-123", "asdas", new Date(), address, "123123", "example@example.com");
		agency = aService.save(agency);
	//	TransionUser user1 = new TransionUser("Petar", "Petrovic", "pera@gmail.com", transionUserService.passwordEncrypt("admin"), "0802993880018", "1122233", address, li);
		TransionUser user = new TransionUser("Petar", "Petrovic", "pera@gmail.com", "Djoka",
				"Srpsko", "M", "VII", transionUserService.passwordEncrypt("admin"),
				"12314", "asd", li, address, agency, true);
		return new ResponseEntity<TransionUser>(transionUserService.save(user), HttpStatus.CREATED);
	}

	@RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<TransionUser> updateTransionUser(@RequestBody TransionUser user) {
		if (user == null) {
			return new ResponseEntity<TransionUser>(HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<TransionUser>(transionUserService.save(user), HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<TransionUser> deleteTransionUser(@PathVariable("id") ObjectId id) {
		if (StringUtils.isEmpty(id)) {
			return new ResponseEntity<TransionUser>(HttpStatus.BAD_REQUEST);
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
	public ResponseEntity<TransionUser> findByUsername(@RequestParam(value = "username") String username){
		return new ResponseEntity<TransionUser>(transionUserService.findByUsername(username), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/activateAccount/{userId}", method = RequestMethod.GET)
	public ResponseEntity<TransionUser> sendConfirmationMailForSignUp(@PathVariable ObjectId userId) {

		if (StringUtils.isEmpty(userId)) {
			return new ResponseEntity<TransionUser>(HttpStatus.BAD_REQUEST);
		}

		Optional<TransionUser> transionUser = transionUserService.findOne(userId);
		if (!transionUser.isPresent()) {
			return new ResponseEntity<TransionUser>(HttpStatus.NOT_FOUND);
		}
		TransionUser activatedUser = transionUser.get();
		
		activatedUser.setIsActivated(true);
		
		return new ResponseEntity<TransionUser>(transionUserService.save(activatedUser), HttpStatus.OK);
	}
	
}