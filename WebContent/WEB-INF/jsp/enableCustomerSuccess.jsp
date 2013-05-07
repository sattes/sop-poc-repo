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
	<title>SalesOrderProcessingSystem - Enable Customers Succes</title>
	<link rel="stylesheet" type="text/css" href="/Spring3MVCDemo/css/sopstyles.css"/>
</head>
<body>
	<form:form action="/Spring3MVCDemo/logout.htm" method="GET" name="enablecustomers">
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
					<th>SalesOrderProcessingSystem - Enable Customers Succes</th>
				</tr>
			</thead>
		</table>
		<c:out value="Enabled Selected Customers Successfully."></c:out><br/>
		<input type="submit" class="button" value="Close"/>
	</form:form>
</body>
</html>