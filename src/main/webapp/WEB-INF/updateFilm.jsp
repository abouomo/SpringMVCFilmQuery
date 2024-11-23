<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Update Film</title>
</head>
<body>
	<h1>Update Film</h1>

	<c:if test="${!empty film}">
		<form action="updateFilm.do" method="post">
			<label for="id">Film ID (Read-Only):</label> <input type="text"
				id="id" name="id" value="${film.id}" readonly><br>
			<br> <label for="title">Title:</label> <input type="text"
				id="title" name="title" value="${film.title}" required><br>
			<br> <label for="description">Description:</label><br>
			<textarea id="description" name="description" rows="4" cols="50"
				required>${film.description}</textarea>
			<br>

			<button type="submit">Update Film</button>
		</form>
	</c:if>

	<c:if test="${empty film}">
		<p>No film found for update.</p>
	</c:if>

	<c:if test="${!empty message}">
		<p>${message}</p>
	</c:if>

	<a href="index.do">Back to Home Page</a>
</body>
</html>