package com.example.Transion.app.repository;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.Transion.app.model.TransionUser;

@Repository
public interface TransionUserRepository extends MongoRepository<TransionUser, ObjectId> {

	public TransionUser findByUsername(String username);

	// ne radi trenutno
	@Query("{'address.country': ?0}")
	public List<TransionUser> findByCountry(String country);
}
