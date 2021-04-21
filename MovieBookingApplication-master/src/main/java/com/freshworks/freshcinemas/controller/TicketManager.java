package com.freshworks.freshcinemas.controller;

import com.freshworks.freshcinemas.util.*;
import java.sql.*;

public class TicketManager {

	public int getAvailableSeats(String movieName, String theatreName) throws BusinessControllerException {
		Connection conn = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		int availableSeats;
		
		try {
			conn = DBUtil.getConnection();
			String query = "SELECT availableSeats FROM movieTicket where movieName=? and theatreName =?";
			st = conn.prepareStatement(query);
			st.setString(1, movieName);
			st.setString(2, theatreName);
			rs = st.executeQuery();
			rs.next();

			availableSeats = rs.getInt("availableSeats");
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new BusinessControllerException(e.getMessage());
		}finally {
			DBUtil.closeResources(conn,st, rs);
		}
		return availableSeats;
	}

	public void updateTotalSeats(String movieName, String theatreName, int seatsBooked)
			throws BusinessControllerException {
		Connection conn = null;
		PreparedStatement st = null;

		int availableSeats = getAvailableSeats(movieName, theatreName);
		availableSeats = availableSeats - seatsBooked;

		try {
			conn = DBUtil.getConnection();
			String query = "update movieTicket set availableSeats=? where movieName=? and theatreName =?";
			st = conn.prepareStatement(query);
			st = conn.prepareStatement(query);
			st.setInt(1, availableSeats);
			st.setString(2, movieName);
			st.setString(3, theatreName);
			st.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
			throw new BusinessControllerException(e.getMessage());
		}finally {
			DBUtil.closeResources(conn,st, null);
		}
	}
	
	public void addMovieInTheatre(String movieName,String theatreName) throws BusinessControllerException {
		int availableSeats=80;
		Connection conn = null;
		PreparedStatement st = null;
		

		try {
			conn = DBUtil.getConnection();
			String query = "INSERT into movieTicket(movieName,theatreName,availableSeats) values(?,?,?)";
			st = conn.prepareStatement(query);
			st.setString(1, movieName);
			st.setString(2, theatreName);
			st.setInt(3, availableSeats);
			st.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new BusinessControllerException(e.getMessage());
		} finally {
			DBUtil.closeResources(conn,st, null);
		}
	}

}
