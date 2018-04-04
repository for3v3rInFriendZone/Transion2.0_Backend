package com.example.Transion.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.Transion.app.model.Test;
import com.example.Transion.app.service.TestService;

@RestController
@RequestMapping(value = "/test")
public class TestController {

	@Autowired
	TestService testService;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Test>> getTests() {
		return new ResponseEntity<List<Test>>(testService.findAll(), HttpStatus.OK);
	}

	@RequestMapping(value="/add", method = RequestMethod.GET)
	public ResponseEntity<Test> setTest() {
		Test test = new Test("asd", "asdd");
		
		return new ResponseEntity<Test>(testService.save(test), HttpStatus.CREATED);
	}
}
