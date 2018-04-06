package com.example.Transion.app.model.user;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "addresses")
public class Address {

	@Id
	private String id;

	private String country;

	private String city;

	private String street;

	private Integer streetNumber;

	@Indexed(unique = true)
	private String postalCode;

	public Address() {
		super();
	}

	public Address(String country, String city, String street, Integer streetNumber, String postalCode) {
		super();
		this.country = country;
		this.city = city;
		this.street = street;
		this.streetNumber = streetNumber;
		this.postalCode = postalCode;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public Integer getStreetNumber() {
		return streetNumber;
	}

	public void setStreetNumber(Integer streetNumber) {
		this.streetNumber = streetNumber;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getId() {
		return id;
	}

}
