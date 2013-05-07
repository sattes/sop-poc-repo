package com.sp3.mvc.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.sp3.mvc.dao.CustomerDao;
import com.sp3.mvc.models.Customer;

@Service("registrationService")
public class RegistrationService {
	
	protected static Logger logger = Logger.getLogger(RegistrationService.class);
	
	/*@Autowired
	CustomerDao custDao ;
	
	public JdbcTemplate getCustomerDetails() {
		//CustomerDao custDao = new CustomerDao();
		JdbcTemplate str = custDao.getJdbcTemp();
		
		return str;
	}
	
	public void registerCustomer(Customer customer) {
		
	}*/

}
