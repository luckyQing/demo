package com.liyulin.skills.data.mongodb;

import org.bson.Document;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Sorts;

import lombok.extern.slf4j.Slf4j;

/**
 * mongodb demo
 *
 * @author liyulin
 * @date 2019年3月3日下午12:27:45
 */
@Slf4j
public class MongodbDemo {

	public static void main(String[] args) {
		// connect to mongoDB, ip and port number
		MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017");

		// get database from MongoDB,
		// if database doesn't exists, mongoDB will create it automatically
		MongoDatabase db = mongoClient.getDatabase("mongo_db_test");

		// Get collection from MongoDB, database named "yourDB"
		// if collection doesn't exists, mongoDB will create it automatically
		MongoCollection<Document> collection = db.getCollection("yourCollection");

		// create a document to store key and value
		Document doc = new Document("name", "MongoDB").append("type", "database").append("No", 1).append("info",
				new Document("x", 203).append("y", 102));

		// save it into collection named "yourCollection"
		collection.insertOne(doc);

		// search query
		try (MongoCursor<Document> cursor = collection.find().iterator();) {
			while (cursor.hasNext()) {
				log.info(cursor.next().toJson());
			}
		}

		Document myDoc1 = collection.find(Filters.eq("No", 1)).first();
		log.info("myDoc1={}", myDoc1.toJson());

		Document myDoc2 = collection.find(Filters.and(Filters.gt("No", 0), Filters.lte("No", 20)))
				.sort(Sorts.descending("No")).first();
		log.info("myDoc2={}", myDoc2.toJson());
	}

}