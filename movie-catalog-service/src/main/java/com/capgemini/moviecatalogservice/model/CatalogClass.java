package com.capgemini.moviecatalogservice.model;

public class CatalogClass {
	private Movie movie;
	private int rating;
	public Movie getMovie() {
		return movie;
	}
	public void setMovie(Movie movie) {
		this.movie = movie;
	}
	public int getRating() {
		return rating;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}
	public CatalogClass(Movie movie, int rating) {
		super();
		this.movie = movie;
		this.rating = rating;
	}
	public CatalogClass() {
		super();
	}
	
	

}
