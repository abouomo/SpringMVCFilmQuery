<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>Add a New Film</h1>

	<c:if test="${!empty message}">
		<p>${message}</p>
	</c:if>

	<form action="addFilm.do" method="post">
		<label for="title">Title:</label> <input type="text" id="title"
			name="title" required><br>
		<br> <label for="description">Description:</label><br>
		<textarea id="description" name="description" rows="4" cols="50"
			required></textarea>
		<br>

		<button type="submit">Add Film</button>
	</form>
	<br>
	<a href="index.do">Back to Home Page</a>
</body>
</html>