package com.freshworks.freshcinemas.adminaccess;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.freshworks.freshcinemas.controller.*;

@WebServlet("/AdminDbUpdate")
public class AdminDbUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AdminDbUpdate() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		MovieManager movieManagerObj = new MovieManager();
		response.setContentType("text/html");
		String movieName = request.getParameter("movieName");
		String movieGenre = request.getParameter("movieGenre");
		String stringDuration = request.getParameter("duration");
		String result = null;
		RequestDispatcher rdAdminAccessHome = request.getRequestDispatcher("AdminAccessHome.jsp");

		try {
			int duration = Integer.parseInt(stringDuration);
			movieManagerObj.updateMovieTable(movieName, movieGenre, duration);
			result = "Congrats !!!.....Data Successfully Updated: <br/>" + "Movie successfully added to Theatre";

			// PrintWriter out = response.getWriter();
			// this was getting printed directly on jsp out.println("Congrats !!!.....Data
			// Successfully Updated: <br/>");
			// out.println("Movie successfully added to Theatre");

			request.setAttribute("updateResult", result);
			rdAdminAccessHome.include(request, response);
		} catch (BusinessControllerException e) {
			e.printStackTrace();
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
