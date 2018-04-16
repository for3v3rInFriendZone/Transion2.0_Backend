package com.example.Transion.app.repository;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.Transion.app.model.Address;
import com.example.Transion.app.model.TransionUser;

@Repository
public interface AddressRepository extends MongoRepository<Address, ObjectId>{

	@Query("{'address.country': ?0}")
	public List<TransionUser> findByCountry(String country);
}
