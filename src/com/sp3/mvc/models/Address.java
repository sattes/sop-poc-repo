package com.sp3.mvc.models;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotEmpty;

import com.sp3.mvc.enums.AddressTypeEnum;

public class Address implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Integer addressId;
	private String userId;
	private AddressTypeEnum addressType;
	@NotEmpty(message = "Address1 must not be blank.")
	private String address1;
	private String address2;
	@NotEmpty(message = "City must not be blank.")
	private String city;
	@NotEmpty(message = "State must not be blank.")
	private String state;
	@NotEmpty(message = "Zip must not be blank.")
	private String zip;
	@NotEmpty(message = "Country must not be blank.")
	private String country;
	@NotEmpty(message = "Phone must not be blank.")
	private String phone;
	
	public Integer getAddressId() {
		return addressId;
	}
	public void setAddressId(Integer addressId) {
		this.addressId = addressId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public AddressTypeEnum getAddressType() {
		return addressType;
	}
	public void setAddressType(AddressTypeEnum addressType) {
		this.addressType = addressType;
	}
	public String getAddress1() {
		return address1;
	}
	public void setAddress1(String address1) {
		this.address1 = address1;
	}
	public String getAddress2() {
		return address2;
	}
	public void setAddress2(String address2) {
		this.address2 = address2;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getZip() {
		return zip;
	}
	public void setZip(String zip) {
		this.zip = zip;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
}
