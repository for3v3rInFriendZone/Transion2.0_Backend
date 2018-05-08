package com.example.Transion.app.model;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "transionUsers")
public class TransionUser {

	@Id
	private ObjectId id;

	private String firstname;

	private String lastname;

	@Indexed
	private String username;
	
	private String parentsName;
	
	private String citizenship;
	
	private String sex;
	
	private String education;

	private String password;

	@Indexed
	private String jmbg;

	private String phoneNumber;

	private List<String> authorities;
	
	private Boolean isActivated;

	@DBRef
	private Address address;
	
	@DBRef
	private Agency agency;

	public TransionUser() {
		super();
		this.isActivated = false;
	}

	public TransionUser(String firstname, String lastname, String username, String password, String jmbg,
			String phoneNumber, Address address, List<String> authorities) {
		super();
		this.firstname = firstname;
		this.lastname = lastname;
		this.username = username;
		this.password = password;
		this.jmbg = jmbg;
		this.phoneNumber = phoneNumber;
		this.address = address;
		this.authorities = authorities;
		this.isActivated = false;
	}

	public TransionUser(String firstname, String lastname, String username, String parentsName,
			String citizenship, String sex, String education, String password, String jmbg,
			String phoneNumber, List<String> authorities, Address address, Agency agency) {
		super();
		this.firstname = firstname;
		this.lastname = lastname;
		this.username = username;
		this.parentsName = parentsName;
		this.citizenship = citizenship;
		this.sex = sex;
		this.education = education;
		this.password = password;
		this.jmbg = jmbg;
		this.phoneNumber = phoneNumber;
		this.authorities = authorities;
		this.address = address;
		this.agency = agency;
		this.isActivated = false;
	}
	
	public Boolean getIsActivated() {
		return isActivated;
	}

	public void setIsActivated(Boolean isActivated) {
		this.isActivated = isActivated;
	}

	public String getParentsName() {
		return parentsName;
	}

	public void setParentsName(String parentsName) {
		this.parentsName = parentsName;
	}

	public String getCitizenship() {
		return citizenship;
	}

	public void setCitizenship(String citizenship) {
		this.citizenship = citizenship;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getEducation() {
		return education;
	}

	public void setEducation(String education) {
		this.education = education;
	}

	public void setId(ObjectId id) {
		this.id = id;
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
	
	public Agency getAgency() {
		return agency;
	}

	public void setAgency(Agency agency) {
		this.agency = agency;
	}
}