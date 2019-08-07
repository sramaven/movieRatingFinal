package com.capgemini.moviecatalogservice.service;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.capgemini.moviecatalogservice.model.Rating;
import com.capgemini.moviecatalogservice.model.UserRating;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

@Service
public class MovieRatingService {

	@Autowired
	private RestTemplate restTemplate;
	@HystrixCommand(fallbackMethod = "fallBackGetUserRating",
	commandProperties = {
			@HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds", value = "3000"),//Time to live
			@HystrixProperty(name="circuitBreaker.requestVolumeThreshold", value = "10"),//to analyse last 10 requests
			@HystrixProperty(name="circuitBreaker.errorThresholdPercentage", value = "50"),//if 50% of 10 consecutive requests fail
			@HystrixProperty(name="circuitBreaker.sleepWindowInMilliseconds", value = "5000")//after circuit breaks it will check server is up or not in 5 sec
	})
	public UserRating getUserRating(String userId) {
		UserRating ratings=restTemplate.getForObject("http://movie-rating-service/movierating/"+userId, UserRating.class);
		return ratings;
	}
	
	public UserRating fallBackGetUserRating(String userId) {
		UserRating userRating=new UserRating(userId,Arrays.asList(new Rating(0,0)));
		return userRating;
	}
}
