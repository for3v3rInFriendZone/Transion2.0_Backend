package com.example.Transion.app.service.impl;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Transion.app.model.Address;
import com.example.Transion.app.repository.AddressRepository;
import com.example.Transion.app.service.AddressService;

@Service
public class AddressServiceImpl implements AddressService {

	@Autowired
	AddressRepository addressRepo;
	
	@Override
	public Boolean exists(ObjectId id) {
		Optional<Address> addressCheck = addressRepo.findById(id);
		return addressCheck.isPresent();
	}

	@Override
	public Address save(Address item) {
		return addressRepo.save(item);
	}

	@Override
	public List<Address> save(List<Address> items) {
		return addressRepo.saveAll(items);
	}

	@Override
	public Optional<Address> findOne(ObjectId id) {
		return addressRepo.findById(id);
	}

	@Override
	public List<Address> findAll() {
		return addressRepo.findAll();
	}

	@Override
	public void delete(ObjectId id) {
		addressRepo.deleteById(id);
	}

	@Override
	public void delete(Address item) {
		addressRepo.delete(item);
	}

	@Override
	public void deleteAll() {
		addressRepo.deleteAll();
	}

	@Override
	public void deleteAll(List<Address> items) {
		addressRepo.deleteAll(items);
	}

}
