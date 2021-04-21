<%@ page language="java" contentType="text/html; charset=UTF-8"
	import="com.freshworks.freshcinemas.controller.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Seats Status</title>
<script src="/FreshCinemasBookingApp/MyScript.js"></script>
</head>
<body>
	<%
		TicketManager ticketManagerObj = new TicketManager();
		String movieName = request.getParameter("movieName");
		String theatreName = request.getParameter("theatreName");
		int seatsToBook = Integer.parseInt(request.getParameter("seatsToBook"));
		int availableSeats = ticketManagerObj.getAvailableSeats(movieName, theatreName);
	%>

	Seats To Book:
	<%
		out.println(seatsToBook);
	%><br /> Available seats for the show:
	<%
		out.println(availableSeats);
	%><br />

	<%
		if (availableSeats >= seatsToBook) {
	%>
	Congrats........Required number of seats are available for booking !!!
	<br /> Seats available after booking:<%
		out.println(availableSeats - seatsToBook);
	%>
	<%
		} else {
	%>
	Sorry....We do not have have require number of seats
	<%
		}
	%>
	<br />
	<br />
	<br />

	<form action="FreshCinemasUserHome.jsp"
		onsubmit="return confirmToHomePage()">
		<input type="submit" value="Back to Home Page">&nbsp;&nbsp;&nbsp;
	</form>
	<br />
	<br />
	<form action="FreshCinemasUserHome.jsp"
		onsubmit="return confirmToGateway()">
		<input type="submit" value="Proceed To Payment Gateway"
			onclick="alert('Sorry,Payment Gateway currently not available...')">
	</form>

</body>
</html>