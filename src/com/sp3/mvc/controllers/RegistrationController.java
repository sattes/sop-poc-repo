package com.sp3.mvc.controllers;

import java.io.StringWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;
import javax.validation.Valid;
import javax.xml.transform.stream.StreamResult;

import org.apache.log4j.Logger;
import org.springframework.oxm.Marshaller;
import org.springframework.oxm.Unmarshaller;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.sp3.mvc.dao.AddressDao;
import com.sp3.mvc.dao.CustomerDao;
import com.sp3.mvc.dao.CustomerRoleDao;
import com.sp3.mvc.dao.DBUtils;
import com.sp3.mvc.enums.AddressTypeEnum;
import com.sp3.mvc.enums.CustomerStatusEnum;
import com.sp3.mvc.helper.AMQPMessageHelper;
import com.sp3.mvc.helper.JaxbHelper;
import com.sp3.mvc.helper.MessageHelper;
import com.sp3.mvc.models.Address;
import com.sp3.mvc.models.Customer;
import com.sp3.mvc.models.CustomerRole;

@Controller
public class RegistrationController {
	
	private static Logger logger = Logger.getLogger(RegistrationController.class);
	
	@Resource(name = "myProps")
	private Properties myProps;
	
	@Resource(name = "custDao")
	private CustomerDao custDao;
	
	@Resource(name = "roleDao")
	private CustomerRoleDao roleDao;
	
	@Resource(name = "addrDao")
	private AddressDao addrDao;
	
	@Resource(name="jaxb2Marshaller")
	org.springframework.oxm.jaxb.Jaxb2Marshaller jaxb2Marshaller;
	
	@Resource(name="dataSource")
	DataSource dataSource;
	
	private Marshaller marshal;
    private Unmarshaller unmarshal; 
    
    private String userType;

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public Marshaller getMarshal() {
		return marshal;
	}

	public void setMarshal(Marshaller marshal) {
		this.marshal = marshal;
	}

	public Unmarshaller getUnmarshal() {
		return unmarshal;
	}

	public void setUnmarshal(Unmarshaller unmarshal) {
		this.unmarshal = unmarshal;
	}

	@RequestMapping(value="/gotoregister", method = RequestMethod.GET)
	public String goToRegisterPage(@ModelAttribute("customer")Customer customer, Model model, HttpServletRequest request,
			@RequestParam("userType") String userType ) {
		logger.debug("Inside goToIndexPage method...");
		
		logger.debug("userType = "+userType);
		this.setUserType(userType);
		
		return "register";
	}
	
	@RequestMapping(value="/register", method=RequestMethod.POST)
	public String register(@Valid Customer customer, BindingResult result, Model model) throws SQLException, ClassNotFoundException {
		logger.debug("Inside register method...");
		
		/*if (result.hasErrors()) {
			return "register";
		}*/
		
		return doRegister(customer, model);
		
	}
	
	private String doRegister(Customer customer, Model model) throws SQLException, ClassNotFoundException {
		logger.debug("Inside doRegister method...");
		logger.debug("First Name = "+customer.getFname());
		logger.debug("Last Name = "+customer.getLname());
		logger.debug("Gender = "+customer.getGender());
		logger.debug("Type of Address = "+customer.getTypeOfAddress());
		logger.debug("email = "+customer.getEmail());
		logger.debug("User Name = "+customer.getUserName());
		logger.debug("Password = "+customer.getPassword());
		logger.debug("Customer Address "+customer.getCustomerAddress().getAddress1());
		logger.debug("Customer Type = "+customer.getCustType());
		
		//logger.debug("Selected Check Box = "+registration.getCb());
		
		model.addAttribute("fname", customer.getFname());
		model.addAttribute("lname", customer.getLname());
		model.addAttribute("gender", customer.getGender());
		model.addAttribute("typeOfAddress", customer.getTypeOfAddress());
		model.addAttribute("email", customer.getEmail());
		model.addAttribute("uname", customer.getUserName());
		
		customer.setStatus(CustomerStatusEnum.ACTIVE);
		customer.setEnabled(0);
		
		Integer addrId = addrDao.getMaxAddressId();
		addrId = addrId + 1;
		
		Address customerAddr = customer.getCustomerAddress();
		customerAddr.setUserId(customer.getUserName());
		customerAddr.setAddressId(addrId);
		customerAddr.setAddressType(AddressTypeEnum.PERMANENT);
		CustomerRole gotRole = null;
		
		if(this.getUserType().equals("customer")) {
			logger.debug("ROLE_USER");
			gotRole = roleDao.getRoleByRoleName("ROLE_USER");
		}
		if(this.getUserType().equals("admin")) {
			if(!customer.getUserName().equals("sysadmin")) {
				logger.debug("ROLE_ADMIN");
				gotRole = roleDao.getRoleByRoleName("ROLE_ADMIN");
			}
			if(customer.getUserName().equals("sysadmin")) {
				logger.debug("ROLE_SYSADMIN");
				gotRole = roleDao.getRoleByRoleName("ROLE_SYSADMIN");
				customer.setEnabled(1);
			}
			
		}
		
		customer.setRoleId(gotRole.getRoleId());
		
		CustomerRole custRole = new CustomerRole();
		custRole.setRoleDesc(gotRole.getRoleDesc());
		custRole.setRoleId(gotRole.getRoleId());
		custRole.setRoleName(gotRole.getRoleName());
		customer.setRole(custRole);
		
		// Enable this code for Local Data Base - Start
		/*Connection conn = null;
		try {
			conn = dataSource.getConnection();
			logger.debug("Connection is successful");
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
			custDao.insertCustomer(conn,customer);
			addrDao.insertAddress(conn, customerAddr);
			
			conn.commit();
		} catch (SQLException e) {
			logger.error("SQLException got from CustomerDao in insertCustomer(customer)");
			conn.rollback();
			throw e;
		} catch(Exception e) {
			logger.error("Exception occured in doRegister method");
			conn.rollback();
		} finally {
			DBUtils.closeConnection(conn);
		}*/
		// Enable this code for Local Data Base - End
		
		//Enable this code for sending Address xml - Start
		com.sp3.mvc.jaxb.Address jaxbCustAddr = JaxbHelper.getJaxbAddress(customerAddr, customer);
		String textMessage = getMarshalledString(jaxbCustAddr);
		textMessage = textMessage.replaceAll("ns2:", "");
		textMessage = textMessage.replaceAll("ns3:", "");
		textMessage = textMessage.replaceAll("ns4:", "");
		textMessage = textMessage.replaceAll("ns5:", "");
		
		String exchangeName = myProps.getProperty("exchange.name");
		String qName = myProps.getProperty("registration.qname");
		String ipAddress = myProps.getProperty("ip.address");
		AMQPMessageHelper helper = new AMQPMessageHelper();
		helper.sendMessage(textMessage,exchangeName, qName,ipAddress);
		//Enable this code for sending Address xml - End
		
		logger.debug("Registration record inserted successfully...");
		
		return "success";
	}
	
	@RequestMapping(value="/backtoindex", method=RequestMethod.POST)
	public String doBack() {
		logger.debug("Inside doBack method...");
		return "welcomePage";
	}
	
	private String getMarshalledString(com.sp3.mvc.jaxb.Address jaxbAddr) {
		
		final StringWriter out = new StringWriter();
		jaxb2Marshaller.marshal(jaxbAddr, new StreamResult(out));
		String textMessage = out.toString();
		
		return textMessage;
	}

}
