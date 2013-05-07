<%-- <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>SalesOrderProcessingSystem-Login</title>
<link rel="stylesheet" type="text/css" href="/Spring3MVCDemo/css/sopstyles.css"/>
<!-- <script src="http://code.jquery.com/jquery-latest.js"></script>
	<script type="text/javascript">
         $(document).ready(function () {
        	 $('#mainheader').css({"color":"green"});
        	 $('#mainheader').css({"background-color":"yellow"});
        	 $('#mainheader').css({"height":"25px"});
        	 $('#mainheader').css({"width":"400px"});
        	 $('#mainheader').css({"margin-left":"30px"});
       });
	</script> -->
<script language="javascript">
	function adjust(obj) {
		if(obj.name == "Login") {
			document.loginpage.action = "/Spring3MVCDemo/login.htm";
			return true;
		}
		if(obj.name == "Back") {
			document.loginpage.action = "/Spring3MVCDemo/backtoindex.htm";
			return true;
		}
	}
</script>
</head>
<body>
	<form:form modelAttribute="login" action="" name="loginpage">
		<table class="mainheader">
			<thead>
				<tr>
					<th width="100%" align="center">SalesOrderProcessingSystem - Login</th>
				</tr>
			</thead>
		</table>
		<fieldset style="width: 375px;">
		<legend>Login</legend>
		<table>
			<tbody>
				<tr>
					<td width="50%" align="center">User Name</td>
					<td width="50%" align="center"><form:input path="userName" /><font color="red"> <form:errors
								path="userName" /></font><br /></td>
				</tr>
				<tr>
					<td width="50%" align="center">Password</td>
					<td width="50%" align="center"><form:password path="password" /><font color="red">
							<form:errors path="password" />
					</font><br /></td>
				</tr>
				<tr></tr>
				</tbody>
			</table>
			<table align="center">
				<tr>
					<td>
						<input type="submit" class="button" value="Back" name="Back" onClick="adjust(this);"/>&nbsp;
						<input type="submit" class="button" value="Login" name="Login" onClick="adjust(this);"/>
					</td>
				</tr>
		</table>
		</fieldset>
		<!-- <img src="\images\sqlfire.jpg" alt="sqlfimage" width="200" height="100" id="sqlfimage" mapfile="\images\sqlfire.jpg"/>  -->
	</form:form>
</body>
</html> --%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>Login Page</title>
<link rel="stylesheet" type="text/css" href="/Spring3MVCDemo/css/sopstyles.css"/>
</head>
<body onload="document.loginform.username.focus();">
	<form name="loginform" action="<c:url value='j_spring_security_check' />"
		method="POST">
 		<table class="mainheader">
			<thead>
				<tr>
					<th width="100%" align="center">SalesOrderProcessingSystem - Login</th>
				</tr>
			</thead>
		</table>
		<c:if test="${not empty param.login_error}">
			<div class="errorblock">
				Your login attempt was not successful, try again.<br /> Caused : ${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}
			</div>
		</c:if>
		<fieldset style="width: 375px;">
		<legend>Login</legend>
			<table>
				<tr>
					<td>User:</td>
					<td><input type="text" name="username" value="">
					</td>
				</tr>
				<tr>
					<td>Password:</td>
					<td><input type="password" name="password" />
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<input name="submit" type="submit"	value="Submit" class="button"/>&nbsp;
						<input name="reset" type="reset" value="Reset" class="button"/>
					</td>
				</tr>
			</table>
		</fieldset>
	</form>
</body>
</html>