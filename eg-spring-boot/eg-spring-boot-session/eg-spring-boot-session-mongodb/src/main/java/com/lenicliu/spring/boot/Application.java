package com.lenicliu.spring.boot;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

@SpringBootApplication
public class Application implements CommandLineRunner {
	public static void main(String[] args) {
		new SpringApplicationBuilder(Application.class).web(false).run(args);
	}

	@Autowired
	private MongoTemplate mongoTemplate;

	@Override
	public void run(String... args) throws Exception {
		DBCollection collection = mongoTemplate.createCollection(User.class);
		System.out.println("FullName:" + collection.getFullName());
		System.out.println("Collection:" + collection);
		System.out.println();

		BasicDBObject user1 = new BasicDBObject().append("id", "1").append("name", "lenic");
		BasicDBObject user2 = new BasicDBObject().append("id", "2").append("name", "richard");
		collection.insert(Arrays.asList(user1, user2));
		System.out.println("Collection:" + collection);
		System.out.println("Total Count:" + collection.getCount());
		System.out.println();

		DBCursor cursor = collection.find(null);
		while (cursor.hasNext()) {
			DBObject next = cursor.next();
			System.out.println(next);
		}
		System.out.println();

		BasicDBObject user1remove = new BasicDBObject().append("id", "1").append("name", "lenic");
		collection.remove(user1remove);
		System.out.println("Total Count:" + collection.getCount());
	}

	public static class User {
		private Long	id;
		private String	name;

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}
	}
}