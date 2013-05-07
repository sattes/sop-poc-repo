package com.sp3.mvc.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.sp3.mvc.models.Discount;

public class DiscountDao {
	
	private static Logger logger = Logger.getLogger(CustomerDao.class);
	
	private Properties myProps;
	
	public DiscountDao() {
		
	}
	
	public DiscountDao(Properties myProps) {
		this.myProps = myProps;
	}
	
	public Discount getDiscountByCustType(String custType) throws SQLException, ClassNotFoundException {
		
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT * FROM ")
		.append(myProps.getProperty("schemaName"))
		.append("DISCOUNT WHERE CUSTTYPE = ")
		.append("'")
		.append(custType)
		.append("'");
		
		String sql = sb.toString();
		logger.debug("SQL Query - "+sql);
		
		Connection con = null;
		Discount discount = null;
		
		try {
			con  = DBUtils.getConnection();
			logger.debug("Got the connection...");
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			logger.debug("Result = "+rs);
			while(rs.next()) {
				discount = new Discount();
				discount.setDiscId(rs.getInt("DISCID"));
				discount.setCustType(rs.getString("CUSTTYPE"));
				discount.setDiscType(rs.getString("DISCTYPE"));
				discount.setDiscPercent(rs.getDouble("DISCPERCENT"));
			}
			
		} catch (SQLException e) {
			logger.error("SQLException occured while geting Discount by custType."+e);
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
		return discount;
		
	}

}
