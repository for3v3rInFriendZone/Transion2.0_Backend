package com.example.Transion.app.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.Transion.app.model.Test;

@Repository
public interface TestRepository extends MongoRepository<Test, String> {
	
	public String findByName(String name);
}
