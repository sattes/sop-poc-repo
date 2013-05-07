package com.sp3.mvc.controllers;

import java.sql.SQLException;
import java.util.Properties;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;

import com.sop.message.transform.Payment;

@Controller
public class PaymentConsumerController {
	
	private static Logger logger = Logger.getLogger(PaymentConsumerController.class);
	
	@Resource(name="restTemplate")
	RestTemplate restTemplate;
	
	@Resource(name = "myProps")
	private Properties myProps;
	
	@RequestMapping(method = RequestMethod.GET, value="/gotoupdatepayments")
	public String goToPaymentInfo(@ModelAttribute Payment payment, HttpServletRequest request) throws SQLException, ClassNotFoundException {
		logger.debug("PaymentInfoController::goToPaymentInfo End...");
		
		logger.debug("PaymentInfoController::goToPaymentInfo End...");
		return "payment_track";
	}
	
	@RequestMapping(value="/getpayment", method=RequestMethod.POST)
	public String getPaymentDetails(@ModelAttribute Payment payment, Model model) {
		logger.debug("Inside PaymentConsumerController::getPaymentDetails method...");
		
		logger.debug("Given Payment - "+payment.getPaymentId());
		
		String url = myProps.getProperty("get.payment.resturl");
		logger.debug("get.payment.resturl - "+url);
		
		String errorMsg = null;
		String returnPage = null;
		try {
			Payment returnedPayment = (Payment) restTemplate.getForObject(url, Payment.class, payment.getPaymentId());
		    
		    logger.debug("PaymentId - "+returnedPayment.getPaymentId()+", PaymentDate - "+returnedPayment.getPaymentDate()+
		    		", PaymentAmount - "+returnedPayment.getPaymentAmount()+", Status - "+returnedPayment.getPaymentStatus());
			
		    payment.setPaymentId(returnedPayment.getPaymentId());
		    payment.setPaymentDate(returnedPayment.getPaymentDate());
		    payment.setPaymentAmount(returnedPayment.getPaymentAmount());
		    payment.setPaymentStatus(returnedPayment.getPaymentStatus());
		    returnPage = "paymentDetails";
		} catch(EmptyResultDataAccessException e) {
			errorMsg = "No Payment found for the given PaymentId "+payment.getPaymentId();
			logger.error(errorMsg);
			model.addAttribute("errorMsg", errorMsg);
			returnPage = "payment_track";
		} catch(Exception e) {
			errorMsg = "No Payment found for the given PaymentId "+payment.getPaymentId();
			logger.error(errorMsg);
			model.addAttribute("errorMsg", errorMsg);
			returnPage = "payment_track";
		}
		return returnPage;
		
	}
	
	@RequestMapping(value="/backtopaymenttrack", method=RequestMethod.POST)
	public String back(@ModelAttribute Payment payment) {
		return "payment_track"; 
	}
	
	@RequestMapping(value="/updatepayment", method=RequestMethod.POST)
	public String updatePaymentStatus(@ModelAttribute Payment payment, HttpServletRequest req) {
		logger.debug("Inside PaymentConsumerController::updatePaymentStatus method...");
		
		String paymentId = (String)req.getParameter("payId");
		payment.setPaymentId(paymentId);
		logger.debug("Given PaymentId - "+paymentId);
		logger.debug("Given PaymentStatus - "+payment.getPaymentStatus());
		logger.debug("restTemplate - "+restTemplate);
		
		return updatePaymentStatus(payment);
		
	}
	
	private String updatePaymentStatus(Payment payment) {
		logger.debug("Inside updatePaymentStatus:: "+payment.getPaymentId());
		String url = myProps.getProperty("update.payment.resturl");
		logger.debug("update.payment.resturl - "+url);
		
		Payment returnedPayment = (Payment) restTemplate.getForObject(url, Payment.class, payment.getPaymentId(), payment.getPaymentStatus());
	    
	    logger.debug("PaymentId - " + returnedPayment.getPaymentId()+", Status - "+returnedPayment.getPaymentStatus()+", Updated - "+returnedPayment.isUpdated());
		
	    payment.setUpdated(returnedPayment.isUpdated());
	    
		return "payment_track";
	}

}
