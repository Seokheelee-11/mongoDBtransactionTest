package com.example.mongodbtest.service;

import org.springframework.stereotype.Service;

import com.example.mongodbtest.domain.Person;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class PersonService {

	public Person readPerson(String firstName) {
		
		Person findedPerson = new Person();
		
		
		return findedPerson;
	}
	
}
