package com.sp3.mvc.models;

public class Discount { 
	
	private Integer discId;
	private String custType;
	private String discType;
	private Double discPercent;
	
	public Integer getDiscId() {
		return discId;
	}
	public void setDiscId(Integer discId) {
		this.discId = discId;
	}
	public String getCustType() {
		return custType;
	}
	public void setCustType(String custType) {
		this.custType = custType;
	}
	public String getDiscType() {
		return discType;
	}
	public void setDiscType(String discType) {
		this.discType = discType;
	}
	public Double getDiscPercent() {
		return discPercent;
	}
	public void setDiscPercent(Double discPercent) {
		this.discPercent = discPercent;
	}
	
}
