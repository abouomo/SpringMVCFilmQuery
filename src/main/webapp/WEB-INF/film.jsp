<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="jakarta.tags.core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Film</title>
</head>
<body>
	
	<c:if test="${!empty film}">
        <h1>Film Details</h1>
        <p><strong>Title:</strong> ${film.title}</p>
        <p><strong>Description:</strong> ${film.description}</p>
        <a href="deleteFilmForm.do?id=${film.id}">Delete this Film</a>
        <a href="updateFilmForm.do?id=${film.id}">Update this Film</a>  
    </c:if>
    
    <c:if test="${empty film}">
        <h1>Film Not Found</h1>
        <p>${message}</p>
    </c:if>
    <br>
    <br>
    <a href="index.do">Back to Home Page</a>
     
</body>
</html>