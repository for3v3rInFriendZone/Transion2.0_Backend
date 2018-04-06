package com.example.Transion.app.controller.user;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.Transion.app.model.user.TransionUser;
import com.example.Transion.app.service.user.TransionUserService;

@RestController
@RequestMapping(value = "api/transionUser")
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
	public ResponseEntity<TransionUser> getTransionUserById(@PathVariable("id") String id) {
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

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<TransionUser> saveTransionUser(@RequestBody TransionUser user) {
		if (user == null) {
			return new ResponseEntity<TransionUser>(HttpStatus.PRECONDITION_FAILED);
		}
		if (transionUserService.exists(user.getId())) {
			return new ResponseEntity<TransionUser>(HttpStatus.NOT_FOUND);
		}

		TransionUser savedUser = transionUserService.save(user);
		return new ResponseEntity<TransionUser>(savedUser, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<TransionUser> updateTransionUser(@RequestBody TransionUser user) {
		if (user == null) {
			return new ResponseEntity<TransionUser>(HttpStatus.PRECONDITION_FAILED);
		}
		if (transionUserService.exists(user.getId())) {
			return new ResponseEntity<TransionUser>(HttpStatus.NOT_FOUND);
		}

		TransionUser savedUser = transionUserService.save(user);
		return new ResponseEntity<TransionUser>(savedUser, HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<TransionUser> deleteTransionUser(@PathVariable("id") String id) {
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

}
