package com.example.Transion.app.service;

import java.util.List;
import java.util.Optional;

import javax.mail.MessagingException;

import org.bson.types.ObjectId;

import com.example.Transion.app.model.Address;
import com.example.Transion.app.model.TransionUser;

public interface TransionUserService {

	public Boolean exists(ObjectId id);
	
	public TransionUser save(TransionUser item);

	public List<TransionUser> save(List<TransionUser> items);

	public Optional<TransionUser> findOne(ObjectId id);

	public List<TransionUser> findAll();

	public void delete(ObjectId id);

	public void delete(TransionUser item);

	public void deleteAll();

	public void deleteAll(List<TransionUser> items);
	
	public TransionUser findByUsername(String username);
	
	public String passwordEncrypt(String password);
	
	public Address saveAddress(Address address);
	
	public void sendConfirmationEmail(TransionUser user) throws MessagingException;
	
}
