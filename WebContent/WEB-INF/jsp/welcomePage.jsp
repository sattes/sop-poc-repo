<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>SalesOrderProcessingSystem - HomePage</title>
<link rel="stylesheet" type="text/css" href="/Spring3MVCDemo/css/sopstyles.css"/>
</head>
<body>
<form:form>
	
	<table class="mainheader">
		<thead>
			<tr>
				<th><c:out value="SalesOrderProcessingSystem - HomePage"></c:out></th>
			</tr>
		</thead>
	</table>
	<marquee>SalesOrderProcessing System with in memory DataBase SQLFire</marquee>
	<table align="right">
		<tr>
			
			<td>
				<sec:authorize access="isAuthenticated()">
				    <a href="<c:url value="/j_spring_security_logout" />">Logout</a>
				</sec:authorize>
			</td>
		</tr>
	</table>
	<div style="width:100%;">
	<div style="float:left; width:20%;">
	<table>
		<tbody>
			<tr>
				<td>
					<a href="/Spring3MVCDemo/gotoregister.htm?userType=admin" >Admin Registration Page</a>
					
				</td>
			</tr>
			<tr>
				<td>
					<a href="/Spring3MVCDemo/gotoregister.htm?userType=customer">Customer Registration Page</a>
					
				</td>
			</tr>
			<tr>
				<td><a href="/Spring3MVCDemo/login.htm">Login Page</a></td>
			</tr>
		</tbody>
	</table>
	</div>
	<div style="float:right; width:80%;">
		<img src="./images/sqlfire.jpg" alt="sqlfimage" width="230" height="110" id="sqlfimage" mapfile="./images/sqlfire.jpg"/>
	</div>
	</div>
</form:form>
</body>
</html>