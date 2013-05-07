package com.sp3.mvc.models;

import java.io.Serializable;

public class Category implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private String catId;
	
	private String name;
	
	private String description;

	public String getCatId() {
		return catId;
	}

	public void setCatId(String catId) {
		this.catId = catId;
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
	
}
