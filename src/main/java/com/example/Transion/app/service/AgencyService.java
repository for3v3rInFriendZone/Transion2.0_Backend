package com.example.Transion.app.service;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;

import com.example.Transion.app.model.Address;
import com.example.Transion.app.model.Agency;

public interface AgencyService {

	public Boolean exists(ObjectId id);
	
	public Agency save(Agency item);

	public List<Agency> save(List<Agency> items);

	public Optional<Agency> findOne(ObjectId id);

	public List<Agency> findAll();

	public void delete(ObjectId id);

	public void delete(Agency item);

	public void deleteAll();

	public void deleteAll(List<Agency> items);
	
}
