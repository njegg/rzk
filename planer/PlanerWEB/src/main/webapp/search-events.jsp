<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
 <link rel="stylesheet" href="styles.css">
</head>
<body>
	<nav>
		<a href="/PlanerWEB/">Home</a>
		<a href="/PlanerWEB/AddEventServlet">Add event</a>
	</nav>
	
	<form action="/PlanerWEB/SearchEventsServlet" method="post">
			Date: <input type="date" name="date" id="date" value="${today}"> <br>
			
			<input type="submit" value="Search">
	</form>
	
	<hr>
	
	<c:forEach items="${searchResult }" var="event">
		<div style="border-bottom:  1px solid gray; width: 300px">
			<b>${event.eventType.name}</b>
			 <small>
			 	<fmt:formatDate value="${event.fromDate}" pattern="HH:mm" />-
			 	<fmt:formatDate value="${event.toDate}" pattern="HH:mm" />
			 </small>
			<br>
			${event.description}
			<br>
			
		</div>
	</c:forEach>	
	
</body>
</html>