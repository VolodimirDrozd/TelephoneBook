<%@ page import="java.util.ArrayList"%>
<%@ page import="com.lardi.entity.Contact"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page session="true"%>
<html>
<body>
	Telephone book:
	
	<c:forEach items="${contacts}" var="contact" varStatus="contactIndex">
		<br>
	name: ${contact.name},
	surname: ${contact.surname},
	telephone: ${contact.telephone},
	homeTelephone: ${contact.homeTelephone},
	address: ${contact.address},
	email: ${contact.email}
	contactId : ${contact.contactId}

		<td><a href="/editContact?contactId=${contact.contactId}">edit</a>
		</td><a href="/deleteContact?contactId=${contact.contactId}">delete</a>

	</c:forEach>
	
	<p> <a href="/addContact">create contact<spring:message code="create" /></a>
	<p>	<a href="/logout">logout</a><spring:messagecode ="buttonLogout"/>		</a>	</p>
	
	<p>
</body>
</html>
