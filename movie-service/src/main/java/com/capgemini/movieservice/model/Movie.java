package com.capgemini.movieservice.model;

import java.util.List;

public class Movie {
	
	private int movieId;
	private String movieTitle;
	private int releaseYear;
	private List<String> casts;
	public int getMovieId() {
		return movieId;
	}
	public void setMovieId(int movieId) {
		this.movieId = movieId;
	}
	public String getMovieTitle() {
		return movieTitle;
	}
	public void setMovieTitle(String movieTitle) {
		this.movieTitle = movieTitle;
	}
	public int getReleaseYear() {
		return releaseYear;
	}
	public void setReleaseYear(int releaseYear) {
		this.releaseYear = releaseYear;
	}
	public List<String> getCasts() {
		return casts;
	}
	public void setCasts(List<String> casts) {
		this.casts = casts;
	}
	public Movie(int movieId, String movieTitle, int releaseYear, List<String> casts) {
		super();
		this.movieId = movieId;
		this.movieTitle = movieTitle;
		this.releaseYear = releaseYear;
		this.casts = casts;
	}
	
	

}
