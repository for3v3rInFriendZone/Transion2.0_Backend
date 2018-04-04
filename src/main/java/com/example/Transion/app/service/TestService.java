package com.example.Transion.app.service;

import java.util.List;

import com.example.Transion.app.model.Test;

public interface TestService {

	public Test save(Test item);

	public List<Test> save(List<Test> items);

	public Test findOne(Long id);

	public List<Test> findAll();

	public void delete(Long id);

	public void delete(Test item);

	public void deleteAll();

	public void deleteAll(List<Test> items);

	public String findByName(String name);
}