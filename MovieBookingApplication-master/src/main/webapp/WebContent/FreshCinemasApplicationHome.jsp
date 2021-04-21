<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Application Home</title>
<link rel="stylesheet" type="text/css" href="/FreshCinemasBookingApp/MyStyles.css" />
</head>
<body>

	<h3>HELLO THERE !!!!!...........</h3>
	<br>
	<br> Please tell us about yourself:
	<br>
	<br>

	<form action="FreshCinemasUserHome.jsp">
		<input type="submit" value="You are a User">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<input type="submit" formaction="AdminAccessHome.jsp"
			value="You are an Admin"
			onclick="alert('We are giving you the Admin permissions...')">
	</form>

</body>
</html>