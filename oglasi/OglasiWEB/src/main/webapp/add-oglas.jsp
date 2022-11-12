<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="styles.css">
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<nav>
		<a href="/OglasiWEB/">Home</a>
		<c:if test="${not empty username}">
		 	<a href="/OglasiWEB/LogoutServlet">Logout</a>
		</c:if>
	</nav>

	<form action="/OglasiWEB/AddOglasServlet" method="post">
		Novi oglas
		<textarea name="text" placeholder="Write something"></textarea>
		<button type="submit">Dodaj</button>
	</form>
</body>
</html>