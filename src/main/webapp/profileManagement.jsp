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
<title>Insert title here</title>
</head>
<body>
	<div class="row">
		<div class="container">
			<h3 class="text-center">List of Profiles Information</h3>
			<hr>
			<div class="container text-left">
				<!-- Add new user button redirects to the register.jsp page -->
				<a href="<%=request.getContextPath()%>/profile.jsp"
					class="btn btnsuccess">Add in your Profile information</a>
			</div>
			<br>
			<!-- Create a table to list out all current users information -->
			<table class="table">
				<thead>
					<tr>
						<th>UserName</th>
						<th>FirstName</th>
						<th>LastName</th>
						<th>Email</th>
						<th>Address</th>
						<th>Gender</th>
						<th>DOB</th>
						<th>Actions</th>
					</tr>
				</thead>
				<!-- Pass in the list of users receive via the Servlet’s response to a loop-->
				<tbody>
					<c:forEach var="Profiles" items="${listProfiles}">
						<!-- For each user in the database, display their information accordingly -->
						<tr>
							<td><c:out value="${Profiles.username}" /></td>
							<td><c:out value="${Profiles.firstname}" /></td>
							<td><c:out value="${Profiles.lastname}" /></td>
							<td><c:out value="${Profiles.email}" /></td>
							<td><c:out value="${Profiles.address}" /></td>
							<td><c:out value="${Profiles.gender}" /></td>
							<td><c:out value="${Profiles.dob}" /></td>
							<!-- For each user in the database, Edit/Delete buttons which invokes the edit/delete functions -->
							<td>
							<a href="edit?username=<c:out value='${Profiles.username}'/>">Edit</a>
							&nbsp;&nbsp;&nbsp;&nbsp; 
							<a href="delete?username=<c:out value='${Profiles.username}' />">Delete</a>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
</body>
</html>