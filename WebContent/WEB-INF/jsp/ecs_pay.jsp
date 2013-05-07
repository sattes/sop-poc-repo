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
					<th>SalesOrderProcessingSystem - ECS Payment</th>
				</tr>
			</thead>
		</table>
		<table>
			<thead>
				<tr>
					<td width="25%">Merchant Name</td>
					<td>RELIANCE INFO</td>
					<td width="25%">Payment Amount</td>
					<td>${payment.paymentAmount}</td>
				</tr>
			</thead>
		</table>
		<fieldset>
		<legend>Payment Information (Please enter your card details to authorise this transaction)</legend>
		<table>
			<tbody>
				<tr>
					<td>Account Holder Name</td>
					<td><form:input path="directDebit.accHolderName" /></td>
				</tr>
				<tr>
					<td>Account Number</td>
					<td><form:input path="directDebit.accNumber" /></td>
				</tr>
				<tr>
					<td>Account Type</td>
					<td><form:input path="directDebit.accType" /></td>
				</tr>
				<tr>
					<td>Bank Name</td>
					<td><form:input path="directDebit.bankName" /></td>
				</tr>
				<tr>
					<td>Branch Name</td>
					<td><form:input path="directDebit.branchName" /></td>
				</tr>
				<tr>
					<td>IFSC Code</td>
					<td><form:input path="directDebit.ifscCode" /></td>
				</tr>
				<%-- <tr>
					<td>Debt Amount</td>
					<td><form:input path="directDebit.debtAmount" /></td>
				</tr>
				<tr>
					<td>Debt Date</td>
					<td><form:input path="directDebit.debtDate" /></td>
				</tr> --%>
				<tr>
					<td>Debt Frequency</td>
					<td><form:input path="directDebit.debtFrequency" /></td>
				</tr>
				<tr></tr>
				<tr>
					<td>
						<input type="submit" class="button" value="Make Payment"/>
					</td>
				</tr>
			</tbody>
		</table>
		</fieldset>
	</form:form>
</body>
</html>