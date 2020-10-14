package com.example.mongodbtest.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.mongodbtest.domain.Person;
import com.example.mongodbtest.service.PersonService;

import lombok.RequiredArgsConstructor;



@RestController
@RequestMapping("person")
@RequiredArgsConstructor
public class PersonController {

	private final PersonService personService;
	
	@PostMapping
	public Person getPerson(String firstName)  {
		
		return personService.readPerson(firstName);
	}
	
}
