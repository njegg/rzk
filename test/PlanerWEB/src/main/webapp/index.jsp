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
		<c:if test="${empty email }">
		 	<a href="/PlanerWEB/login.jsp">Login</a>
			<a href="/PlanerWEB/register.jsp">Register</a>
		</c:if>
		<c:if test="${not empty email }">
		 	<a href="/PlanerWEB/LogoutServlet">Logout</a>
		</c:if>
		<a href="/PlanerWEB/search-events.jsp">Search Events</a>
		<a href="/PlanerWEB/AddEventServlet">Add event</a>
	</nav>
	
	<c:if test="${not empty email }">
		<div>Welcome,<b><i>${email }</i></b></div>
	</c:if>
</body>
</html>