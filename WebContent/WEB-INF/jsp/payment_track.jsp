<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>SalesOrderProcessingSystem-PaymentDetails</title>
<link rel="stylesheet" type="text/css" href="/Spring3MVCDemo/css/sopstyles.css"/>
<script language="javascript">
	function adjust(obj) {
		if(obj.name == "GetPaymentDetails") {
			document.paymenttrack.action = "/Spring3MVCDemo/getpayment.htm";
			return true;
		}
		if(obj.name == "Close") {
			document.paymenttrack.action = "/Spring3MVCDemo/backtohome.htm";
			return true;
		}
	}
</script>
</head>
<body>
	<form:form modelAttribute="payment" name="paymenttrack"
		action="">
		<table class="mainheader">
			<thead>
				<tr>
					<th>SalesOrderProcessingSystem - Payment Details</th>
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
					<td>Payment ID</td>
					<td><form:input path="paymentId" /><font color="red"> <form:errors
								path="paymentId" /></font></td>
				</tr>
			</tbody>
		</table>
		<table>
			<tbody>
				<tr align="center">
					<td align="center">
						<input type="submit" class="button" value="Close" name="Close" onClick="adjust(this);"/>&nbsp;
						<input type="submit" class="button" value="GetPaymentDetails" name="GetPaymentDetails" onClick="adjust(this);"/>
					</td>
				</tr>
			</tbody>
		</table>
	</form:form>
</body>
</html>