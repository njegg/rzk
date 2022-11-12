<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<link rel="stylesheet" href="styles.css">
</head>
<body>
	<h1>OGLASI</h1>
	<hr>
	<nav>
		<c:if test="${empty username}">
		 	<a href="/OglasiWEB/login.jsp">Login</a>
			<a href="/OglasiWEB/register.jsp">Registracija</a>
		</c:if>
		<c:if test="${not empty username}">
		 	<a href="/OglasiWEB/AddOglasServlet">Dodaj oglas</a>
		 	<a href="/OglasiWEB/search-oglas.jsp">Pretraga oglasa</a>
		 	
		 	<a href="/OglasiWEB/LogoutServlet">Logout</a>
		</c:if>
	</nav>
	
    <c:if test="${not empty username}">
		<div>Dobrodosao, <b><i>${username}</i></b></div>
    </c:if>
</body>
</html>