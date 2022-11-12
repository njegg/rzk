<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Oglas</title>
	<link rel="stylesheet" href="styles.css">
</head>
<body>
	<nav>
		<a href="/OglasiWEB/">Home</a>
		<c:if test="${not empty username}">
		 	<a href="/OglasiWEB/LogoutServlet">Logout</a>
		</c:if>
	</nav>

	<div class="oglas-item">
		<font size="1">napisao <b style="display: inline">${oglas.oglasKorisnik.username}</b></font>
		<br>
		<font size="1">pregleda: ${oglas.brojPregleda}</font>
		<br>
		<span class="oglas-text">${oglas.text}</span>
	</div>
	
	<form action="/OglasiWEB/OglasPrijavaServlet?oglasID=${oglas.idOglas}" method="post">
		Javi se na oglas:
		<textarea name="text" placeholder="Napisi nesto..."></textarea>
		<button type="submit">Javi se</button>
	</form>
	
	<hr>
	
	<span>Prijave</span>
	
	<c:forEach items="${prijave}" var="prijava">
		<div class="oglas-item">
			<font size="1">
				OD:	<b style="display: inline">${prijava.oglasKorisnik.username}</b>
			</font>			
			<span class="oglas-text">${prijava.text}</span>
		</div>
	</c:forEach>
</body>
</html>