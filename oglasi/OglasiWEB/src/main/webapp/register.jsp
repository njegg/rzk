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
		<a href="/OglasiWEB/">Home</a>
		<a href="/OglasiWEB/login.jsp">Login</a>
	</nav>

	<form action="/OglasiWEB/RegisterServlet" method="post">
		username: <input type="text" name="username" id="username"> <br>
		nickname: <input type="text" name="nickname" id="nickname"> <br>
		password: <input type="password" name="password" id="password"> <br>
		<input type="submit" value="Register">
	</form>
	${msg}
</body>
</html>