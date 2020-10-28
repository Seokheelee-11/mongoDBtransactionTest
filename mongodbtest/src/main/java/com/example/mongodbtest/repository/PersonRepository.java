package com.example.mongodbtest.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.mongodbtest.domain.Person;

public interface PersonRepository extends MongoRepository<Person, String> {

	Person findOneByFirstNameAndLastName(String firstName, String lastName);
	List<Person> findAllByFirstName(String firstName);
	
}
