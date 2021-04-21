package com.freshworks.freshcinemas.model;

public class Movie {

	private String movieName;
	private String movieGenre;
	private int duration;

	public String getMovieName() {
		return movieName;
	}

	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}

	public String getMovieGenre() {
		return movieGenre;
	}

	public void setMovieGenre(String movieGenre) {
		this.movieGenre = movieGenre;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}
	
	public Movie() {
		
	}
	
	public Movie(String movieName,String movieGenre,int duration) {
		this.movieName = movieName;
		this.movieGenre = movieGenre;
		this.duration = duration;
	}

}
