package com.example.mongodbtest.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.mongodbtest.domain.Person;
import com.example.mongodbtest.service.PersonService;

import lombok.RequiredArgsConstructor;



@RestController
@RequestMapping("Person")
@RequiredArgsConstructor
public class PersonController {

	private final PersonService personService;
	
	@GetMapping("firstName/{firstName}")
	public List<Person> getPerson(String firstName)  {
		
		return personService.readPerson(firstName);
	}
	
	@PutMapping("firstName/{firstName}")
	public Person updatePerson(String firstName)  {
		
		return personService.updatePerson(firstName);
	}
	
	@PostMapping
	public void inputPerson(@RequestBody Person inputPerson) {
		personService.insertPerson(inputPerson);
	}
	
}
