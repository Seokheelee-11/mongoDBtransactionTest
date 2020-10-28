package com.example.mongodbtest.service;

import java.util.List;

import org.bson.BsonDocument;
import org.bson.conversions.Bson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.example.mongodbtest.domain.Person;
import com.example.mongodbtest.repository.PersonRepository;
import com.mongodb.MongoCommandException;
import com.mongodb.TransactionOptions;
import com.mongodb.WriteConcern;
import com.mongodb.client.ClientSession;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class PersonService {

	@Autowired
	private MongoTemplate mongoTemplate;

	private static MongoClient client;

	String uri = "mongodb+srv://chatbot:dbwj123!@chatbot.v13hn.mongodb.net/test?retryWrites=true&w=majority";
	private static MongoCollection<Person> personCollection;

	private final Bson filterSeokHee = Filters.eq("firstName", "seokhee");
	private final Bson incNum = Updates.inc("num", 2);
//	private final String incNum = "{$inc: {\"num\",1}";

	@Autowired
	private PersonRepository personRepository;

	public void updatePerson(String firstName, String lastName) {

		// Query query = new
		// Query(Criteria.where("firstName").is(firstName).where("lastName"));
		// String update = "{$set: {firstName: \"Updated\"}";
//		firstName = firstName.concat("22");
		// List<Person> users = mongoTemplate.find(query, Person.class);

//		String uri = "mongodb://localhost:27017";

		initMongoDB(uri);
		// clearCollections();
		setNumIncTransaction();

//		Person user = personRepository.findOneByFirstNameAndLastName(firstName, lastName);
//		log.info(user.toString());
//		user.setNum(user.getNumPlus());
//		personRepository.save(user);

		// return user;
	}

	private void setNumIncTransaction() {
		ClientSession session = client.startSession();
		try {
			session.startTransaction(TransactionOptions.builder().writeConcern(WriteConcern.MAJORITY).build());
			setNumIncrease(session);
			sleep();
			session.commitTransaction();
		} catch (MongoCommandException e) {
			session.abortTransaction();
			System.out.println("####### ROLLBACK TRANSACTION #######");
		} finally {
			session.close();
			System.out.println("####################################\n");
			printDatabaseState();
		}
	}

	private void setNumIncrease(ClientSession session) {

		personCollection.updateOne(session, filterSeokHee, incNum);
	}

	private void sleep() {
		System.out.println("Sleeping 3 seconds...");
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			System.err.println("Oups...");
			e.printStackTrace();
		}
	}

	private void printDatabaseState() {
		log.info("Database state:");
		//printPersons(personCollection.find().into(new ArrayList<>()));
		printPersons();
	}

	private void printPersons() {
		FindIterable<Person> person = personCollection.find();
		log.info(person.toString());
//		for (int i = 0; i < persons.size(); i++) {
//			log.info(persons.get(i).toString());
//		}

	}

	private void clearCollections() {
		personCollection.deleteMany(new BsonDocument());
	}

	private static void initMongoDB(String mongodbURI) {

		client = MongoClients.create(mongodbURI);
		MongoDatabase db = client.getDatabase("test");
		personCollection = db.getCollection("person", Person.class);
	}

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

	public void insertPerson(Person inputPerson) {
		initMongoDB(uri);
		ClientSession session = client.startSession();

		
		try {
			session.startTransaction(TransactionOptions.builder().writeConcern(WriteConcern.MAJORITY).build());
			
			
			personRepository.save(inputPerson);
			Integer isCount = personRepository.findAll().size();
			log.info(isCount.toString());
			sleep();
			isCount = personRepository.findAll().size();
			log.info(isCount.toString());
			session.commitTransaction();
		} catch (MongoCommandException e) {
			session.abortTransaction();
			System.out.println("####### ROLLBACK TRANSACTION #######");
		} finally {
			session.close();
			System.out.println("####################################\n");
			log.info(inputPerson.toString());
		}
		
		
//		Person user = inputPerson;
//		personRepository.save(user);
		 //mongoTemplate.insert(user);

		return;
	}
	

}
