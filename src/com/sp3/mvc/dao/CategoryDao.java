package com.sp3.mvc.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.sp3.mvc.models.Category;

public class CategoryDao {
	
	private static Logger logger = Logger.getLogger(CategoryDao.class);
	
	private Properties myProps;
	
	public CategoryDao() {
		
	}
	
	public CategoryDao(Properties myProps) {
		this.myProps = myProps;
	}
	
	public List<Category> getAllCategories() throws SQLException, ClassNotFoundException {
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT * FROM ")
		.append(myProps.getProperty("schemaName"))
		.append("CATEGORY");
		String sql = sb.toString();
		logger.debug("SQL Query - "+sql);
		
		Connection con = null;
		List<Category> categoryList = new ArrayList<Category>();
		
		try {
			con  = DBUtils.getConnection();
			logger.debug("Got the connection...");
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			logger.debug("Result = "+rs);
			while(rs.next()) {
				Category cat = new Category();
				cat.setCatId(rs.getString("CATID"));
				cat.setName(rs.getString("NAME"));
				cat.setDescription(rs.getString("DESCN"));
				categoryList.add(cat);
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
		return categoryList;
	}
	
	public Category getCategoryByCatId(String catId) throws SQLException, ClassNotFoundException {
		
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT * FROM ")
		.append(myProps.getProperty("schemaName"))
		.append("CATEGORY WHERE CATID = ")
		.append("'")
		.append(catId)
		.append("'");
		
		String sql = sb.toString();
		logger.debug("SQL Query - "+sql);
		
		Connection con = null;
		Category category = null;
		
		try {
			con  = DBUtils.getConnection();
			logger.debug("Got the connection...");
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			logger.debug("Result = "+rs);
			while(rs.next()) {
				category = new Category();
				category.setCatId(rs.getString("CATID"));
				category.setName(rs.getString("NAME"));
				category.setDescription(rs.getString("DESCN"));
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
		return category;
	}

}
