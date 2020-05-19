package com.deepak.rating;

import org.jongo.Jongo;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.deepak.Utill.MongoDBUtill;

@RestController
@RequestMapping("/ratings")
public class RatingController {
	
	@RequestMapping("/movieId")
	public Ratings getRatings(@PathVariable("movieId") int movieId) {
		return new Jongo(MongoDBUtill.getDB()).getCollection("ratings").findOne("{movieId:#}",movieId).as(Ratings.class);
		
	}

	
	@RequestMapping("/addratings")
	public void addRatings(@RequestBody Ratings ratings) {
		new Jongo(MongoDBUtill.getDB()).getCollection("ratings").insert(ratings);
	}
}
