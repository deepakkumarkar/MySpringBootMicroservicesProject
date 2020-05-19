package com.deepak.movie;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MovieService {

	@Autowired
	IMovieDao iMovieDao;
	public Movie getMovieInfo(int movieId) {

		return iMovieDao.getMovieInfo(movieId);
	}

	public void addMovie(Movie movie) {
		iMovieDao.addMovie(movie);
		
	}

	
}
