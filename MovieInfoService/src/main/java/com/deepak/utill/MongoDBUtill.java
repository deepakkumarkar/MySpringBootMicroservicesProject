package com.deepak.utill;

import java.io.IOException;

import org.bson.Document;

import com.mongodb.DB;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoException;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

public class MongoDBUtill {


	private static MongoClient mongoClient = null;
	private static MongoDatabase db = null;
	private static DB jongoDB = null;

	private MongoDBUtill() {
	}

	static {
		initializeDB();
	}

	@SuppressWarnings("deprecation")
	private static void initializeDB() {
		if (mongoClient == null) {
			ServerAddress serverAddresses = new ServerAddress("localhost", 27017);
			if (serverAddresses != null ) {
				MongoClientOptions.Builder builder = new MongoClientOptions.Builder();
				// build the connection options
				builder.maxConnectionIdleTime(60000);
//				builder.sslEnabled(true);
				MongoClientOptions options = builder.build();
				
				mongoClient = new MongoClient(serverAddresses, options);
				mongoClient.slaveOk();
			}else{
				mongoClient = new MongoClient(serverAddresses);	
			}
		}
		if (db == null) {
			db = mongoClient.getDatabase("MicroservicesDemo");
		}
		if (jongoDB == null) {
			jongoDB = new DB(mongoClient, "MicroservicesDemo");
		}
	}

	public static DB getDB() {
		if (jongoDB == null) {
			initializeDB();
		}
		return jongoDB;
	}

	public static MongoCollection<Document> getCollection(String collectionName) {
		return getMongoDataBase().getCollection(collectionName);
	}

	public static MongoDatabase getMongoDataBase() {
		if (db == null) {
			initializeDB();
		}
		return db;
	}

	public static void closeCursor(MongoCursor<Document> cursor) {
		try {
			if (cursor != null) {
				cursor.close();
			}
		} catch (MongoException e) {
			System.out.println("Exception occurred while closing Mongo Cursor connection");
		}
	}

	@SuppressWarnings("rawtypes")
	public static void closeJongoCursor(org.jongo.MongoCursor cursor) {
		try {
			if (cursor != null) {
				cursor.close();
			}
		} catch (MongoException | IOException e) {
			System.err.print("Exception occurred while closing jongo connection");
		}
	}



}
