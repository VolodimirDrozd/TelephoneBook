<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<body>
	<form action="/editContact?contactId=${contactId}" method="post">
		<fieldset style="width: 300px">
			<table>
				<tr>
					<td>Contact</td>
				</tr>
				<tr>
					<td>name:</td>
					<td><input type='text' name='name' value=''></td>
				</tr>
				<tr>
					<td>surname:</td>
					<td><input type='text' name='surname' /></td>
				</tr>
				<tr>
					<td>patronymic:</td>
					<td><input type='text' name='patronymic' /></td>
				</tr>
				<tr>
					<td>telephone:</td>
					<td><input type='text' name='telephone' /></td>
				</tr>
				<tr>
					<td>home telephone:</td>
					<td><input type='text' name='homeTelephone' /></td>
				</tr>
				<tr>
					<td>address:</td>
					<td><input type='text' name='address' /></td>
				</tr>
				<tr>
					<td>email:</td>
					<td><input type='text' name='email' /></td>
				<tr>
					<td colspan='2'><input name="submit" type="submit"
						value="submit"/> <input type="reset" value="Reset" /></td>
				</tr>
				
				</form>
</body>
</html>