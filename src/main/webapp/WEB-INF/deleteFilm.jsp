<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Delete Film</title>
</head>
<body>
	<h1>Delete Film</h1>

	<c:if test="${!empty film}">
		<p>Are you sure you want to delete this film?</p>
		<p>
			<strong>Title:</strong> ${film.title}
		</p>
		<p>
			<strong>Description:</strong> ${film.description}
		</p>

		<form action="deleteFilm.do" method="post">
			<input type="hidden" name="id" value="${film.id}">
			<button type="submit">Yes, Delete</button>
		</form>
		
		<a href="index.do">Cancel</a>
	</c:if>

	<c:if test="${empty film}">
		<h1>Film Not Found</h1>
		<p>No film found for deletion.</p>
		<a href="index.do">Back to Home Page</a>
	</c:if>
</body>
</html>