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
<title>Update</title>
</head>
<body>
	<nav class="navbar navbar-expand-md navbar-light">
		<div>
			<a class="navbar-brand"> Profile Management Application </a>
		</div>
		<ul class="navbar-nav">
			<li><a
				href="<%=request.getContextPath()%>/ProfilesServlet/dashboard"
				class="nav-link">Back to Dashboard</a></li>
		</ul>
	</nav>
	<div class="container col-md-6">
		<div class="card">
			<div class="card-body">
				<c:if test="${Profiles != null}">
					<form action="update" method="post">
				</c:if>
				<c:if test="${Profiles == null}">
					<form action="insert" method="post">
				</c:if>
				<caption>
					<h2>
						<c:if test="${Profiles != null}">Edit User</c:if>
						<c:if test="${Profiles == null}">Add New User</c:if>
					</h2>
				</caption>
				<c:if test="${Profiles != null}">
					<input type="hidden" name="oriName" value="<c:out value='${Profiles.username}' />" />
				</c:if>
				<fieldset class="form-group">
				<label>UserName</label> <input type="text" value="<c:out value='${Profiles.username}' />" class="form-control" name="username" readonly>
				</fieldset>
				<fieldset class="form-group">
					<label>FirstName</label> <input type="text" value="<c:out value='${Profiles.firstname}' />" class="form-control" name="firstname">
				</fieldset>
				<fieldset class="form-group">
					<label>LastName</label> <input type="text"value="<c:out value='${Profiles.lastname}' />" class="form-control" name="lastname">
				</fieldset>
				<fieldset class="form-group">
					<label>Email</label> <input type="text"value="<c:out value='${Profiles.email}' />" class="form-control" name="email">
				</fieldset>
				<fieldset class="form-group">
					<label>Address</label> <input type="text" value="<c:out value='${Profiles.address}' />" class="form-control" name="address">
				</fieldset>
				<fieldset class="form-group">
					<label>Gender</label> <input type="text"value="<c:out value='${Profiles.gender}' />" class="form-control" name="gender">
				</fieldset>
				<fieldset class="form-group">
					<label>Date of Birth</label> <input type="text"value="<c:out value='${Profiles.dob}' />" class="form-control" name="dob">
				</fieldset>
				<button type="submit" class="btn btn-success">Save</button>
				</form>
			</div>
		</div>
	</div>

</body>
</html>