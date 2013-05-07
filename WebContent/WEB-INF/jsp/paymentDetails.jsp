<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>SalesOrderProcessingSystem- PaymentTracking</title>
<link rel="stylesheet" type="text/css" href="./css/sopstyles.css"/>
<script type="text/javascript">
	function adjust(obj) {
		if (obj.name == "Back") {
			document.updatepayments.action = "/Spring3MVCDemo/backtopaymenttrack.htm";
			return true;
		}
		if (obj.name == "UpdatePaymentStatus") {
			document.updatepayments.action = "/Spring3MVCDemo/updatepayment.htm";
			return true;
		}
	}
</script>

</head>
<body>
<form:form modelAttribute="payment" name="updatepayments"	action="">
	<table class="mainheader">
		<thead>
			<tr>
				<th>SalesOrderProcessingSystem - Payment Tracking</th>
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
	<table id="datatable">
		<thead>
			<tr>
				<th>PaymentId</th>
				<th>OrderId</th>
				<th>TotalAmount</th>
				<th>PaymentDate</th>
				<th>Status</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td>${payment.paymentId}</td>
				<td>${payment.orderId}</td>
				<td>${payment.paymentAmount}</td>
				<td>${payment.paymentDate}</td>
				<td><form:select path="paymentStatus" itemValue="${payment.paymentStatus}">
								<form:option value="SUCCESS">SUCCESS</form:option>
								<form:option value="FAILURE">FAILURE</form:option>
								<form:option value="PENDING">PENDING</form:option>
							</form:select>
					<input type="hidden" name="payId" value="${payment.paymentId}">
							</td>
			</tr>
		</tbody>
	</table>
	<table>
		<tbody>
			<tr>
				<td>
					<input type="submit" value="Back" name="Back" onclick="adjust(this);" class="button"/>&nbsp;
					<input type="submit" value="UpdatePaymentStatus" name="UpdatePaymentStatus" onclick="adjust(this);" class="button"/>
				</td>
			</tr>
		</tbody>
	</table>
</form:form>
</body>
</html>