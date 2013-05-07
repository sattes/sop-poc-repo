package com.sp3.mvc.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class ErrorController {
	@RequestMapping(value="/accessdenied", method = RequestMethod.GET)
	public String accessDenied(Model model) {
		
		model.addAttribute("message", "You do not have permission to access this page!");
		return "accessdenied";
	
	}

	
	
}
