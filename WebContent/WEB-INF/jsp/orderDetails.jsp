<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>SalesOrderProcessingSystem-OrderTracking</title>
<link rel="stylesheet" type="text/css" href="./css/sopstyles.css"/>
<script type="text/javascript">
	function callUpdate(obj) {
		if (obj.name == "UpdateOrderStatus") {
			document.updateorders.action = "/Spring3MVCDemo/updateorder.htm";
			return true;
		}
		if (obj.name == "Back") {
			document.updateorders.action = "/Spring3MVCDemo/backtoorder.htm";
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
					<th>SalesOrderProcessingSystem - Order Tracking</th>
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
					<th>OrderId</th>
					<th>UserId</th>
					<th>AddressId</th>
					<th>OrderDate</th>
					<th>TotalPrice</th>
					<th>Status</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td>${order.orderId}</td>
					<td>${order.userId}</td>
					<td>${order.addressId}</td>
					<td>${order.orderDate}</td>
					<td>${order.totalPrice}</td>
					<td><form:select path="status" itemValue="${order.status}">
							<form:option value="ORDERED">ORDERED</form:option>
							<form:option value="PAID">PAID</form:option>
							<form:option value="DELIVERED">DELIVERED</form:option>
						</form:select>
						<input type="hidden" name="ordId" value="${order.orderId}">
					</td>
				</tr>
			</tbody>
		</table>	
		<table >
			<tbody>	
				<tr>
					<td>
						<input type="submit" value="Back" name="Back"class="button" onclick="callUpdate(this);"/>&nbsp;
						<input type="submit" value="UpdateOrderStatus" name="UpdateOrderStatus" onclick="callUpdate(this);" class="button"/>
					</td>
				</tr>
			</tbody>
		</table>
	</form:form>
</body>
</html>