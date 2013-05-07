package com.sp3.mvc.controllers;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.sp3.mvc.dao.ProductDao;
import com.sp3.mvc.models.CatAndProducts;
import com.sp3.mvc.models.Order;
import com.sp3.mvc.models.Product;

@Controller
@SessionAttributes("caps")
public class CategoryAndProductsController {
	
	private static Logger logger = Logger.getLogger(CategoryAndProductsController.class);
	
	/*@Resource(name = "myProps")
	private Properties myProps;*/
	
	@Resource(name = "prodDao")
	private ProductDao prodDao;
	
	@RequestMapping(value="/getproducts", method = RequestMethod.GET)
	public String getProductsByCategory(@ModelAttribute("caps")CatAndProducts caps, Model model, @RequestParam String category, HttpServletRequest request) throws SQLException, ClassNotFoundException {
		logger.debug("Inside getProductsByCategory method...");
		
		logger.debug("RequestParameter - "+category);
		logger.debug("caps - "+caps);
		
		List<Product> products = null;
		
		try {
			products = prodDao.getProductsByCatId(category);
			//caps.getProducts().addAll(products);
			caps.setProducts(products);
		} catch (SQLException e) {
			logger.error("SQLException got from ProductDao in getProductsByCatId(category)");
			throw e;
		} catch (ClassNotFoundException e) {
			logger.error("ClassNotFoundException got from ProductDao in getProductsByCatId(category)");
			throw e;
		}
		
		model.addAttribute("caps", caps);
		//request.getSession().setAttribute("products", products);
		
		return "viewProducts";
	}
	
	@RequestMapping(value="/gotocart", method = RequestMethod.POST)
	public String goToCart(@RequestParam(value = "selectedProdIds", required = false) String[] selectedProdIds, @ModelAttribute("caps")CatAndProducts caps, @ModelAttribute("order")Order order, HttpServletRequest request) throws ClassNotFoundException, SQLException {
		logger.debug("Inside goToCart method...");
		
		Set<Product> selectedProducts = null;
		Object obj = request.getSession().getAttribute("selectedProducts");
		if(obj ==  null) {
			selectedProducts = new HashSet<Product>();
		} else {
			selectedProducts = (HashSet<Product>)obj;
		}
		try {
			if(selectedProdIds != null) {
				for(String prod : selectedProdIds) {
					logger.debug("Selected ProductId - "+prod);
					selectedProducts.add(prodDao.getProductByProdId(prod));
				}
			
				for(Product prod : caps.getProducts()){
					for(Product selectedProd : selectedProducts) {
						logger.debug("ProductId - "+prod.getProductId()+", Selected - "+prod.getSelected()+", Quantity - "+prod.getQuantity());
						if(selectedProd.getProductId().equals(prod.getProductId())){
							selectedProd.setQuantity(prod.getQuantity());
						}
					}
				}
				request.getSession().setAttribute("selectedProducts", selectedProducts);
			}
		} catch (ClassNotFoundException e) {
			logger.error("ClassNotFoundException got from ProductDao in getProductByProdId(prod)");
			throw e;
		} catch (SQLException e) {
			logger.error("SQLException got from ProductDao in getProductByProdId(prod)");
			throw e;
		}
		request.getSession().setAttribute("selectedProducts", selectedProducts);
		return "viewCart";
	}
	
}
