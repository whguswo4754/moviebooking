<%@ page language="java" contentType="text/html; charset=UTF-8"
	import="com.freshworks.freshcinemas.controller.*" import="java.util.*"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css"
	href="/FreshCinemasBookingApp/MyStyles.css" />
<title>FreshCinemas Home</title>

</head>

<h1>WELCOME TO FRESHCINEMAS !!!!!</h1>

<body>

	<%
		TheatreManager theatreManagerObj = new TheatreManager();
		MovieManager movieManagerObj = new MovieManager();
	%>

	<h3>We have following Movies to screen in our theatre:</h3>
	<%
		ArrayList<String> allMovieName = movieManagerObj.getAllMovieName();
	%><br />
	<br />
	<h4>Please Select the Movie to see its details:</h4>
	<form action="FreshCinemasUserHome.jsp">
		<select name="movieName" onchange="this.form.submit()">
			<option value=null>Select</option>
			<%
			    String previousMovie=null;
				String selectedMovie = (String) request.getParameter("movieName");
				Iterator<String> itr = allMovieName.iterator();
				while (itr.hasNext()) {
					String movieName = itr.next();
					if (movieName.equalsIgnoreCase(selectedMovie)&& movieName!=previousMovie) {
						%>
						<option value="<%=selectedMovie%>" selected><%=movieName%></option>
						<%
						
					} else { 
						%>
						<option value="<%=movieName%>"><%=movieName%></option>
						<%
					}
					previousMovie=movieName;
				}
			%>
		</select>
	</form>
	<%
		if (selectedMovie != null) {
	%>
	<br />
	<br />

	<h4>Please Select the Theatre to proceed for booking:</h4>
	<form action="TicketBookingDetails.jsp">
		<select name="theatreName">

			<%
				ArrayList<String> runningTheatres = theatreManagerObj.getRunningTheatres(selectedMovie);
					Iterator<String> theatreItr = runningTheatres.iterator();

					while (theatreItr.hasNext()) {
						String theatreName = theatreItr.next();
			%>
			<option value="<%=theatreName%>"><%=theatreName%></option>
			<%
				}
			%>
		</select> <br /> <br /> Please fill the details if you wish to Book Seats:<br />
		<input type="radio" name="movieName" value="<%=selectedMovie%>"
			checked>
		<%
			out.println(selectedMovie);
		%>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; No. of
		Seats:<input type="number" name="seatsToBook" min="1" max="10" required>&nbsp;
		<input type="submit" value="Submit">
	</form>
	<%
		}
	%>
</body>
</html>