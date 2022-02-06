<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<title>User Management Application</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">

<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<nav class="navbar navbar-expand-md navbar-light">
		<div>
			<a class="navbar-brand"> Product Management Application </a>
		</div>
		<ul class="navbar-nav">
			<li><a
				href="<%=request.getContextPath()%>/ProductsServlet/dashboard"
				class="nav-link">Back to Dashboard</a></li>
		</ul>
	</nav>
	<div class="container col-md-6">
		<div class="card">
			<div class="card-body">
				<c:if test="${Products != null}">
					<form action="update" method="post">
				</c:if>
				<c:if test="${Products == null}">
					<form action="insert" method="post">
				</c:if>
				<caption>
					<h2>
						<c:if test="${Products != null}">Edit Product</c:if>
						<c:if test="${Products == null}">Add New Product</c:if>
					</h2>
				</caption>
				<c:if test="${Products != null}">
					<input type="hidden" name="oriName"
						value="<c:out
value='${Products.product}' />" />
				</c:if>
				<fieldset class="form-group">
					<label>Product</label> <input type="text"
						value="<c:out
value='${Products.product}' />"
						class="form-control" name="product" required="required">
				</fieldset>
				<fieldset class="form-group">
					<label>Price</label> <input type="text"
						value="<c:out
value='${Products.price}' />" class="form-control"
						name="price">
				</fieldset>
				<fieldset class="form-group">
					<label>Description</label> <input type="text"
						value="<c:out
value='${Products.description}' />"
						class="form-control" name="description">
				</fieldset>
				<fieldset class="form-group">
					<label>Status</label> 
					<select name="status">
						<option>Out of Stock</option>
						<option>Sale</option>
						<option>Running Out</option>
					</select>
				</fieldset>
				<button type="submit" class="btn btn-success">Save</button>
				</form>
			</div>
		</div>
	</div>
</body>
</html>