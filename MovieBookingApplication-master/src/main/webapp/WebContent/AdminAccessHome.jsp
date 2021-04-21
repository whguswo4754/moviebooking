<%@ page language="java" contentType="text/html; charset=UTF-8"
	import="java.util.*" import="com.freshworks.freshcinemas.controller.*"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Admin Home</title>
<link rel="stylesheet" type="text/css"
	href="/FreshCinemasBookingApp/MyStyles.css" />
	
<script src="/FreshCinemasBookingApp/MyScript.js"></script>	

</head>
   
<body>


	<h5>
	<% if(request.getAttribute("searchResult") != null){
	   out.println(request.getAttribute("searchResult"));}
	%>
	<br />
	<% if(request.getAttribute("updateResult") != null){
		   out.println(request.getAttribute("updateResult"));
		}
	%>
	</h5>
	<br/>

	<h2>HELLO ADMIN.....</h2>
	<br />
	<br />

	<form action="AdminNewDbDetails">
		Please Enter the given details correctly to update our database:<br>
		<br>
		<h4>Movie Name:</h4>
		<input type="text" name="movieName" required pattern="[a-z A-Z 0-9]{0,20}" title="Movie name can contain only Characters or Numbers">
		<h4>Available Theatres</h4>
		<select name="theatreName">
			<%
				TheatreManager theatreManagerObj = new TheatreManager();
				ArrayList<String> availableTheatres = theatreManagerObj.getAllTheatreName();
				Iterator<String> theatreItr = availableTheatres.iterator();

				while (theatreItr.hasNext()) {
					String theatreName = theatreItr.next();
			%>
			<option value="<%=theatreName%>"><%=theatreName%></option>
			<%
				}
			%>
		</select> 
		<br>
		<br> 
		<input type="submit" value="Submit">
	</form>

</body>
</html>