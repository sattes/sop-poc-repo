package com.sp3.mvc.reports;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.emc.sqlfire.sop.domain.Order;
import com.sp3.mvc.controllers.PaymentController;
import com.sp3.mvc.dao.DBUtils;
import com.sp3.mvc.enums.OrderStatusEnum;
import com.sp3.mvc.models.OrderReport;

@Controller
public class OrderReportsController {
	
	private static Logger logger = Logger.getLogger(PaymentController.class);
	
	@RequestMapping(value="/gotoorderreports", method = RequestMethod.GET)
	public String goToOrderReports(@ModelAttribute("orderReport")OrderReport orderReport, Model model, HttpServletRequest request) throws SQLException, ClassNotFoundException {
		logger.debug("OrderReportsController::goToOrderReports Start...");
		logger.debug("session attribute = "+request.getSession().getAttribute("orderReports"));
		
		if(request.getSession().getAttribute("orderReports") != null) {
			request.getSession().removeAttribute("orderReports");
		}
		
		logger.debug("OrderReportsController::goToOrderReports End...");
		return "order_reports";
	}
	
	@RequestMapping(value="/getorders", method = RequestMethod.POST)
	public String getOrders(@ModelAttribute("orderReport")OrderReport orderReport, Model model, HttpServletRequest request) throws SQLException, ClassNotFoundException {
		logger.debug("OrderReportsController::getOrders Start...");
		
		logger.debug("OrderId = "+orderReport.getOrderId());
		logger.debug("OrderStatus = "+orderReport.getStatus());
		logger.debug("FromDate = "+orderReport.getFromDate());
		logger.debug("ToDate = "+orderReport.getToDate());
		
		Connection connection = DBUtils.getConnection();
		CallableStatement stmt = connection.prepareCall("{call SOPV2.GET_ALL_ORDERS_FOR_PERIOD(?,?,?,?)  ON TABLE SOPV2.ORDERS}");
		
		stmt.setString(1, orderReport.getOrderId());
		if(orderReport.getStatus().equals("Select")) {
			stmt.setString(2, null);
		} else {
			stmt.setString(2, orderReport.getStatus());
		}
		if(orderReport.getFromDate() == null) {
			stmt.setDate(3, null);
		} else {
			Calendar fromcal = Calendar.getInstance();
			fromcal.setTime(orderReport.getFromDate());
			stmt.setDate(3, new java.sql.Date(fromcal.getTimeInMillis())); 
		}
		if(orderReport.getToDate() == null) {
			stmt.setDate(4, null);
		} else {
			Calendar tocal = Calendar.getInstance();
			tocal.setTime(orderReport.getToDate());
			stmt.setDate(4, new java.sql.Date(tocal.getTimeInMillis())); 
		}
		
		stmt.execute();
		ResultSet rs = stmt.getResultSet();
		
		List<com.sp3.mvc.models.Order> orderReports = new ArrayList<com.sp3.mvc.models.Order>();
		while (rs. next()) {
			String colName = rs.getMetaData().getColumnName(1);
			Object obj  = rs.getObject(colName);
			List<Order> orders2 = (ArrayList<Order>)obj;
			for(Order order : orders2){
				com.sp3.mvc.models.Order ord = new com.sp3.mvc.models.Order();
				ord.setAddressId(order.getAddrId());
				ord.setOrderDate(order.getOrderDate());
				ord.setOrderId(order.getOrderId());
				ord.setStatus(OrderStatusEnum.getEnumByValue(order.getOrderStatus()));
				ord.setTotalPrice(order.getTotalPrice());
				ord.setUserId(order.getUserId());
				orderReports.add(ord);
			}
			break;
		}
		logger.debug("orders size - "+orderReports.size());
		
		request.getSession().setAttribute("orderReports", orderReports);
		logger.debug("OrderReportsController::getOrders End...");
		return "order_reports";
	}

}
