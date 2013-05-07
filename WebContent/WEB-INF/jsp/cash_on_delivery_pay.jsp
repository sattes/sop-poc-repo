<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>SalesOrderProcessingSystem - Payment Gateway</title>
</head>
<body>
	<form:form action="/Spring3MVCDemo/makepayment.htm" modelAttribute="payment" method="POST">
		
   		<table class="mainheader">
			<thead>
				<tr>
					<th>SalesOrderProcessingSystem - Cash On Delivery Payment</th>
				</tr>
			</thead>
		</table>
		
		
		<table>
			<tbody>
				<tr>
					<td>Please click on the below button to have Cash On Delivery option</td>
				</tr>
				<tr></tr>
				<tr>
					<td>
						<input type="submit" class="button" value="Make Payment"/>
					</td>
				</tr>
			</tbody>
		</table>
		
	</form:form>
</body>
</html>