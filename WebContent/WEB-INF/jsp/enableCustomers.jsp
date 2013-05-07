<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>SalesOrderProcessingSystem - Enable Customers</title>
	<link rel="stylesheet" type="text/css" href="/Spring3MVCDemo/css/sopstyles.css"/>
	<script language="javascript">
		function adjust(obj) {
			if(obj.name == "B1") {
				document.enableCustomers.action = "/Spring3MVCDemo/enableCustomers.htm";
				return true;
			}
			if(obj.name == "Close") {
				document.enableCustomers.action = "/Spring3MVCDemo/index.htm";
				return true;
			}
			if(obj.name == "Back") {
				document.enableCustomers.action = "/Spring3MVCDemo/index.htm";
				return true;
			}
		}
	</script>
</head>
<body>
	<form:form action="" method="POST" name="enableCustomers">
		<table align="right">
   			<tr align="right">
   				<td align="right">
   					<c:if test="${login != null}">
   						<a href="/Spring3MVCDemo/logout.htm" >Logout</a>
   					</c:if>
   				</td>
   			</tr>
   		</table>
   		<table class="mainheader">
			<thead>
				<tr>
					<th>SalesOrderProcessingSystem -  Enable Customers</th>
				</tr>
			</thead>
		</table>
		<c:if test="${fn:length(disabledCustomers) > 0}">
			<table>
				<tr>
					<th>Customer Id</th><th>FirstName</th><th>LastName</th><th>Email</th><th>Enable</th>
				</tr>
				<c:forEach var="cust" items="${disabledCustomers}" varStatus="status">
					<tr>
						<td>${cust.userName}</td>
				      <td>${cust.fname}</td>
				      <td>${cust.lname}</td>
				      <td>${cust.email}</td>
				      <td><input type = "checkbox" name = "userIds" value = "${cust.userName}"/></td>
				      
				</c:forEach>
				<tr></tr>
				<tr>
					<td><input type="submit" class="button" value="Back" name="Back" onClick="adjust(this);"/>&nbsp;
					<input type="submit" class="button" value="EnableCustomers" name="B1" onClick="adjust(this);"/></td>
				</tr>  
			</table>
		</c:if>
		<c:if test="${fn:length(disabledCustomers) == 0}">
			<c:out value="There are no disable customers"></c:out><br/>
			<input type="submit" class="button" value="Close" name="Close" onClick="adjust(this);"/>
		</c:if>
	</form:form>
</body>
</html>