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
        <title>SalesOrderProcessingSystem - AdminHomePage</title>
        <link rel="stylesheet" type="text/css" href="/Spring3MVCDemo/css/sopstyles.css"/>
    </head>
    <body>
    	<form:form action="/Spring3MVCDemo/gotocart.htm" modelAttribute="caps">
	    	<table class="mainheader">
				<thead>
					<tr>
						<th>SalesOrderProcessingSystem - AdminHomePage</th>
					</tr>
				</thead>
			</table>
			<table>
				<tbody>
					<tr>
						<td align="left" width="10%">Welcome <sec:authentication property="principal.username"/>.</td>
						<td align="right" width="90%">
    						<a href="<c:url value="/j_spring_security_logout" />" >Logout</a>
    				</td>
					</tr>
				</tbody>
			</table>
			<table>
				
			</table>
		</form:form>
	</body>
</html>