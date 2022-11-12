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
<nav>
	<a href="/PlanerWEB/">Home</a>
	<a href="/PlanerWEB/search-events.jsp">Search Events</a>
</nav>
	${today }
	<form action="/PlanerWEB/AddEventServlet" method="post">
			description: <textarea  name="description" id="description" cols="25" ></textarea>
			start: <input type="datetime-local" name="fromDate" id="fromDate" value="${today }">
			end: <input type="datetime-local" name="toDate" id="toDate" value="${today }">
			
			<select name="eventTypeId">
	    		<c:forEach items="${types}" var="type">
	        		<option value="${type.id}">${type.name}</option>
	    		</c:forEach>
			</select>
	
			<input type="submit" value="Add">
	</form>

	<c:if test="${not empty msg }">
		<hr>
		<h4>${msg }</h4>
	</c:if>
</body>
</html>