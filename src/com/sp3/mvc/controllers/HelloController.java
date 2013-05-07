package com.sp3.mvc.controllers;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sp3.mvc.models.Customer;
import com.sp3.mvc.models.Login;

@Controller
//@RequestMapping("/hello")
public class HelloController {
	
	private static Logger logger = Logger.getLogger(HelloController.class);

	@RequestMapping("/index")
	public String sayHello(Model model) {
		logger.debug("Inside sayHello method...");
		model.addAttribute("message", "Welcome to SalesOrderProcessingSystem");
		return "welcomePage";
	}
	
	/*@RequestMapping(value="/gotoregister")
	public String goToRegisterPage(@ModelAttribute("registration")Registration registration, Model model) {
		logger.debug("Inside goToIndexPage method...");
		return "register";
	}
	
	@RequestMapping(value="/register", method=RequestMethod.POST)
	public String register(@ModelAttribute("registration")Registration registration, Model model) {
		logger.debug("Inside submit method...");
		
		logger.debug("First Name = "+registration.getFname());
		logger.debug("Last Name = "+registration.getLname());
		logger.debug("Gender = "+registration.getGender());
		logger.debug("Type of Address = "+registration.getTypeOfAddress());
		logger.debug("Address = "+registration.getAddr());
		logger.debug("email = "+registration.getEmail());
		logger.debug("Country = "+registration.getCountry());
		logger.debug("User Name = "+registration.getUsername());
		logger.debug("Password = "+registration.getPassword());
		
		//logger.debug("Selected Check Box = "+registration.getCb());
		
		model.addAttribute("fname", registration.getFname());
		model.addAttribute("lname", registration.getLname());
		model.addAttribute("gender", registration.getGender());
		model.addAttribute("typeOfAddress", registration.getTypeOfAddress());
		model.addAttribute("addr", registration.getAddr());
		model.addAttribute("email", registration.getEmail());
		model.addAttribute("country", registration.getCountry());
		model.addAttribute("uname", registration.getUsername());
		
		//model.addAttribute("cb", registration.getCb());
		
		return "success";
	}*/
	

	/*@RequestMapping(value="/login", method = RequestMethod.GET)
	public String goToLogin(@ModelAttribute("registration")Registration registration, Model model) {
		model.addAttribute("registration", new Registration());
		logger.debug("Inside login method...");
		return "login";
	}
	
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public String login(@Valid Login login, BindingResult result, Model model) {
		logger.debug("Inside login method...");
		
		logger.debug("User Name = "+login.getUserName());
		logger.debug("Password = "+login.getPassword());
		
		String userName = "imsubbu";
		String password = "subbu123";
		
		if (result.hasErrors()) {
			return "login";
		}
		
		if (!login.getUserName().equals(userName)
				|| !login.getPassword().equals(password)) {
			return "login";
		}
		//model.put("loginForm", login);
		
		return "viewProducts";
	}*/
		
}
