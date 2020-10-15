package com.example.mongodbtest.service;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import com.example.mongodbtest.domain.Person;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class PersonService {
	
	
	MongoTemplate mongoTemplate;

	public Person readPerson(String firstName) {
		
		Person findedPerson = new Person();
		
		Query query = new Query(Criteria.where("firstName").is(firstName));
		Update update = Update.update("lastName", "lee");
		
		mongoTemplate.findAndModify(query, update, Person.class);
		
		
		
		
		return findedPerson;
	}
	
	public void insertPerson(Person inputPerson) {
		mongoTemplate.insert(inputPerson);
		return;
	}
}
