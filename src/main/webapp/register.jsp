<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Sign up now! | eCmmerce  </title>
</head>
<body>

<form action="register_successful" method="post">
	Username: <input type="text" name="username">
	Password: <input type="password" name="password">
	Email: <input type="text" name="email">
	Gender: <select name="gender">
		<option>Male</option>
		<option>Female</option>
		<option>Prefer not to say</option>
	</select>
	<input type="submit" value="Sign up"/>
</form>

</body>
</html>