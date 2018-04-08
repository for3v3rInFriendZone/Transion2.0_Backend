package com.example.Transion.app.model;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.example.Transion.app.util.CascadeSave;

@Document(collection = "transionUsers")
public class TransionUser {

	@Id
	private ObjectId id;

	private String firstname;

	private String lastname;

	private String username;

	private String password;

	private String jmbg;

	private String email;

	private String phoneNumber;
	
	private List<String> authorities;

	@DBRef
	@CascadeSave
	private Address address;

	public TransionUser() {
		super();
	}

	public TransionUser(String firstname, String lastname, String username, String password, String jmbg, String email,
			String phoneNumber, Address address, List<String> authorities) {
		super();
		this.firstname = firstname;
		this.lastname = lastname;
		this.username = username;
		this.password = password;
		this.jmbg = jmbg;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.address = address;
		this.authorities = authorities;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getJmbg() {
		return jmbg;
	}

	public void setJmbg(String jmbg) {
		this.jmbg = jmbg;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public ObjectId getId() {
		return id;
	}

	public List<String> getAuthorities() {
		return authorities;
	}

	public void setAuthorities(List<String> authorities) {
		this.authorities = authorities;
	}
}