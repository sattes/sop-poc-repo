package com.sp3.mvc.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CatAndProducts implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private List<Category> categories = new ArrayList<Category>();
	
	private List<Product> products = new ArrayList<Product>();

	public List<Category> getCategories() {
		return categories;
	}

	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}
	
}
