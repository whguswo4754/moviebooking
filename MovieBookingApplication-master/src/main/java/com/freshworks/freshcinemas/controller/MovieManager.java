package com.freshworks.freshcinemas.controller;

import java.sql.*;
import java.util.ArrayList;
import com.freshworks.freshcinemas.model.Movie;
import com.freshworks.freshcinemas.util.*;

public class MovieManager {

	public boolean checkMovieAvailability(String movieName) throws BusinessControllerException {

		ArrayList<String> allMovieName = getAllMovieName();
		for (String movie : allMovieName) {
			if (movie.equalsIgnoreCase(movieName))
				return true;
		}
		return false;

	}

	public ArrayList<String> getAllMovieName() throws BusinessControllerException {
		Movie movieObject = new Movie();
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		ArrayList<String> movieName = new ArrayList<String>();

		try {
			conn = DBUtil.getConnection();
			String query = "SELECT movieName FROM movie";
			st = conn.createStatement();
			rs = st.executeQuery(query);

			while (rs.next()) {
				movieObject.setMovieName(rs.getString("movieName"));
				movieName.add(movieObject.getMovieName());
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new BusinessControllerException(e.getMessage());
		} finally {
			DBUtil.closeResources(conn,st, rs);
		}
		return movieName;
	}

	public Movie getMovieDetails(String movieName) throws BusinessControllerException {
		Movie movieDetail = new Movie();
		Connection conn = null;
		PreparedStatement st = null;
		ResultSet rs = null;

		try {
			conn = DBUtil.getConnection();

//If you want to display database name	//System.out.println("Connected " + conn.getMetaData().getDatabaseProductName());
			// use exact column names

			String query = "SELECT movieName,Genre,duration FROM movie where movieName =?";
			st = conn.prepareStatement(query);
			st.setString(1, movieName);
			rs = st.executeQuery();

			rs.next();
			movieDetail = new Movie(rs.getString("movieName"), rs.getString("Genre"), rs.getInt("duration"));

		} catch (Exception e) {
			e.printStackTrace();
			throw new BusinessControllerException(e.getMessage());
		} finally {
			DBUtil.closeResources(conn,st, rs);
		}
		return movieDetail;
	}

	public ArrayList<Movie> getAllMovieDetails() throws BusinessControllerException {
		Movie movieObject = new Movie();
		ArrayList<Movie> allMovieDetails = new ArrayList<Movie>();
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		
		try {
			conn = DBUtil.getConnection();
			String query = "SELECT movieName,Genre,duration FROM movie";
			st = conn.createStatement();
			rs = st.executeQuery(query);

			while (rs.next()) {
				movieObject = new Movie(rs.getString("movieName"), rs.getString("Genre"), rs.getInt("duration"));
				allMovieDetails.add(movieObject);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new BusinessControllerException(e.getMessage());
		} finally {
			DBUtil.closeResources(conn,st, rs);
		}
		return allMovieDetails;
	}
	
	public ArrayList<String> getRunningMovies(String theatreName) throws BusinessControllerException {
		Movie movieObject = new Movie();
		ArrayList<String> runningMovieNames = new ArrayList<String>();
		Connection conn = null;
		PreparedStatement st = null;
		ResultSet rs = null;

		try {
			conn = DBUtil.getConnection();
			String query = "SELECT movieName FROM movieTicket where theatreName=?";
			st = conn.prepareStatement(query);
			st.setString(1, theatreName);
			rs = st.executeQuery();
			while (rs.next()) {
				movieObject.setMovieName(rs.getString("movieName"));
				runningMovieNames.add(movieObject.getMovieName());
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new BusinessControllerException(e.getMessage());
		} finally {
			DBUtil.closeResources(conn,st, rs);
		}
		return runningMovieNames;
	}
	
	public void updateMovieTable(String movieName,String genre,int duration) throws BusinessControllerException {
		Connection conn = null;
		PreparedStatement st = null;

		try {
			conn = DBUtil.getConnection();
			String query = "INSERT into movie(movieName,genre,duration) values(?,?,?)";
			st = conn.prepareStatement(query);
			st.setString(1, movieName);
			st.setString(2, genre);
			st.setInt(3, duration);
			st.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new BusinessControllerException(e.getMessage());
		} finally {
			DBUtil.closeResources(conn,st, null);
		}
	}
}
