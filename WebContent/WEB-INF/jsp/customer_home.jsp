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
        <title>SalesOrderProcessingSystem - CustomerHomePage</title>
        <link rel="stylesheet" type="text/css" href="/Spring3MVCDemo/css/sopstyles.css"/>
    </head>
    <body>
    	<form:form action="/Spring3MVCDemo/gotocart.htm" modelAttribute="caps">
	    	<table class="mainheader">
				<thead>
					<tr>
						<th>SalesOrderProcessingSystem - CustomerHomePage</th>
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
			<table>
				<tr>
					<td>
						<sec:authorize access="hasRole('ROLE_USER')">
							<a href="/Spring3MVCDemo/viewproduct.htm" >View Products</a>
						</sec:authorize>
					</td>
				</tr>
				<tr>
					<td>
						<sec:authorize access="hasRole('ROLE_ADMIN')">
							<a href="/Spring3MVCDemo/gotoupdatepayments.htm" >Payment Tracking Page</a>
						</sec:authorize>
					</td>
				</tr>
				<tr>
					<td>
						<sec:authorize access="hasRole('ROLE_ADMIN')">
							<a href="/Spring3MVCDemo/order.htm" >Order Tracking Page</a>
						</sec:authorize>
					</td>
				</tr>
				<tr>
					<td>
						<a href="/Spring3MVCDemo/gotoorderreports.htm" >Order Reports Page</a>
					</td>
				</tr>
				<tr>
					<td>
						<sec:authorize access="hasRole('ROLE_SYSADMIN')">
							<a href="/Spring3MVCDemo/getDisabledCustomers.htm" >Enable Customer</a>
						</sec:authorize>
					</td>
				</tr>
			</table>
			
		</form:form>
	</body>
</html>