package com.example.Transion.app.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "test")
public class Test {

	@Id
	public String id;

	public String name;
	public String surname;

	public Test() {
	}

	public Test(String name, String surname) {
		super();
		this.name = name;
		this.surname = surname;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}
}