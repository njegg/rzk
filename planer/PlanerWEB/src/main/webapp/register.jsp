<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
 <link rel="stylesheet" href="styles.css">
</head>
<body>
	<nav>
		<a href="/PlanerWEB">Home</a>
	</nav>

	<form action="/PlanerWEB/RegisterServlet" method="post">
		email: <input type="text" name="email" id="email"> <br>
		first name: <input type="text" name="firstName" id="firstName"> <br>
		last name: <input type="text" name="lastName" id="lastName"> <br>
		password: <input type="password" name="password" id="password"> <br>
		<input type="submit" value="Register">
	</form>
</body>
</html>