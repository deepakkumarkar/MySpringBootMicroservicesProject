package com.deepak.movie;

import org.jongo.Jongo;
import org.springframework.stereotype.Repository;

import com.deepak.utill.MongoDBUtill;

@Repository
public class MovieDaoImpl implements IMovieDao{

	@Override
	public Movie getMovieInfo(int movieId) {
		
		return new Jongo(MongoDBUtill.getDB()).getCollection("movie").findOne("{movieId:#}",movieId).as(Movie.class);
	}

	@Override
	public void addMovie(Movie movie) {
		
		new Jongo(MongoDBUtill.getDB()).getCollection("movie").insert(movie);

		
	}

}
