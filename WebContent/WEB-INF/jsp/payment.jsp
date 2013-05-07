<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>SalesOrderProcessingSystem - Payment</title>
	<link rel="stylesheet" type="text/css" href="/Spring3MVCDemo/css/sopstyles.css"/>
	<script src="http://code.jquery.com/jquery-latest.js"></script>
	<script type="text/javascript">
         $(document).ready(function () {
        	 $('#div1').hide('fast');
        	 $('#div2').hide('fast');
        	 $('#div3').hide('fast');
        	 $('#div4').hide('fast');
        	 $('#div5').hide('fast');
        	
            $('#pmcc').click(function () {
            	$('#div1').show('fast');
               $('#div2').hide('fast');
               $('#div3').hide('fast');
               $('#div4').hide('fast');
               $('#div5').hide('fast');
              
        });
        $('#pmdc').click(function () {
              $('#div1').hide('fast');
              $('#div2').show('fast');
              $('#div3').hide('fast');
              $('#div4').hide('fast');
              $('#div5').hide('fast');
         });
        $('#pmnb').click(function () {
            $('#div1').hide('fast');
            $('#div2').hide('fast');
            $('#div3').show('fast');
            $('#div4').hide('fast');
            $('#div5').hide('fast');
       });
        $('#pmecs').click(function () {
            $('#div1').hide('fast');
            $('#div2').hide('fast');
            $('#div3').hide('fast');
            $('#div4').show('fast');
            $('#div5').hide('fast');
       });
        $('#pmcod').click(function () {
            $('#div1').hide('fast');
            $('#div2').hide('fast');
            $('#div3').hide('fast');
            $('#div4').hide('fast');
            $('#div5').show('fast');
       });
       });
       
         function adjust(obj) {
     		if(obj.name == "payNow") {
     			document.paymentpage.action = "/Spring3MVCDemo/paynow.htm";
     			return true;
     		}
     	}
	</script>
</head>
<body>
	<form:form action="" modelAttribute="payment" method="POST" name="paymentpage">
   		<table class="mainheader">
			<thead>
				<tr>
					<th>SalesOrderProcessingSystem - Payment</th>
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
			<tbody>	
				<tr></tr>
				<tr>
					<td>
						<c:out value="OrderId "></c:out>
					</td>
					<td>
						<c:out value="${payment.orderId}"></c:out>
					</td>
				</tr>
				<tr>
					<td>
						<c:out value="Payment Amount"></c:out>
					</td>
					<td>
						<c:out value="${payment.paymentAmount}"></c:out>
					</td>
				</tr>
				
			</tbody>
		</table>
          <div>
          	<table>
         		<tr>
					<td>Payment Mode:</td>
					<td>
						<form:radiobutton id="pmcc" name="name_radio1" path="txnType" value="CreditCard"/>Credit Cards&nbsp;
              			<form:radiobutton id="pmdc" name="name_radio1" path="txnType" value="DebitCard"/>Debit Cards&nbsp;
              			<form:radiobutton id="pmnb" name="name_radio1" path="txnType" value="NetBanking"/>Net Banking&nbsp;
              			<form:radiobutton id="pmecs" name="name_radio1" path="txnType" value="ECS"/>ECS&nbsp;
              			<form:radiobutton id="pmcod" name="name_radio1" path="txnType" value="CashOnDelivery"/>Cash On Delivery
              		</td>
				</tr>
			</table>
          </div>
          <div id="div1"> 
	          <form:radiobutton id="div1visa" path="gatewayType" value="VISA"/>Visa&nbsp;
	          <form:radiobutton id="div1master" path="gatewayType" value="MASTERCARD"/>MasterCard&nbsp;
	          <form:radiobutton id="div1amex" path="gatewayType" value="AMEX"/>American Express<br />
	          <input type="submit" class="button" value="Pay Now" name="payNow" onClick="adjust(this);"/>
       	  </div>
          <div id="div2"> 
	          <form:radiobutton id="div2visa" path="gatewayType" value="VISA"/>Visa&nbsp;
	          <form:radiobutton id="div2master" path="gatewayType" value="MASTERCARD"/>MasterCard&nbsp;
	          <form:radiobutton id="div2amex" path="gatewayType" value="AMEX"/>American Express<br />
	          <input type="submit" class="button" value="Pay Now" name="payNow" onClick="adjust(this);"/>
          </div>
          <div id="div3"> 
	          NetBanking
	          <form:radiobutton path="selectedNetBankName" value="Axis Bank"/>Axis Bank&nbsp;
	          <form:radiobutton path="selectedNetBankName" value="Duetch Bank"/>Duetch Bank&nbsp;
	          <form:radiobutton path="selectedNetBankName" value="ICICI Bank"/>ICICI Bank<br/>
	          <form:radiobutton path="selectedNetBankName" value="SBI"/>SBI&nbsp;
	          <form:radiobutton path="selectedNetBankName" value="SBH"/>SBH&nbsp;
	          <input type="submit" class="button" value="Pay Now" name="payNow" onClick="adjust(this);"/>
          </div>
          <div id="div4"> 
	          ECS
	          <input type="submit" class="button" value="Pay Now" name="payNow" onClick="adjust(this);"/>
          </div>
          <div id="div5"> 
	          Cash On Delivery
	          <input type="submit" class="button" value="Pay Now" name="payNow" onClick="adjust(this);"/>
          </div>
	</form:form>
</body>
</html>