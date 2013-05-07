package com.sp3.mvc.models;

import java.io.Serializable;

import org.apache.log4j.Logger;

public class Product implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private String productId;
	private String category;
	private Double unitCost;
	private String name;
	private String description;
	private Integer quantity;
	
	private Boolean selected = false;
	
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public Double getUnitCost() {
		return unitCost;
	}
	public void setUnitCost(Double unitCost) {
		this.unitCost = unitCost;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Boolean getSelected() {
		return selected;
	}
	public void setSelected(Boolean selected) {
		this.selected = selected;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public int hashCode() {
		return productId.hashCode();
	}
	
	public boolean equals(Object obj) {
		Product prod = (Product)obj;
		if(this.getProductId().equals(prod.getProductId()) && this.getCategory().equals(prod.getCategory())) {
			return true;
		} else {
			return false;
		}
	}
	
}
