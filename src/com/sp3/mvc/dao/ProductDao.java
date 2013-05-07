package com.sp3.mvc.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.sp3.mvc.models.Product;

public class ProductDao {
	
	private static Logger logger = Logger.getLogger(ProductDao.class);
	
	private Properties myProps;
	
	public ProductDao() {
		
	}
	
	public ProductDao(Properties myProps) {
		this.myProps = myProps;
	}
	
	public List<Product> getProductsByCatId(String catId) throws SQLException, ClassNotFoundException {
		
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT * FROM ")
		.append(myProps.getProperty("schemaName"))
		.append("PRODUCT WHERE CATID = ")
		.append("'")
		.append(catId)
		.append("'");
		
		String sql = sb.toString();
		logger.debug("SQL Query - "+sql);
		
		Connection con = null;
		List<Product> products = new ArrayList<Product>();
		
		try {
			con  = DBUtils.getConnection();
			logger.debug("Got the connection...");
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			logger.debug("Result = "+rs);
			while(rs.next()) {
				Product prod = getLoadedProdObj(rs);
				products.add(prod);
			}
			
		} catch (SQLException e) {
			logger.error("SQLException occured while inserting registration."+e);
			throw e;
		} catch (ClassNotFoundException e) {
			logger.error("ClassNotFoundException while  loading the driver."+e);
			throw e;
		} finally {
			try {
				DBUtils.closeConnection(con);
			} catch (SQLException e) {
				logger.error("SQLException occured while closing connection."+e);
			}
		}
		return products;
	}
	
	public Product getProductByProdId(String prodId) throws ClassNotFoundException, SQLException {
		
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT * FROM ")
		.append(myProps.getProperty("schemaName"))
		.append("PRODUCT WHERE PRODUCTID = ")
		.append("'")
		.append(prodId)
		.append("'");
		
		String sql = sb.toString();
		logger.debug("SQL Query - "+sql);
		
		Connection con = null;
		Product product = null;
		
		try {
			con  = DBUtils.getConnection();
			logger.debug("Got the connection...");
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			logger.debug("Result = "+rs);
			while(rs.next()) {
				product = getLoadedProdObj(rs);
			}
		} catch (SQLException e) {
			logger.error("SQLException occured while inserting registration."+e);
			throw e;
		} catch (ClassNotFoundException e) {
			logger.error("ClassNotFoundException while  loading the driver."+e);
			throw e;
		} finally {
			try {
				DBUtils.closeConnection(con);
			} catch (SQLException e) {
				logger.error("SQLException occured while closing connection."+e);
			}
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
