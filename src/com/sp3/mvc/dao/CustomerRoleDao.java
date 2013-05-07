package com.sp3.mvc.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;

import com.sp3.mvc.enums.CustomerStatusEnum;
import com.sp3.mvc.enums.CustomerTypeEnum;
import com.sp3.mvc.models.Customer;
import com.sp3.mvc.models.CustomerRole;

public class CustomerRoleDao {
	
	private static Logger logger = Logger.getLogger(CustomerRoleDao.class);
	
	private Properties myProps;
	
	public CustomerRoleDao() {
		
	}
	
	public CustomerRoleDao(Properties myProps) {
		this.myProps = myProps;
	}
	
	public Integer getMaxRoleId() throws SQLException, ClassNotFoundException {
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT MAX(ROLEID) FROM ")
		.append(myProps.getProperty("schemaName"))
		.append("CUSTOMERROLES");
		
		String sql = sb.toString();
		logger.debug("SQL Query - "+sql);
		
		Connection con = null;
		Integer maxId = 0;
		
		try {
			con  = DBUtils.getConnection();
			logger.debug("Got the connection...");
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			logger.debug("Result = "+rs);
			while(rs.next()) {
				maxId = rs.getInt(1);
			}
			
		} catch (SQLException e) {
			logger.error("SQLException occured while getting MAX(ROLEID)."+e);
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
		
		return maxId;
	}
	
	public void insertCustomerRole(Connection conn, CustomerRole customerRole) throws SQLException {
		
		StringBuffer sb = new StringBuffer();
		sb.append("INSERT INTO ")
		.append(myProps.getProperty("schemaName"))
		.append("CUSTOMERROLES VALUES(")
		.append(customerRole.getRoleId())
		.append(",'")
		.append(customerRole.getRoleDesc())
		.append("','")
		.append(customerRole.getRoleName())
		.append("');");
		
		String sql = sb.toString();
		logger.debug("SQL Query - "+sql);
				
		try {
			Statement st = conn.createStatement();
			int rs = st.executeUpdate(sql);
			logger.debug("Result = "+rs);
			
		} catch (SQLException e) {
			logger.error("SQLException occured while inserting CustomerRole."+e);
			throw e;
		} 
		
	}
	
	public CustomerRole getRoleByRoleName(String roleName) throws SQLException, ClassNotFoundException {
		
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT * FROM ")
		.append(myProps.getProperty("schemaName"))
		.append("CUSTOMERROLES WHERE ROLENAME = ")
		.append("'")
		.append(roleName)
		.append("'");
		
		String sql = sb.toString();
		logger.debug("SQL Query - "+sql);
		
		Connection con = null;
		CustomerRole customerRole = null;
		
		try {
			con  = DBUtils.getConnection();
			logger.debug("Got the connection...");
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			logger.debug("Result = "+rs);
			while(rs.next()) {
				customerRole = new CustomerRole();
				customerRole.setRoleId(rs.getInt("ROLEID"));
				customerRole.setRoleName(rs.getString("ROLENAME"));
				customerRole.setRoleDesc(rs.getString("ROLEDESC"));
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
		return customerRole;
	}
	
	public CustomerRole getRoleByRoleId(Integer roleId) throws SQLException, ClassNotFoundException {
		
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT * FROM ")
		.append(myProps.getProperty("schemaName"))
		.append("CUSTOMERROLES WHERE ROLEID = ")
		.append(roleId);
		
		String sql = sb.toString();
		logger.debug("SQL Query - "+sql);
		
		Connection con = null;
		CustomerRole customerRole = null;
		
		try {
			con  = DBUtils.getConnection();
			logger.debug("Got the connection...");
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			logger.debug("Result = "+rs);
			while(rs.next()) {
				customerRole = new CustomerRole();
				customerRole.setRoleId(rs.getInt("ROLEID"));
				customerRole.setRoleName(rs.getString("ROLENAME"));
				customerRole.setRoleDesc(rs.getString("ROLEDESC"));
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
		return customerRole;
	}
	
	JdbcTemplate jdbcTemp;
	
	public JdbcTemplate getJdbcTemp() {
		return jdbcTemp;
	}
	public void setJdbcTemp(JdbcTemplate jdbcTemp) {
		this.jdbcTemp = jdbcTemp;
	}

}
