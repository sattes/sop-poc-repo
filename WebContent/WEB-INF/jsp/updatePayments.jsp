<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>SalesOrderProcessingSystem - UpdatePayments</title>
	<link rel="stylesheet" type="text/css" href="/Spring3MVCDemo/css/sopstyles.css"/>
	<script language="javascript">
		function adjust(obj) {
			if(obj.name == "B1") {
				document.updatepayments.action = "/Spring3MVCDemo/updatepayments.htm";
				return true;
			}
			if(obj.name == "Close") {
				document.updatepayments.action = "/Spring3MVCDemo/backtohome.htm";
				return true;
			}
		}
	</script>
</head>
<body>
	<form:form action="" method="POST" name="updatepayments">
   		<table class="mainheader">
			<thead>
				<tr>
					<th>SalesOrderProcessingSystem - UpdatePayments</th>
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
		<c:if test="${fn:length(paymentList) > 0}">
			<table>
				<tr>
					<th>PaymentId</th><th>OrderId</th><th>PaymentStatus</th><th>PaymentDate</th><th>PaymentAmount</th>
				</tr>
				<c:forEach var="payment" items="${paymentList}" varStatus="status">
					<tr>
						<td>${payment.paymentId}</td>
				      <td>${payment.orderId}</td>
				      <td>${payment.paymentStatus}</td>
				      <td>${payment.paymentDate}</td>
				      <td>${payment.paymentAmount}</td>
				      
				</c:forEach>
				<tr></tr>
				<tr>
					<td><input type="submit" class="button" value="Update Status" name="B1" onClick="adjust(this);"/></td>
				</tr>  
			</table>
		</c:if>
		<c:if test="${fn:length(paymentList) == 0}">
			<c:out value="There are no pending payments"></c:out><br/>
			<input type="submit" class="button" value="Close" name="Close" onClick="adjust(this);"/>
		</c:if>
	</form:form>
</body>
</html>