package com.deepak.movie;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movie")
public class MovieController {

	@Autowired
	MovieService movieService;
	
	@RequestMapping("/{movieId}")
	public Movie getMovieInfo(@PathVariable("movieId") int movieId) {
		
		
		return movieService.getMovieInfo(movieId);
		
		
	}
	
	@PostMapping("/addmovie")
	public void addMovie(@RequestBody Movie movie) {
		
		movieService.addMovie(movie);
	}
	
}
