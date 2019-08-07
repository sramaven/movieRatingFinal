package com.capgemini.moviecatalogservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.capgemini.moviecatalogservice.model.Movie;
import com.capgemini.moviecatalogservice.model.Rating;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

@Service
public class MovieInfoService {
	
	@Autowired
	private RestTemplate restTemplate;
	  
		@HystrixCommand(fallbackMethod = "fallBackGetMovieInfo",
		commandProperties = {
				@HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds", value = "3000"),
				@HystrixProperty(name="circuitBreaker.requestVolumeThreshold", value = "10"),
				@HystrixProperty(name="circuitBreaker.errorThresholdPercentage", value = "50"),
				@HystrixProperty(name="circuitBreaker.sleepWindowInMilliseconds", value = "5000")					
		}) 


	public Movie getMovieInfo(Rating movieRating) {
		Movie movie=
				restTemplate.getForObject("http://movie-info-service/movies/"+movieRating.getMovieId(), Movie.class);
		return movie;
	}
	
	public Movie fallBackGetMovieInfo(Rating movieRating) {
		return new Movie(movieRating.getMovieId(),"Not Available",0,null);
	}

}
