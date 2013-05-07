package com.sp3.mvc.models;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.sp3.mvc.enums.OrderStatusEnum;

public class OrderReport {
	
	private String orderId;
	private String status;
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date fromDate;
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date toDate;
	
	private List<Order> orderList;
	
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Date getFromDate() {
		return fromDate;
	}
	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}
	public Date getToDate() {
		return toDate;
	}
	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}
	public List<Order> getOrderList() {
		return orderList;
	}
	public void setOrderList(List<Order> orderList) {
		this.orderList = orderList;
	}
	
}
