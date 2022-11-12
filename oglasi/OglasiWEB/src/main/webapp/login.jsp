<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
 <link rel="stylesheet" href="styles.css">
<title>Insert title here</title>
</head>
<body>
	<nav>
		<a href="/OglasiWEB/">Home</a>
	</nav>

	<form action="/OglasiWEB/LoginServlet" method="get">
		<input type="text" name="username" id="username"> <br>
		<input type="password" name="password" id="password"> <br>
		<input type="submit" value="Login">
	</form>
	
	<a href="/OglasiWEB/register.jsp">Registruj se ovde</a>
	<c:if test="${not empty msg}">
		${msg}
	</c:if>
</body>
</html>