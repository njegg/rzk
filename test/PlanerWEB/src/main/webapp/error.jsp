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
	<a href="/PlanerWEB/">Home</a>
	<c:if test="${ empty email}">
		<a href="/PlanerWEB/login.jsp">Login</a>
	</c:if>
	<h4>What a mistaka to maka .jsp</h4>
</nav>

	${msg}
</body>
</html>