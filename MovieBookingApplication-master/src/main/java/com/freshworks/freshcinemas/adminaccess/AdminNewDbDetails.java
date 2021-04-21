package com.freshworks.freshcinemas.adminaccess;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.freshworks.freshcinemas.controller.*;

@WebServlet("/AdminNewDbDetails")
public class AdminNewDbDetails extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AdminNewDbDetails() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		MovieManager movieManagerObj = new MovieManager();
		TheatreManager theatreManagerObj = new TheatreManager();
		response.setContentType("text/html");
		String submittedMovieName = request.getParameter("movieName");
		String theatreName = request.getParameter("theatreName");
		RequestDispatcher rd = request.getRequestDispatcher("AdminAccessHome.jsp");
		RequestDispatcher rdPost = request.getRequestDispatcher("MovieUpdateDetails.jsp");
		int totalRunningMovies = theatreManagerObj.totalMoviesRunningInTheatre(theatreName);
		String movieName = submittedMovieName.trim();
		String searchResult = null;
		String result = null;

		try {
			int totalScreens = theatreManagerObj.totalScreens(theatreName);

			if (movieManagerObj.checkMovieAvailability(movieName)) {
				result = "Given movie [" + movieName + "]  is already running in Theatre <br/>"
						+ " Its Running in following theatres: " + theatreManagerObj.getRunningTheatres(movieName)
						+ " <br/>";

				if (!theatreManagerObj.checkMovieAvailabilityInTheatre(movieName, theatreName)
						&& totalRunningMovies < totalScreens) {
					searchResult = result + "The theatre [" + theatreName + "]  is available to add movie[" + movieName
							+ "]....You can proceed....";

					request.setAttribute(theatreName, theatreName);
					request.setAttribute("searchResult", searchResult);
					rdPost.include(request, response);
					// Call to update database ..updateTable(MovieName,TheatreName)

				} else {
					searchResult = result + "Sorry!! Theatre [" + theatreName
							+ "] is not available to add Movie...  Please select another Theatre or remove any running Movie ";
					request.setAttribute("searchResult", searchResult);
					rd.include(request, response);
				}
			} else {

				if (totalRunningMovies < totalScreens) {
					searchResult = "The theatre [" + theatreName + "]  is available to add movie[" + movieName
							+ "]....You can proceed....";

					request.setAttribute(theatreName, theatreName);
					request.setAttribute("searchResult", searchResult);
					rdPost.include(request, response);
					// Call to update database....updateTable(MovieName,TheatreName)

				} else {
					searchResult = "Sorry!! theatre [" + theatreName
							+ "]  is not available to add Movie...  Please select another Theatre or remove any running Movie ";

					request.setAttribute("searchResult", searchResult);
					rd.forward(request, response);
				}
			}

		} catch (BusinessControllerException e) {
			e.printStackTrace();
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
