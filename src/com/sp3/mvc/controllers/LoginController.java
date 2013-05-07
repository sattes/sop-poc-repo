package com.sp3.mvc.controllers;

import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.sp3.mvc.dao.CategoryDao;
import com.sp3.mvc.dao.CustomerDao;
import com.sp3.mvc.models.CatAndProducts;
import com.sp3.mvc.models.Category;
import com.sp3.mvc.models.Customer;
import com.sp3.mvc.models.Login;

@Controller
@SessionAttributes("caps")
public class LoginController {
	
	private static Logger logger = Logger.getLogger(LoginController.class);
	
	/*@Resource(name = "myProps")
	private Properties myProps;*/
	
	@Resource(name = "custDao")
	private CustomerDao custDao;
	
	@Resource(name = "catDao")
	private CategoryDao catDao;
	
	@RequestMapping(value="/login", method = RequestMethod.GET)
	public String goToLogin(@ModelAttribute("login")Login login, Model model,HttpServletRequest request) {
		logger.debug("Inside login method...");
		logger.debug("request param = "+request.getParameter("customerlogin"));
		final String currentUser = SecurityContextHolder.getContext()
				.getAuthentication().getName();
		logger.info("WLCOME USER [ "+ currentUser+ " ]");
		if ((currentUser.equals("") || currentUser.equals(null) || currentUser
				.equalsIgnoreCase("anonymousUser"))) {
		
		return "login";
		} 
		
		else{
			model.addAttribute("message", "Welcome user:- "+ currentUser);
			logger.info("Inside Validation of session for user:- "+ currentUser);
			return "welcomePage";
		}
	}
	
	//Now this method is not called as we are using Spring Security
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public String login(@Valid Login login, BindingResult result, Model model, HttpServletRequest request) throws SQLException, ClassNotFoundException {
		logger.debug("Inside login method...");
		
		String userName = login.getUserName();
		String password = login.getPassword();
		
		logger.debug("User Name = "+userName);
		logger.debug("Password = "+password);
		
		if (result.hasErrors()) {
			return "login";
		}
		
		Customer dbLogin = null;
		try {
			dbLogin = custDao.getCustomerByUserId(userName);
		} catch (SQLException e) {
			logger.error("SQLException got from CustomerDao in getCustomerByUserId(userName)");
			throw e;
		} catch (ClassNotFoundException e) {
			logger.error("ClassNotFoundException got from CustomerDao in getCustomerByUserId(userName)");
			throw e;
		}
		logger.debug("dbLogin = "+dbLogin);
		request.getSession().setAttribute("login", dbLogin);
		
		return "customer_home";//LoginController.java
	}
	
	@RequestMapping(value="/viewproduct", method=RequestMethod.GET)
	public String viewproduct(@Valid Login login, BindingResult result, Model model, HttpServletRequest request) throws SQLException, ClassNotFoundException {
		logger.debug("Inside viewproduct method...");
		
		/*final String currentUser = SecurityContextHolder.getContext()
				.getAuthentication().getName();
		
		String userName = currentUser;
		
		logger.debug("User Name = "+userName);*/
		
		//Get all the Categories
		List<Category> categories = null;
		try {
			categories = catDao.getAllCategories();
			CatAndProducts caps = new CatAndProducts();
			caps.getCategories().addAll(categories);
			model.addAttribute("caps", caps);
		} catch (SQLException e) {
			logger.error("SQLException got from CategoryDao in getAllCategories()");
			throw e;
		} catch (ClassNotFoundException e) {
			logger.error("ClassNotFoundException got from CategoryDao in getAllCategories()");
			throw e;
		}
		
		return "viewProducts";
	}
	
	
	
	
	@RequestMapping(value="/viewmyproduct", method=RequestMethod.GET)
	public String viewmyproduct(@Valid Login login, BindingResult result, Model model, HttpServletRequest request) throws SQLException, ClassNotFoundException {
		logger.debug("Inside viewproduct method...");
		System.out.println("Inside viewmyproduct Get");
		final String currentUser = SecurityContextHolder.getContext()
				.getAuthentication().getName();
		
		
		String userName = currentUser;
		
		System.out.println("User Name = "+userName);
		
		//Get all the Categories
		List<Category> categories = null;
		try {
			categories = catDao.getAllCategories();
			CatAndProducts caps = new CatAndProducts();
			caps.getCategories().addAll(categories);
			model.addAttribute("caps", caps);
		} catch (SQLException e) {
			logger.error("SQLException got from CategoryDao in getAllCategories()");
			throw e;
		} catch (ClassNotFoundException e) {
			logger.error("ClassNotFoundException got from CategoryDao in getAllCategories()");
			throw e;
		}
		
		//request.getSession().setAttribute("categories", categories);
		
		
		model.addAttribute("user", userName);
		return "viewmyProducts";
	}
	@RequestMapping(value="/logout", method=RequestMethod.GET)
	public String logout(@ModelAttribute("login")Login login, HttpServletRequest request) {
		logger.debug("Inside logout method...");
		
		if(request.getSession() != null) {
			request.getSession().invalidate();
		}
		logger.debug("request.getSession() categories - "+request.getSession().getAttribute("categories"));
		return "welcomePage";
	}
	
	@RequestMapping(value="/gotochome", method=RequestMethod.GET)
	public String goToCustomerHome(HttpServletRequest request) throws SQLException, ClassNotFoundException {
		logger.debug("Inside goToCustomerHome method...");
		
		String userName = SecurityContextHolder.getContext()
				.getAuthentication().getName();
		Customer dbLogin = null;
		try {
			dbLogin = custDao.getCustomerByUserId(userName);
		} catch (SQLException e) {
			logger.error("SQLException got from CustomerDao in goToCustomerHome()");
			throw e;
		} catch (ClassNotFoundException e) {
			logger.error("ClassNotFoundException got from CustomerDao in goToCustomerHome()");
			throw e;
		}
		logger.debug("dbLogin = "+dbLogin);
		request.getSession().setAttribute("login", dbLogin);
		
		return "customer_home";
	}

}
