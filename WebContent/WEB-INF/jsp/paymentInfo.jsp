<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>SalesOrderProcessingSystem - PaymentInfo</title>
	<link rel="stylesheet" type="text/css" href="/Spring3MVCDemo/css/sopstyles.css"/>
</head>
<body>
	<form:form action="/Spring3MVCDemo/backtohome.htm" modelAttribute="payment">
   		<table class="mainheader">
			<thead>
				<tr>
					<th>SalesOrderProcessingSystem - PaymentInfo</th>
				</tr>
			</thead>
		</table>
		<table>
			<tr>
				<td align="left" width="10%">Welcome <sec:authentication property="principal.username"/>.</td>
				<td align="right" width="90%">
  						<a href="<c:url value="/j_spring_security_logout" />" >Logout</a>
  					</td>
			</tr>
		</table>
		<table>
			<tbody>
				<tr></tr>
				<tr>
					<td>
						<c:out value="PaymentId "></c:out>
					</td>
					<td>
						<c:out value="${payment.paymentId}"></c:out>
					</td>
				</tr>
				<tr>
					<td>
						<c:out value="Payment Amount"></c:out>
					</td>
					<td>
						<c:out value="${payment.paymentAmount}"></c:out>
					</td>
				</tr>
				<tr>
					<td>
						<c:out value="Payment Date"></c:out>
					</td>
					<td>
						<c:out value="${payment.paymentDate}"></c:out>
					</td>
				</tr>
				<tr></tr>
				<tr>
					<td align="center"><input type="submit" class="button" value="Close" /></td>
				</tr>
			</tbody>
		</table>
		
	</form:form>
</body>
</html>