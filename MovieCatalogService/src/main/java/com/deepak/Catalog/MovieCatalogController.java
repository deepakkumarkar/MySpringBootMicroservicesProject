package com.deepak.Catalog;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.jongo.Jongo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.deepak.utill.MongoDBUtill;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogController {
	 
	@Autowired
	CatalogService catalogService;
	
	@Autowired
	RestTemplate restTemplete;
	
	@RequestMapping("{id}")
	public List<CatalogItem> getCatalog(@PathVariable("id") int id){
	
		Iterator<Ratings> itr = new Jongo(MongoDBUtill.getDB()).getCollection("ratings").find().as(Ratings.class).iterator();
		List<Ratings> ratings= new ArrayList<Ratings>();
		while(itr.hasNext()) {
			ratings.add(itr.next());
		}
//		Movie movie =restTemplete.getForObject("http://localhost:8082/MovieInfoService/movie/1", Movie.class);
//		return ratings.stream().Map(rating ->{
//			restTemplete.getForObject("http://localhost:8082/MovieInfoService/movie/"+rating.getMovieId(), Movie.class);
//			catalogService.getCatalog(id);
//		})
				List<CatalogItem> items= ratings.stream().map(rating ->{
					
					Movie movie =restTemplete.getForObject("http://localhost:8082/MovieInfoService/movie/"+rating.getMovieId(), Movie.class);
					return catalogService.getCatalog(rating.getMovieId());
					
				}).collect(Collectors.toList());
				return items;
		
		
		
	}
	
	@PostMapping("/add")
	public void addCatalog(@RequestBody CatalogItem CatalogItem) {
		 catalogService.addCatalog(CatalogItem);
	}
	

}
