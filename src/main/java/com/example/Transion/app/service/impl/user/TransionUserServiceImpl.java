package com.example.Transion.app.service.impl.user;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Transion.app.model.user.TransionUser;
import com.example.Transion.app.repository.user.TransionUserRepository;
import com.example.Transion.app.service.user.TransionUserService;

@Service
public class TransionUserServiceImpl implements TransionUserService {

	@Autowired
	TransionUserRepository transionUserRepo;

	@Override
	public Boolean exists(String id) {
		Optional<TransionUser> userCheck = transionUserRepo.findById(id);
		return userCheck.isPresent();
	}

	@Override
	public TransionUser save(TransionUser item) {
		return transionUserRepo.insert(item);
	}

	@Override
	public List<TransionUser> save(List<TransionUser> items) {
		return transionUserRepo.insert(items);
	}

	@Override
	public Optional<TransionUser> findOne(String id) {
		return transionUserRepo.findById(id);
	}

	@Override
	public List<TransionUser> findAll() {
		return transionUserRepo.findAll();
	}

	@Override
	public void delete(String id) {
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

}
