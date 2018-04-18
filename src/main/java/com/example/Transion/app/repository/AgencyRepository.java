package com.example.Transion.app.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.Transion.app.model.Agency;

@Repository
public interface AgencyRepository extends MongoRepository<Agency, ObjectId>{

}
