<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<form action="/registration" method="post" >
		<fieldset style="width: 300px">
			<legend> Registration</legend>
			<table>
				<tr>
					<td>Login</td>
					<td><input type="text" name="login" required="required"/></td>
				</tr>
				<tr>
					<td>Password</td>
					<td><input type="password" name="password" required="required"/></td>
				</tr>
				<tr>
					<td>Name</td>
					<td><input type="text" name="name" required="required"/></td>
				</tr>
				<tr>
					<td>Surname</td>
					<td><input type="text" name="surname" required="required"/></td>
				</tr>
					<tr>
					<td>Patronymic</td>
					<td><input type="text" name="patronymic" required="required"/></td>
				</tr>


			</table>
			<input type="submit" value="Submit" />
			<input type="reset" value="Reset" />
		</fieldset>

	</form>
</body>
</html>