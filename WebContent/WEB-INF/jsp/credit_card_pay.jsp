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
					<th>SalesOrderProcessingSystem - Payment Gateway</th>
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
					<td>Card Number</td>
					<td><form:input path="cardInfo.cardNumber" /></td>
				</tr>
				<tr>
					<td>Name on Card</td>
					<td><form:input path="cardInfo.nameOnCard" /></td>
				</tr>
				<tr>
					<td>Expiry Date</td>
					<td>
               		<form:select path="cardInfo.expiryMonth">
                       <form:option value="01">01</form:option>
                       <form:option value="02">02</form:option>
                       <form:option value="03">03</form:option>
                       <form:option value="04">04</form:option>
                       <form:option value="05">05</form:option>
                       <form:option value="06">06</form:option>
                       <form:option value="07">07</form:option>
                       <form:option value="08">08</form:option>
                       <form:option value="09">09</form:option>
                       <form:option value="10">10</form:option>
                       <form:option value="11">11</form:option>
                       <form:option value="12">12</form:option>
                   </form:select>&nbsp;
                   <form:select path="cardInfo.expiryYear">
                       <form:option value="2013">2013</form:option>
                       <form:option value="2014">2014</form:option>
                       <form:option value="2015">2015</form:option>
                       <form:option value="2016">2016</form:option>
                       <form:option value="2017">2017</form:option>
                   </form:select>
                   </td>
				</tr>
				<tr>
					<td>CVV Number</td>
					<td><form:input path="cardInfo.cvvNumber" /></td>
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