package com.sp3.mvc.models;

import java.io.Serializable;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import com.sp3.mvc.enums.CustomerStatusEnum;
import com.sp3.mvc.enums.CustomerTypeEnum;

public class Customer implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@NotEmpty(message = "User Name must not be blank.")
	private String userName;
	@NotEmpty(message = "Password must not be blank.")
    private String password;
    @NotEmpty(message = "First Name must not be blank.")
    private String fname;
    @NotEmpty(message = "Last Name must not be blank.")
    private String lname;
    @NotEmpty(message = "Gender must not be blank.")
    private String gender;
    /*@NotEmpty(message = "Country must not be blank.")
    private String country;*/
   
    //private String cb;
    private String typeOfAddress;
    
    @NotEmpty(message = "email must not be blank.")
    @Email(message = "email must be valid.")
    private String email;
    
    private Integer enabled;
    private Integer roleId;
    
    private CustomerTypeEnum custType;
    private CustomerStatusEnum status;
    
    private Address customerAddress;
    
    private CustomerRole role;
    
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
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public String getLname() {
		return lname;
	}
	public void setLname(String lname) {
		this.lname = lname;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	/*public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}*/
	
	/*public String getCb() {
		return cb;
	}
	public void setCb(String cb) {
		this.cb = cb;
	}*/
	public String getTypeOfAddress() {
		return typeOfAddress;
	}
	public void setTypeOfAddress(String typeOfAddress) {
		this.typeOfAddress = typeOfAddress;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public CustomerTypeEnum getCustType() {
		return custType;
	}
	public void setCustType(CustomerTypeEnum custType) {
		this.custType = custType;
	}
	public CustomerStatusEnum getStatus() {
		return status;
	}
	public void setStatus(CustomerStatusEnum status) {
		this.status = status;
	}
	public Address getCustomerAddress() {
		return customerAddress;
	}
	public void setCustomerAddress(Address customerAddress) {
		this.customerAddress = customerAddress;
	}
	public Integer getEnabled() {
		return enabled;
	}
	public void setEnabled(Integer enabled) {
		this.enabled = enabled;
	}
	public CustomerRole getRole() {
		return role;
	}
	public void setRole(CustomerRole role) {
		this.role = role;
	}
	public Integer getRoleId() {
		return roleId;
	}
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
	
}
