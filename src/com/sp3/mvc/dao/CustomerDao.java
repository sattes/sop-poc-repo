package com.sp3.mvc.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;

import com.sp3.mvc.enums.CustomerStatusEnum;
import com.sp3.mvc.enums.CustomerTypeEnum;
import com.sp3.mvc.models.Customer;

public class CustomerDao {
	
	private static Logger logger = Logger.getLogger(CustomerDao.class);
	
	private Properties myProps;
	
	public CustomerDao() {
		
	}
	
	public CustomerDao(Properties myProps) {
		this.myProps = myProps;
	}
	
	public Customer getCustomerByUserId(String userName) throws SQLException, ClassNotFoundException {
		
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT * FROM ")
		.append(myProps.getProperty("schemaName"))
		.append("CUSTOMER WHERE USERID = ")
		.append("'")
		.append(userName)
		.append("'");
		
		String sql = sb.toString();
		logger.debug("SQL Query - "+sql);
		
		Connection con = null;
		Customer customer = null;
		
		try {
			con  = DBUtils.getConnection();
			logger.debug("Got the connection...");
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			logger.debug("Result = "+rs);
			while(rs.next()) {
				customer = new Customer();
				customer.setUserName(rs.getString("USERID"));
				customer.setPassword(rs.getString("PASSWORD"));
				customer.setEmail(rs.getString("EMAIL"));
				customer.setFname(rs.getString("FIRSTNAME"));
				customer.setLname(rs.getString("LASTNAME"));
				customer.setEnabled(rs.getInt("ENABLED"));
				customer.setRoleId(rs.getInt("ROLEID"));
				customer.setCustType(CustomerTypeEnum.getEnumByValue(rs.getString("CUSTTYPE")));
				customer.setStatus(CustomerStatusEnum.getEnumByValue(rs.getString("STATUS")));
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
		return customer;
	}
	
	public void insertCustomer(Connection conn, Customer customer) throws SQLException {
		
		StringBuffer sb = new StringBuffer();
		sb.append("INSERT INTO ")
		.append(myProps.getProperty("schemaName"))
		.append("CUSTOMER VALUES('")
		.append(customer.getUserName())
		.append("','")
		.append(customer.getPassword())
		.append("',")
		.append(customer.getEnabled())
		.append(",")
		.append(customer.getRoleId())
		.append(",'")
		.append(customer.getEmail())
		.append("','")
		.append(customer.getFname())
		.append("','")
		.append(customer.getLname())
		.append("','")
		.append(customer.getCustType())
		.append("','")
		.append(customer.getStatus())
		.append("');");
		
		String sql = sb.toString();
		logger.debug("SQL Query - "+sql);
		
				
		try {
			Statement st = conn.createStatement();
			int rs = st.executeUpdate(sql);
			logger.debug("Result = "+rs);
			
		} catch (SQLException e) {
			logger.error("SQLException occured while inserting registration."+e);
			throw e;
		} /*catch (ClassNotFoundException e) {
			logger.error("ClassNotFoundException while  loading the driver."+e);
			throw e;
		} finally {
			try {
				DBUtils.closeConnection(con);
			} catch (SQLException e) {
				logger.error("SQLException occured while closing connection."+e);
			}
		}*/
		
	}
	
	JdbcTemplate jdbcTemp;
	
	public JdbcTemplate getJdbcTemp() {
		return jdbcTemp;
	}
	public void setJdbcTemp(JdbcTemplate jdbcTemp) {
		this.jdbcTemp = jdbcTemp;
	}
	
	public static void main(String args[]) throws SQLException, ClassNotFoundException {
		CustomerDao dao = new CustomerDao();
		Customer cust = dao.getCustomerByUserId("imchitti");
		System.out.println("cust = "+cust);
	}

}
