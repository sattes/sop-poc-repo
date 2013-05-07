<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>SalesOrderProcessingSystem</title>
</head>
<body>
	<form:form modelAttribute="order"
		action="/Spring3MVCDemo/updateorder.htm">
		<table>
			<thead>
				<tr>
					<th>SalesOrderProcessingSystem - Order Details</th>
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
				<tr>
					<td>Order ID</td>
					<td><form:input path="orderId" /><font color="red"> <form:errors
								path="orderId" /></font></td>
				</tr>
				<tr>
					<td>Order Status</td>
					<td><form:select path="status">
							<form:option value="ORDERED">ORDERED</form:option>
							<form:option value="PAID">PAID</form:option>
							<form:option value="DELIVERED">DELIVERED</form:option>
						</form:select><font color="red"> <form:errors
								path="status" /></font></td>
				</tr>
				<tr></tr>
				<tr align="center">
					<td align="center"><input type="submit" value="UpdateOrderStatus" name="orderparam"/>&nbsp;</td>
				</tr>
			</tbody>
		</table>
		<c:if test="${order.updated}">
			<c:out value="Status updated successfully for order with id "/><c:out value="${order.orderId}"></c:out>
		</c:if>
	</form:form>
</body>
</html>