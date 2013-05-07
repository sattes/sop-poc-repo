<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>SalesOrderProcessingSystem - OrderInfo</title>
	<link rel="stylesheet" type="text/css" href="/Spring3MVCDemo/css/sopstyles.css"/>
		<script language="javascript">
		function adjust(obj) {
			if(obj.name == "GoToPayment") {
				document.orderinfopage.action = "/Spring3MVCDemo/gotopayment.htm";
				return true;
			}
			if(obj.name == "Back") {
				document.orderinfopage.action = "/Spring3MVCDemo/backtoviewcart.htm";
				return true;
			}
		}
	</script>
</head>
<body>
	<form:form action="" modelAttribute="order" name="orderinfopage">
   		<table class="mainheader">
			<thead>
				<tr>
					<th>SalesOrderProcessingSystem - OrderInfo</th>
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
			<tr></tr>
			<tr>
				<td>
					<c:out value="Order "></c:out>
					<c:out value="${order.orderId}"></c:out>
					<c:out value=" will be processed soon"></c:out>
				</td>
			</tr>
			<tr></tr>
			<tr>
				<td>
					<!-- <input type="submit" class="button" value="Back" name="Back" onClick="adjust(this);"/> -->
					<input type="submit" class="button" value="Go To Payment" name="GoToPayment" onClick="adjust(this);"/>
				</td>
			</tr>
		</table>
	</form:form>
</body>
</html>