package com.sp3.mvc.controllers;

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

import com.sop.message.transform.Order;

@Controller
public class OrderConsumerController {
	
	private static Logger logger = Logger.getLogger(OrderConsumerController.class);
	
	@Resource(name="restTemplate")
	RestTemplate restTemplate;
	
	@Resource(name = "myProps")
	private Properties myProps;
	
	@RequestMapping(value="/order", method = RequestMethod.GET)
	public String goToOrder(@ModelAttribute("order")Order order, Model model) {
		logger.debug("Inside SalesOrderConsumerController::goToOrder method...");
		return "order";
	}
	
	@RequestMapping(value="/order", method=RequestMethod.POST)
	public String getOrderDetails(@ModelAttribute Order order, Model model) {
		logger.debug("Inside OrderConsumerController::getOrderDetails method...");
		
		logger.debug("Given OrderId - "+order.getOrderId());
		
		String url = myProps.getProperty("get.order.resturl");
		logger.debug("get.order.resturl - "+url);
		String errorMsg = null;
		String returnPage = null;
		try {
			Order returnedOrder = (Order) restTemplate.getForObject(url, Order.class, order.getOrderId());
		    
		    logger.debug("OrderId - " + returnedOrder.getOrderId()+", Status - "+returnedOrder.getStatus()+", UserID - "+returnedOrder.getUserId()+
		    		", AddressId - "+returnedOrder.getAddressId()+", TotalPrice - "+returnedOrder.getTotalPrice()+", OrderDate - "+returnedOrder.getOrderDate());
			
		    order.setAddressId(returnedOrder.getAddressId());
		    order.setOrderDate(returnedOrder.getOrderDate());
		    order.setStatus(returnedOrder.getStatus());
		    order.setTotalPrice(returnedOrder.getTotalPrice());
		    order.setUserId(returnedOrder.getUserId());
		    returnPage = "orderDetails";
		} catch(EmptyResultDataAccessException e) {
			errorMsg = "No Order found for the given orderId "+order.getOrderId();
			logger.error(errorMsg);
			model.addAttribute("errorMsg", errorMsg);
			returnPage = "order";
		} catch(Exception e) {
			errorMsg = "No Order found for the given orderId "+order.getOrderId();
			logger.error(errorMsg);
			model.addAttribute("errorMsg", errorMsg);
			returnPage = "order";
		}
		
		return returnPage;
		
	}
	
	@RequestMapping(value="/updateorder", method = RequestMethod.GET)
	public String goToUpdateOrder(@ModelAttribute("order")Order order, Model model) {
		logger.debug("Inside SalesOrderConsumerController::goToUpdateOrder method...");
		return "updateOrder";
	}
	
	@RequestMapping(value="/updateorder", method=RequestMethod.POST)
	public String updateOrderStatus(@ModelAttribute Order order, HttpServletRequest req) {
		logger.debug("Inside SalesOrderConsumerController::updateOrderStatus method...");
		String orderId = (String)req.getParameter("ordId");
		order.setOrderId(orderId);
		logger.debug("Given OrderId - "+orderId);
		logger.debug("Given OrderStatus - "+order.getStatus());
		logger.debug("restTemplate - "+restTemplate);
		
		return updateOrderStatus(order);
		
	}
	
	@RequestMapping(value="/backtoorder", method=RequestMethod.POST)
	public String back(@ModelAttribute Order order) {
		return "order"; 
	}
	
	@RequestMapping(value="/backtohome", method=RequestMethod.POST)
	public String close() {
		return "customer_home"; 
	}
	
	private String updateOrderStatus(Order order) {
		String url = myProps.getProperty("update.order.resturl");
		logger.debug("update.order.resturl - "+url);
	    Order returnedOrder = (Order) restTemplate.getForObject(url, Order.class, order.getOrderId(), order.getStatus());
	    
	    logger.debug("OrderId - " + returnedOrder.getOrderId()+", Status - "+returnedOrder.getStatus()+", Updated - "+returnedOrder.isUpdated());
		
	    order.setUpdated(returnedOrder.isUpdated());
	    
		return "order";
	}

}
