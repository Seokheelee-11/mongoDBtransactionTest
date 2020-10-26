package com.example.mongodbtest.domain;

import org.springframework.data.annotation.Id;

import lombok.Data;

@Data
public class Person {
	@Id
	private String id;
	
	String firstName;
	String lastName;
	
	public Person() {
		this.firstName = "";
		this.lastName = "lee";
	}
	
	public Person(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
	}
}
