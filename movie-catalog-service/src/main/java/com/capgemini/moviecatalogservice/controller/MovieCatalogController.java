package com.capgemini.moviecatalogservice.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.capgemini.moviecatalogservice.model.CatalogClass;
import com.capgemini.moviecatalogservice.model.Movie;
import com.capgemini.moviecatalogservice.model.MovieCatalog;
import com.capgemini.moviecatalogservice.model.Rating;
import com.capgemini.moviecatalogservice.model.UserRating;
import com.capgemini.moviecatalogservice.service.MovieInfoService;
import com.capgemini.moviecatalogservice.service.MovieRatingService;

@RestController
@RequestMapping("/catalog")
@CrossOrigin(origins = "http://localhost:9000/movicatalog/")
public class MovieCatalogController {
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private MovieRatingService movieRatingService;
	
	@Autowired
	private MovieInfoService movieInfoService;
	
	@GetMapping("/{userId}")
	//@HystrixCommand(fallbackMethod = "fallBackGetMovieCatalog")
	public MovieCatalog getMovieCatalog(@PathVariable String userId)
	{
		//call movie-rating-service and get the rating details
		UserRating ratings = movieRatingService.getUserRating(userId);
		
		List<Rating> movieRatings=ratings.getRatings();
		List<CatalogClass> catalogItems=new ArrayList<CatalogClass>();
		
		//call movie-service and get movie details
	    for(Rating movieRating:movieRatings)
	    {
	    	Movie movie = movieInfoService.getMovieInfo(movieRating);
	    	 
	          catalogItems.add(new CatalogClass(movie,movieRating.getRating())); 	
	    }
		
		//We have to return MovieCatalog to client
	    MovieCatalog movieCatalog=new MovieCatalog(catalogItems);
	    return movieCatalog;
	
	}
//	@HystrixCommand(fallbackMethod = "fallBackGetMovieInfo")
//	private Movie getMovieInfo(Rating movieRating) {
//		Movie movie=
//				restTemplate.getForObject("http://movie-info-service/movies/"+movieRating.getMovieId(), Movie.class);
//		return movie;
//	}
//	@HystrixCommand(fallbackMethod = "fallBackGetUserRating")
//	private UserRating getUserRating(String userId) {
//		UserRating ratings=restTemplate.getForObject("http://movie-rating-service/movierating/"+userId, UserRating.class);
//		return ratings;
//	}
//	public MovieCatalog fallBackGetMovieCatalog(@PathVariable String userId)
//	{
//		List<CatalogClass> catalogItems=Arrays.asList(new CatalogClass(new Movie(0,"Not Available",0,null),0));
//		MovieCatalog movieCatalog=new MovieCatalog();
//		movieCatalog.setCatalogItems(catalogItems);
//		return movieCatalog;
//	}

}
