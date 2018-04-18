package com.example.Transion.app.service.impl;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Transion.app.model.Agency;
import com.example.Transion.app.repository.AgencyRepository;
import com.example.Transion.app.service.AgencyService;

@Service
public class AgencyServiceImpl implements AgencyService{

	@Autowired
	AgencyRepository aRepository;
	
	@Override
	public Boolean exists(ObjectId id) {
		Optional<Agency> agencyCheck = aRepository.findById(id);
		return agencyCheck.isPresent();
	}

	@Override
	public Agency save(Agency item) {
		return aRepository.save(item);
	}

	@Override
	public List<Agency> save(List<Agency> items) {
		return aRepository.saveAll(items);
	}

	@Override
	public Optional<Agency> findOne(ObjectId id) {
		return aRepository.findById(id);
	}

	@Override
	public List<Agency> findAll() {
		return aRepository.findAll();
	}

	@Override
	public void delete(ObjectId id) {
		aRepository.deleteById(id);
	}

	@Override
	public void delete(Agency item) {
		aRepository.delete(item);
	}

	@Override
	public void deleteAll() {
		aRepository.deleteAll();
	}

	@Override
	public void deleteAll(List<Agency> items) {
		aRepository.deleteAll(items);
	}

}
