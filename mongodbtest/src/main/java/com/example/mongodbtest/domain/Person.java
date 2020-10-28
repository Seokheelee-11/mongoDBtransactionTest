package com.example.mongodbtest.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document
public class Person {
	@Id
	private String id;
	
	private String firstName;
	private String lastName;
	private Integer num;
	
	public Integer getNumPlus() {
		return this.num + 1;
	}
	
	public Person() {
		this.firstName = "";
		this.lastName = "lee";
		this.num = 1;
	}
	
	public Person(String firstName, String lastName, Integer num) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.num = num++;
	}
}
