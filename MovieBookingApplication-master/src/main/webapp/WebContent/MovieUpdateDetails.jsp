<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Updating FreshCinemas</title>
<link rel="stylesheet" type="text/css"
	href="/FreshCinemasBookingApp/MyStyles.css" />
</head>
<body>
	<h5>
		<%
			if (request.getAttribute("searchResult") != null) {
				out.println(request.getAttribute("searchResult"));
			}
		%>
	</h5>
	<br />
	<%
		String selectedTheatre = (String) request.getParameter("theatreName");
		String selectedMovie = (String) request.getParameter("movieName");
	%>

	<form action="AdminDbUpdate">

		<h3>You are adding movie to following theatre:</h3>
		Theatre : <input type="text" name="theatreName"
			value="<%=selectedTheatre%>" readonly>
		<%
			//out.println(selectedTheatre);
		%><br> <br>

		<h3>Please give the details to add the Movie in Theatres:</h3>
		Movie name: <input type="text" name="movieName"
			value="<%=selectedMovie%>" readonly>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		Genre: <input type="text" name="movieGenre" required>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		Duration(in minutes): <input type="number" name="duration" min="30"
			max="220" required><br> <br> <br> <input
			type="submit" value="Add Movie to Theatre">
	</form>

</body>
</html>