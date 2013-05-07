<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@page import="java.util.Enumeration"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>SalesOrderProcessingSystem  - Order Details</title>
<link rel="stylesheet" type="text/css" href="/Spring3MVCDemo/css/sopstyles.css"/>
	<script language="javascript">
		function adjust(obj) {
			if(obj.name == "GetOrderDetails") {
				document.updateorders.action = "/Spring3MVCDemo/order.htm";
				return true;
			}
			if(obj.name == "Close") {
				document.updateorders.action = "/Spring3MVCDemo/backtohome.htm";
				return true;
			}
		}
	</script>
</head>
<body>
	<form:form modelAttribute="order" name="updateorders" action="">
		<table class="mainheader">
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
		<c:if test="${errorMsg != null}">
			<div class="errorblock">
				<c:out value="${errorMsg}"/>
			</div>
		</c:if>
		<table>
			<tbody>
				<tr>
					<td>Order ID</td>
					<td><form:input path="orderId" /><font color="red"> <form:errors
								path="orderId" /></font></td>
				</tr>
				<tr></tr>
				<tr align="center">
					<td align="center">
						<input class="button" type="submit" value="Close" name="Close" onClick="adjust(this);"/>&nbsp;
						<input class="button" type="submit" value="GetOrderDetails" name="GetOrderDetails" onClick="adjust(this);"/>
					</td>
				</tr>
			</tbody>
		</table>
		
	</form:form>
</body>
</html>