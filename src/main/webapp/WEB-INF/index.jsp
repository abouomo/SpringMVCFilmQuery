<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>SDPT Films</title>
</head>
<body>
	<h1>Welcome to the Film Query System</h1>

	<section>
		<h2>Find a Film by ID</h2>
		<form action="findById.do" method="get">
			<label for="id">Enter Film ID:</label> <input type="text" id="id"
				name="id" required> <input type="submit" value="Find Film">
		</form>
	</section>
	<hr>

	<section>
		<h2>Add a New Film</h2>
		<p>Click the link below to add a new film to the database:</p>
		<a href="addFilmForm.do">Add a New Film</a>
	</section>
	<hr>


</body>
</html>