<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<form action="ProductServlet" method="post">
		Product Name: <input type="text" name="product"> 
		Price(USD$): <input type="text" name="price"> 
		Description: <input type="text" name="description"> 
		Status: <select name="status">
			<option>Out of Stock</option>
			<option>Sale</option>
			<option>Running Out</option>
		</select> <input type="submit" value="Call Servlet" />
	</form>
</body>
</html>