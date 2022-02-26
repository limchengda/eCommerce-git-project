<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">

<meta charset="ISO-8859-1">
<title>Product Management</title>
</head>
<body>
	<div class="row">
		<div class="container">
			<h3 class="text-center">List of Products</h3>
			<hr>
			<div class="container text-left">
				<!-- Add new user button redirects to the register.jsp page -->
				<a href="<%=request.getContextPath()%>/product.jsp"
					class="btn btnsuccess">Add New Product</a>
			</div>
			<br>
			<!-- Create a table to list out all current users information -->
			<table class="table">
				<thead>
					<tr>
						<th>Product</th>
						<th>Price</th>
						<th>Description</th>
						<th>Status</th>
						<th>Actions</th>
					</tr>
				</thead>
				<!-- Pass in the list of users receive via the Servlets response to a loop-->
				<tbody>
					<c:forEach var="Products" items="${listProducts}">
						<!-- For each user in the database, display their information accordingly -->
						<tr>
							<td><c:out value="${Products.product}" /></td>
							<td><c:out value="${Products.price}" /></td>
							<td><c:out value="${Products.description}" /></td>
							<td><c:out value="${Products.status}" /></td>
							<!-- For each user in the database, Edit/Delete buttons which invokes the edit/delete functions -->
							<td><a
								href="edit?product=<c:out value='${Products.product}'
								/>">Edit</a>
								&nbsp;&nbsp;&nbsp;&nbsp; 
								<a href="delete?product=<c:out 
								value='${Products.product}' />">Delete</a></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
</body>
</html>