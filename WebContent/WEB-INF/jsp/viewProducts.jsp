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
        <title>SalesOrderProcessingSystem - ViewProducts</title>
        <link rel="stylesheet" type="text/css" href="/Spring3MVCDemo/css/sopstyles.css"/>
    </head>
    <body>
    	<form:form action="/Spring3MVCDemo/gotocart.htm" modelAttribute="caps">
	    	<table class="mainheader">
				<thead>
					<tr>
						<th>SalesOrderProcessingSystem - ViewProducts</th>
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
			
		    <div style="width:100%;"> 
				<div style="float:left; width:20%;">
					<table>
						<c:forEach var="category" items="${caps.categories}">
							<tr>
						      <td><a href="/Spring3MVCDemo/getproducts.htm?category=${category.catId}">${category.name}</a></td>
						    </tr>
						</c:forEach>  
					</table>
				</div>
				<div style="float:right; width:80%; ">
					<c:if test="${fn:length(caps.products) > 0}">
					<table id="datatable">
						<tr>
							<th>Select</th><th>ProductId</th><th>Category</th><th>UnitCost</th><th>Name</th><th>Description</th><th>Quantity</th>
						</tr>
						<c:forEach var="prod" items="${caps.products}" varStatus="status">
							<tr>
								<%-- <td><form:checkbox path="${prod.selected}" value=""/></td> --%>
								<td><input type = "checkbox" name = "selectedProdIds" value = "${prod.productId}"/></td>
						      <td>${prod.productId}</td>
						      <td>${prod.category}</td>
						      <td>${prod.unitCost}</td>
						      <td>${prod.name}</td>
						      <td>${prod.description}</td>
						      <td><input name="products[${status.index}].quantity" value="${prod.quantity}"/></td>
						    </tr>
						</c:forEach>
					</table>
					<table align="center">
						<tr></tr>
						<tr>
							<td><input type="submit" class="button" value="Add To Cart" /></td>
						</tr> 
					</table>
					</c:if>
				</div>
			</div>
		 	
 		</form:form>
    </body>
</html>