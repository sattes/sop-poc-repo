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
        <title>SalesOrderProcessingSystem - ViewCart</title>
        <link rel="stylesheet" type="text/css" href="/Spring3MVCDemo/css/sopstyles.css"/>
        <script language="javascript">
			function adjust(obj) {
				if(obj.name == "SubmitOrder") {
					document.viewcartpage.action = "/Spring3MVCDemo/submitorder.htm";
					return true;
				}
				if(obj.name == "Back") {
					document.viewcartpage.action = "/Spring3MVCDemo/backtoproducts.htm";
					return true;
				}
			}
		</script>
    </head>
    <body>
    	<form:form action="" modelAttribute="order" method="POST" name="viewcartpage">
	    	<table class="mainheader">
				<thead>
					<tr>
						<th>SalesOrderProcessingSystem - ViewCart</th>
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
			<fieldset>
				<legend>Order Details</legend>
				<table id="datatable">
					<tr>
						<th>ProductId</th><th>Category</th><th>UnitCost</th><th>Name</th><th>Description</th><th>Quantity</th>
					</tr>
					<c:forEach var="prod" items="${sessionScope.selectedProducts}">
						<tr>
					      <td>${prod.productId}</td>
					      <td>${prod.category}</td>
					      <td>${prod.unitCost}</td>
					      <td>${prod.name}</td>
					      <td>${prod.description}</td>
					      <td>${prod.quantity}</td>
					    </tr>
					</c:forEach>
				</table>
			</fieldset>
			<br/>
			<fieldset>
				<legend>Shipping Address</legend>
				<table>
					<tr>
						<td>Address1</td>
						<td><form:textarea path="shippingAddress.address1" /></td>
					</tr>
					<tr>
						<td>Address2</td>
						<td><form:textarea path="shippingAddress.address2" /></td>
					</tr>
					<tr>
						<td>City</td>
						<td><form:input path="shippingAddress.city" /></td>
					</tr>
					<tr>
						<td>State</td>
						<td><form:input path="shippingAddress.state" /></td>
					</tr>
					<tr>
						<td>ZIP</td>
						<td><form:input path="shippingAddress.zip" /></td>
					</tr>
					<tr>
						<td>Country</td>
						<td>
							<form:input path="shippingAddress.country" />
						</td>
					</tr>
					<tr>
						<td>Phone</td>
						<td><form:input path="shippingAddress.phone" /></td>
					</tr>
				</table>
				</fieldset>
		 	<input type="submit" class="button" value="Back" name="Back" onClick="adjust(this);"/>&nbsp;
		 	<input type="submit" class="button" value="SubmitOrder" name="SubmitOrder" onClick="adjust(this);"/>
 		</form:form>
    </body>
</html>