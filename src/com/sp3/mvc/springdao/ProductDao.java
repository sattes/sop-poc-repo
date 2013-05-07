package com.sp3.mvc.springdao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import com.sp3.mvc.models.Product;

public class ProductDao extends BaseDao {
	
	public ProductDao(JdbcTemplate jdbcTemp)  {
		this.jdbcTemp = jdbcTemp ;
	}
	
	private static Logger logger = Logger.getLogger(ProductDao.class);
	
	public List<Product> getProductsByCatId(String catId) throws SQLException, ClassNotFoundException {
	
		String sql = "SELECT * FROM SOPV2.PRODUCT WHERE CATID = ?";
		logger.debug("SQL Query - "+sql);

		List<Product> products = new ArrayList<Product>();
		
		products  = jdbcTemp.query(sql, new Object[]{catId},
				new BeanPropertyRowMapper<Product>(Product.class));

		
		return products;
	}
	
	public Product getProductByProdId(String prodId) throws ClassNotFoundException, SQLException {
		
			
		String sql = "SELECT * FROM SOPV2.PRODUCT WHERE PRODUCTID = ?";
		logger.debug("SQL Query - "+sql);
		
		Product product = null;
		List<Product> products = new ArrayList<Product>();
		
		products  = jdbcTemp.query(sql, new Object[]{prodId},
					new BeanPropertyRowMapper<Product>(Product.class));
		if(products.size()>0) {
			product = products.get(0);
		}
		return product;
		
	}
	
	private Product getLoadedProdObj(ResultSet rs) throws SQLException {
		Product prod = new Product();
		try {
			prod.setCategory(rs.getString("CATID"));
			prod.setDescription(rs.getString("DESCN"));
			prod.setName(rs.getString("NAME"));
			prod.setProductId(rs.getString("PRODUCTID"));
			prod.setUnitCost(rs.getDouble("UNITCOST"));
		} catch (SQLException e) {
			logger.error("SQLException occured while inserting registration."+e);
			throw e;
		}
		return prod;
	}

}
