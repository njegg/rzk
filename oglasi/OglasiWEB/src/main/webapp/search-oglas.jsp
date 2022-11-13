<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Pretraga oglasa</title>
	<link rel="stylesheet" href="styles.css">
</head>
<body>
	<nav>
		<a href="/OglasiWEB/">Home</a>
		<c:if test="${not empty username}">
		 	<a href="/OglasiWEB/LogoutServlet">Logout</a>
		</c:if>
	</nav>
	
	<form action="/OglasiWEB/SearchOglasiServlet">
		Pretraga
		<input type="text" name="text">
		<button type="submit">Pretrazi</button>
		<button onclick="window.location.href='/OglasiWEB/SearchOglasiServlet?all=true&text='">Prikazi sve</button>
	</form>
	
	<c:if test="${not empty msg}">
		${msg}
		<br>
	</c:if>

	<c:forEach items="${oglasi}" var="oglas">
		<a href="/OglasiWEB/OglasPrijavaServlet?oglasID=${oglas.idOglas}" class="oglas-item">
			<c:choose>
				<c:when test="${oglas.oglasKorisnik.username == username}">
					<font size="1" color="crimson">
						tvoj oglas
					</font>
				</c:when>
				<c:otherwise>
					<font size="1">
						napisao: ${oglas.oglasKorisnik.username}
					</font>
				</c:otherwise>
			</c:choose>
			<br>
			
			<font size="1">pregleda: ${oglas.brojPregleda}</font>
			<br>
			
			<span class="oglas-text">${oglas.text}</span>
		</a>
	</c:forEach>
</body>
</html>