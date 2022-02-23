<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<title>Profile</title>
</head>
<body>
	<!-- Create a form with the action attribute to specific where to send the form-data when
the form is submitted, method attribute to specific the method used (GET, POST, PUT, DELETE,
Etc.) -->
<form action="ProfileServlet" method="post">
<div class="card text-center">
  <div class="card-header">
    <h1>Customer Information</h1>
  </div>
  <div class="card-body">
    <div class="card-text">UserName: <input type="text" name="username"></div>
    <div class="card-text">FirstName: <input type="text" name="firstname" size="20" placeholder="Enter your firstname"></div>
	<div class="card-text">LastName: <input type="text" name="lastname" size="20" placeholder="Enter your lastname"></div>
	<div class="card-text">Email: <input type="text" name="email" size="20" placeholder="Enter your email"></div>
	<div class="card-text">Address: <input type="text" name="address" size="20" placeholder="Enter your address"></div>
	Gender: <select name="gender">
			<option>Male<option>
			<option>Female<option>
			<option>Prefer not to say<option>
			</select>
	<div class="card-text">Enter your Date of Birth: <input type="date" name="dob" size="20"></div>
  </div>
  <div class="card-footer text-muted">
  <!-- Implement submit button with type = submit to perform the request when clicked -->
    <input type="submit" value="Create" />
  </div>
</div>	
</form>
</body>
</html>