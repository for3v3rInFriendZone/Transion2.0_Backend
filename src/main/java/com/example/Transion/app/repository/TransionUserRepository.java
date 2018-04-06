package com.example.Transion.app.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.Transion.app.model.TransionUser;

@Repository
public interface TransionUserRepository extends MongoRepository<TransionUser, String>{

}
