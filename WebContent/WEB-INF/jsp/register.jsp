<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>SalesOrderProcessingSystem - CustomerRegistration</title>
<link rel="stylesheet" type="text/css" href="/Spring3MVCDemo/css/sopstyles.css"/>
<script language="javascript">
	function adjust(obj) {
		if(obj.name == "Register") {
			document.registerpage.action = "/Spring3MVCDemo/register.htm";
			return true;
		}
		if(obj.name == "Back") {
			document.registerpage.action = "/Spring3MVCDemo/backtoindex.htm";
			return true;
		}
	}
</script>
</head>
<body>
	<form:form method="POST" modelAttribute="customer"
		action="" name="registerpage">
		<table class="mainheader">
			<thead>
				<tr>
					<th>SalesOrderProcessingSystem - CustomerRegistration</th>
				</tr>
			</thead>
		</table>
		<fieldset>
			<legend>Customer Details</legend>
		<table>
			<tbody>
				<tr>
					<td>First Name</td>
					<td><form:input path="fname" /><font color="red"> <form:errors	path="fname" /></font></td>
				</tr>
				<tr>
					<td>Last Name</td>
					<td><form:input path="lname" /><font color="red"> <form:errors	path="lname" /></font></td>
				</tr>
				<tr>
					<td>Gender</td>
					<td><form:radiobutton path="gender" value="male" />Male <form:radiobutton
							path="gender" value="female" />Female<br /><font color="red"> <form:errors	path="gender" /></font></td>
				</tr>
				<tr>
					<td>Customer Type</td>
					<td><form:select path="custType">
							<form:option value="REGULAR">Regular</form:option>
							<form:option value="PREFERRED">Preferred</form:option>
							<form:option value="CORPORATE">Corporate</form:option>
						</form:select></td>
				</tr>
				<tr>
					<td>Email</td>
					<td><form:input path="email" /><font color="red"> <form:errors	path="email" /></font></td>
				</tr>
				<tr>
					<td>User Name</td>
					<td><form:input path="userName" /><font color="red"> <form:errors path="userName" /></font></td>
				</tr>
				<tr>
					<td>Password</td>
					<td><form:password path="password" /><font color="red">	<form:errors path="password" /></font></td>
				</tr>
			</tbody>
		</table>
		</fieldset>
		<br/>
		<fieldset>
			<legend>Customer Address</legend>
		<table>
			<tr>
				<td>Address1</td>
				<td><form:textarea path="customerAddress.address1" cssClass="tarea"/><font color="red">	<form:errors path="customerAddress.address1" /></font></td>
			</tr>
			<tr>
				<td>Address2</td>
				<td><form:textarea path="customerAddress.address2" /></td>
			</tr>
			<tr>
				<td>City</td>
				<td><form:input path="customerAddress.city" /><font color="red">	<form:errors path="customerAddress.city" /></font></td>
			</tr>
			<tr>
				<td>State</td>
				<td><form:input path="customerAddress.state" /><font color="red">	<form:errors path="customerAddress.state" /></font></td>
			</tr>
			<tr>
				<td>ZIP</td>
				<td><form:input path="customerAddress.zip" /><font color="red">	<form:errors path="customerAddress.zip" /></font></td>
			</tr>
			<tr>
				<td>Country</td>
				<td>
					<form:input path="customerAddress.country" /><font color="red">	<form:errors path="customerAddress.country" /></font>
				</td>
			</tr>
			<tr>
				<td>Phone</td>
				<td><form:input path="customerAddress.phone" /><font color="red">	<form:errors path="customerAddress.phone" /></font></td>
			</tr>
			<tr></tr>
		</table>
		<table>
			<tr>
				<td>
					<input type="submit" class="button" value="Back" name="Back" onClick="adjust(this);"/>&nbsp;
					<input type="submit" class="button" value="Register" name="Register" onClick="adjust(this);"/>
				</td>
			</tr>
		</table>
		</fieldset>
	</form:form>
</body>
</html>