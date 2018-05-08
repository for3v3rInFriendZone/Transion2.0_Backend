package com.example.Transion.app.service;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;

import com.example.Transion.app.model.Address;

public interface AddressService {

	public Boolean exists(ObjectId id);
	
	public Address save(Address item);

	public List<Address> save(List<Address> items);

	public Optional<Address> findOne(ObjectId id);

	public List<Address> findAll();

	public void delete(ObjectId id);

	public void delete(Address item);

	public void deleteAll();

	public void deleteAll(List<Address> items);
}
