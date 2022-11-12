<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
 <link rel="stylesheet" href="styles.css">
<title>Insert title here</title>
</head>
<body>
	<nav>
		<a href="/PlanerWEB">Home</a>
	</nav>

	<form action="/PlanerWEB/LoginServlet" method="post">
		<input type="text" name="username" id="username"> <br>
		<input type="password" name="password" id="password"> <br>
		<input type="submit" value="Login">
	</form>
	
	<a href="/PlanerWEB/register.jsp">Register here</a>
</body>
</html>