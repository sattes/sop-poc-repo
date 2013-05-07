package com.sp3.mvc.models;

import java.io.Serializable;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

public class Login implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@NotEmpty(message = "Username must not be blank.")
	@Size(min = 1, max = 10, message = "Username must between 1 to 10")
	private String userName;
	
	@NotEmpty(message = "Password must not be blank.")
	@Size(min = 1, max = 10, message = "Password must between 1 to 10")
    private String password;
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
    
}
