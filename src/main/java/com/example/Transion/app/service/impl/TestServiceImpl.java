package com.example.Transion.app.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Transion.app.model.Test;
import com.example.Transion.app.repository.TestRepository;
import com.example.Transion.app.service.TestService;

@Service
public class TestServiceImpl implements TestService{

	@Autowired
	TestRepository testRepo;
	
	@Override
	public Test save(Test item) {
		// TODO Auto-generated method stub
		return testRepo.save(item);
	}

	@Override
	public List<Test> save(List<Test> items) {
		// TODO Auto-generated method stub
		return testRepo.saveAll(items);
	}

	@Override
	public Test findOne(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Test> findAll() {
		// TODO Auto-generated method stub
		return testRepo.findAll();
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Test item) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAll(List<Test> items) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String findByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

}
