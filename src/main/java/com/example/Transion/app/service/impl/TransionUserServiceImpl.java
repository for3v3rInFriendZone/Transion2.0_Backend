package com.example.Transion.app.service.impl;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.Transion.app.model.Address;
import com.example.Transion.app.model.TransionUser;
import com.example.Transion.app.repository.AddressRepository;
import com.example.Transion.app.repository.TransionUserRepository;
import com.example.Transion.app.service.TransionUserService;

@Service
public class TransionUserServiceImpl implements TransionUserService {

	@Autowired
	TransionUserRepository transionUserRepo;
	
	@Autowired
	AddressRepository addressRepository;

	@Override
	public Boolean exists(ObjectId id) {
		Optional<TransionUser> userCheck = transionUserRepo.findById(id);
		return userCheck.isPresent();
	}

	@Override
	public TransionUser save(TransionUser item) {
		item.setAddress(addressRepository.save(item.getAddress()));
		return transionUserRepo.save(item);
	}

	@Override
	public List<TransionUser> save(List<TransionUser> items) {
		return transionUserRepo.saveAll(items);
	}

	@Override
	public Optional<TransionUser> findOne(ObjectId id) {
		return transionUserRepo.findById(id);
	}

	@Override
	public List<TransionUser> findAll() {
		return transionUserRepo.findAll();
	}

	@Override
	public void delete(ObjectId id) {
		transionUserRepo.deleteById(id);
	}

	@Override
	public void delete(TransionUser item) {
		transionUserRepo.delete(item);
	}

	@Override
	public void deleteAll() {
		transionUserRepo.deleteAll();
	}

	@Override
	public void deleteAll(List<TransionUser> items) {
		transionUserRepo.deleteAll(items);
	}

	@Override
	public String passwordEncrypt(String password) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		return passwordEncoder.encode(password);
	}

	@Override
	public TransionUser findByEmail(String email) {
		return transionUserRepo.findByEmail(email);
	}

	@Override
	public List<TransionUser> findByCountry(String country) {
		// TODO Auto-generated method stub
		return transionUserRepo.findByCountry(country);
	}

	@Override
	public Address saveAddress(Address address) {
		// TODO Auto-generated method stub
		return addressRepository.save(address);
	}

}
