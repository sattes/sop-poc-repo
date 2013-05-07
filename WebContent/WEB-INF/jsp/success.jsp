<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="java.util.Enumeration"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
 
<html>
    <head>
        <title>SalesOrderProcessingSystem - RegistrationSuccess</title>
        <link rel="stylesheet" type="text/css" href="/Spring3MVCDemo/css/sopstyles.css"/>
    </head>
    <body>
    	<form:form action="/Spring3MVCDemo/backtoindex.htm">
	    	<table class="mainheader">
				<thead>
					<tr>
						<th>SalesOrderProcessingSystem - RegistrationSuccess</th>
					</tr>
				</thead>
			</table>
			<table>
				<tbody>
					<tr align="center"><td align="center">Customer registered successfully with the following details</td></tr>
					<tr>
						<td>First Name</td>
						<td><c:out value="${customer.fname}"></c:out></td>
					</tr>
					<tr>
						<td>Last Name</td>
						<td><c:out value="${customer.lname}"></c:out></td>
					</tr>
					<tr>
						<td>Gender</td>
						<td><c:out value="${customer.gender}"></c:out></td>
					</tr>
					<tr>
						<td>Address</td>
						<td><c:out value="${customer.email}"></c:out></td>
					</tr>
					<tr>
						<td>User Name</td>
						<td><c:out value="${customer.userName}"></c:out></td>
					</tr>
					<tr></tr>
					<tr align="center">
						<td align="center">
							<a href="/Spring3MVCDemo/login.htm" >Go to Login Page</a>
						</td>
					</tr>
				</tbody>
			</table>
		       
		 	<input type="submit" class="button" value="Back">
 		</form:form>
    </body>
</html>