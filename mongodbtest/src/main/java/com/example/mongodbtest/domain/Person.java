package com.example.mongodbtest.domain;

import org.springframework.data.annotation.Id;

import lombok.Data;

@Data

public class Person {
	@Id
	private String id;
	
	String firstName;
	String lastName;
}
