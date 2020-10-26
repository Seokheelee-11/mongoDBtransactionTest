package com.example.mongodbtest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import com.example.mongodbtest.domain.Person;
import com.example.mongodbtest.repository.PersonRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class PersonService {

	@Autowired
	private MongoTemplate mongoTemplate;

	@Autowired
	private PersonRepository personRepository;

	public List<Person> readPerson(String firstName) {

		Query query = new Query(Criteria.where("firstName").is(firstName));
		// Person user = mongoTemplate.find(query, Person.class);

		List<Person> users = mongoTemplate.find(query, Person.class);

		for (int i = 0; i < users.size(); i++) {
			log.info(users.get(i).toString());
		}

//		Update update = Update.update("lastName", "lee");
//		mongoTemplate.findAndModify(query, update, Person.class);

		return users;
	}

	public Person updatePerson(String firstName) {

		Query query = new Query(Criteria.where("firstName").is(firstName));
		// String update = "{$set: {firstName: \"Updated\"}";
		firstName = firstName.concat("22");
		
		Person user = mongoTemplate.findAndModify(query, Update.update("secondName", firstName), Person.class);
		log.info(firstName);
		log.info(user.toString());
//		Update update = Update.update("lastName", "lee");
//		mongoTemplate.findAndModify(query, update, Person.class);

		return user;
	}

	public void insertPerson(Person inputPerson) {

		Person user = inputPerson;
		// personRepository.save(user);
		mongoTemplate.insert(user);

		return;
	}

}
