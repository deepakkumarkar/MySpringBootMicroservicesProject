package com.deepak.movie;

public interface IMovieDao {
	Movie getMovieInfo(int movieId);
	void addMovie(Movie movie);

}
