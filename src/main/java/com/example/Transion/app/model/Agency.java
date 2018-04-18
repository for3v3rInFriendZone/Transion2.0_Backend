package com.example.Transion.app.model;

import java.util.Date;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "agency")
public class Agency {

	@Id
	private ObjectId id;
	
	private	String name;
	
	private String fullName;
	
	private String pib;
	
	private String uniqueKey;
	
	private String accountNumber;
	
	private String activityCode;
	
	private Date registrationDate;
	
	@DBRef
	private Address address;
	
	private String phone;
	
	private String email;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getPib() {
		return pib;
	}

	public void setPib(String pib) {
		this.pib = pib;
	}

	public String getUniqueKey() {
		return uniqueKey;
	}

	public void setUniqueKey(String uniqueKey) {
		this.uniqueKey = uniqueKey;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getActivityCode() {
		return activityCode;
	}

	public void setActivityCode(String activityCode) {
		this.activityCode = activityCode;
	}

	public Date getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(Date registrationDate) {
		this.registrationDate = registrationDate;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Agency(String name, String fullName, String pib, String uniqueKey, String accountNumber, String activityCode,
			Date registrationDate, Address address, String phone, String email) {
		super();
		this.name = name;
		this.fullName = fullName;
		this.pib = pib;
		this.uniqueKey = uniqueKey;
		this.accountNumber = accountNumber;
		this.activityCode = activityCode;
		this.registrationDate = registrationDate;
		this.address = address;
		this.phone = phone;
		this.email = email;
	}
		
}
