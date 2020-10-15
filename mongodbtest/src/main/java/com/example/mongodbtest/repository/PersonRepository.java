package com.example.mongodbtest.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.mongodbtest.domain.Person;

public interface PersonRepository extends MongoRepository<Person, String> {

}
