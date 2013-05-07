package com.sp3.mvc.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sp3.mvc.dao.CustomerDaoSpring;
import com.sp3.mvc.models.Customer;

@Service("customerService")
public class CustomerService {

protected static Logger logger = Logger.getLogger(CustomerService.class);
	
	/*@Autowired
	CustomerDaoSpring custDao ;
	
	public Customer getCustomerByUserId(String userId) {
		return custDao.getCustomerByUserId(userId);
	}
	
	public int insertCustomer(Customer customer) {
		return custDao.insertCustomer(customer);
	}*/

}
