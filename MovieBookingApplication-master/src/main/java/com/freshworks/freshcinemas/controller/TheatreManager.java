package com.freshworks.freshcinemas.controller;

import java.sql.*;
import java.util.ArrayList;
import com.freshworks.freshcinemas.model.Theatre;
import com.freshworks.freshcinemas.util.*;

public class TheatreManager {

	public boolean checkTheatreAvailability(String theatreName) throws BusinessControllerException {

		ArrayList<String> allTheatreName = getAllTheatreName();
		for (String theatre : allTheatreName) {
			if (theatre.equalsIgnoreCase(theatreName))
				return true;
		}
		return false;
	}

	public ArrayList<String> getAllTheatreName() throws BusinessControllerException {
		Theatre theatreObject = new Theatre();
		ArrayList<String> allTheatreNames = new ArrayList<String>();
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;

		try {
			conn = DBUtil.getConnection();
			String query = "SELECT theatreName FROM theatre";
			st = conn.createStatement();
			rs = st.executeQuery(query);

			while (rs.next()) {
				theatreObject.setTheatreName(rs.getString("theatreName"));
				allTheatreNames.add(theatreObject.getTheatreName());
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new BusinessControllerException(e.getMessage());
		} finally {
			DBUtil.closeResources(conn,st, rs);
		}
		return allTheatreNames;
	}

	public Theatre getTheatreDetails(String theatreName) throws BusinessControllerException {
		Theatre theatreDetail = new Theatre();
		Connection conn = null;
		PreparedStatement st = null;
		ResultSet rs = null;

		try {
			conn = DBUtil.getConnection();
			String query = "SELECT theatreName,totalScreens,basePrice FROM theatre where theatreName =?";
			st = conn.prepareStatement(query);
			st.setString(1, theatreName);
			rs = st.executeQuery();
			rs.next();

			theatreDetail = new Theatre(rs.getString("theatreName"), rs.getInt("totalScreens"), rs.getInt("basePrice"));
		} catch (Exception e) {
			e.printStackTrace();
			throw new BusinessControllerException(e.getMessage());
		} finally {
			DBUtil.closeResources(conn,st, rs);
		}
		return theatreDetail;
	}

	public ArrayList<Theatre> getAllTheatreDetails() throws BusinessControllerException {
		Theatre theatreObject = new Theatre();
		ArrayList<Theatre> allTheatreDetail = new ArrayList<Theatre>();
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;

		try {
			conn = DBUtil.getConnection();
			String query = "SELECT theatreName,totalScreens,basePrice FROM theatre";
			st = conn.createStatement();
			rs = st.executeQuery(query);

			while (rs.next()) {
				theatreObject = new Theatre(rs.getString("theatreName"), rs.getInt("totalScreens"),
						rs.getInt("basePrice"));
				allTheatreDetail.add(theatreObject);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new BusinessControllerException(e.getMessage());
		} finally {
			DBUtil.closeResources(conn,st, rs);
		}
		return allTheatreDetail;
	}

	public ArrayList<String> getRunningTheatres(String movieName) throws BusinessControllerException {
		Theatre theatreObject = new Theatre();
		ArrayList<String> runningTheatreNames = new ArrayList<String>();
		Connection conn = null;
		PreparedStatement st = null;
		ResultSet rs = null;

		try {
			conn = DBUtil.getConnection();
			String query = "SELECT theatreName FROM movieTicket where movieName =?";
			st = conn.prepareStatement(query);
			st.setString(1, movieName);
			rs = st.executeQuery();
			while (rs.next()) {
				theatreObject.setTheatreName(rs.getString("theatreName"));
				runningTheatreNames.add(theatreObject.getTheatreName());
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new BusinessControllerException(e.getMessage());
		} finally {
			DBUtil.closeResources(conn,st, rs);
		}
		return runningTheatreNames;
	}

	public int totalScreens(String theatreName) throws BusinessControllerException {
		Connection conn = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		int screens;
		
		try {
			conn = DBUtil.getConnection();
			String query = "SELECT totalScreens FROM theatre where theatreName=?";
			st = conn.prepareStatement(query);
			st.setString(1, theatreName);
			rs = st.executeQuery();
			rs.next();
			screens = rs.getInt("totalScreens");
			return screens;
		} catch (Exception e) {
			e.printStackTrace();
			throw new BusinessControllerException(e.getMessage());
		} finally {
			DBUtil.closeResources(conn,st, rs);
		}
		
	}

	public int totalMoviesRunningInTheatre(String theatreName) {
		MovieManager movieManagerObj = new MovieManager();
		int totalRunningMovies = 0;

		try {
			ArrayList<String> runningMovies = movieManagerObj.getRunningMovies(theatreName);
			totalRunningMovies = runningMovies.size();
			return totalRunningMovies;

		} catch (BusinessControllerException e) {
			e.printStackTrace();
		}
		return totalRunningMovies;
	}

	public boolean checkMovieAvailabilityInTheatre(String movieName, String theatreName) {
		ArrayList<String> runningTheatreNames = new ArrayList<String>();

		try {
			runningTheatreNames = getRunningTheatres(movieName);

			for (String runningTheatre : runningTheatreNames) {
				if (runningTheatre.equalsIgnoreCase(theatreName))
					return true;
			}
		} catch (BusinessControllerException e) {
			e.printStackTrace();
		}
		return false;

	}
}