package com.example.Transion.app.service;

import java.util.List;
import java.util.Optional;

import com.example.Transion.app.model.TransionUser;

public interface TransionUserService {

	public Boolean exists(String id);
	
	public TransionUser save(TransionUser item);

	public List<TransionUser> save(List<TransionUser> items);

	public Optional<TransionUser> findOne(String id);

	public List<TransionUser> findAll();

	public void delete(String id);

	public void delete(TransionUser item);

	public void deleteAll();

	public void deleteAll(List<TransionUser> items);
}
