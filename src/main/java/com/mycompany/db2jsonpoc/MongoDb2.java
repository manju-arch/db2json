package com.mycompany.db2jsonpoc;

import java.net.UnknownHostException;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.Mongo;
import com.mongodb.MongoException;

public class MongoDb2 {

	public static void main(String[] args) throws UnknownHostException, MongoException {
		// Creating a Mongo client
				
		Mongo mongo = new Mongo("localhost", 27017);
		DB database = mongo.getDB("jsondb");
		database.createCollection("sampleCollection", null);
		DBCollection dbCollection = database.getCollection("sampleCollection");
		
		//insert(dbCollection);
		 
		
		BasicDBObject searchQuery = new BasicDBObject();
		searchQuery.put("name", "Manju");
		DBCursor cursor = dbCollection.find(searchQuery);
		 
		while (cursor.hasNext()) {
		    System.out.println(cursor.next());
		}
		
		mongo.close();
	}
	
	public static void insert(DBCollection dbCollection) {
		  BasicDBObject document = new BasicDBObject(); document.put("name",
		  "Manju"); document.put("company", "Google");
		  dbCollection.insert(document);
	}

}
