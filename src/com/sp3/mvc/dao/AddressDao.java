package com.sp3.mvc.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.sp3.mvc.enums.AddressTypeEnum;
import com.sp3.mvc.models.Address;

public class AddressDao {
	
	private static Logger logger = Logger.getLogger(AddressDao.class);
	
	private Properties myProps;
	
	public AddressDao() {
		
	}
	
	public AddressDao(Properties myProps) {
		this.myProps = myProps;
	}
	
	public void insertAddress(Connection conn, Address address) throws SQLException {
		
		StringBuffer sb = new StringBuffer();
		sb.append("INSERT INTO ")
		.append(myProps.getProperty("schemaName"))
		.append("ADDRESS VALUES(")
		.append(address.getAddressId())
		.append(",'")
		.append(address.getUserId())
		.append("','")
		.append(address.getAddressType())
		.append("','")
		.append(address.getAddress1())
		.append("','")
		.append(address.getAddress2())
		.append("','")
		.append(address.getCity())
		.append("','")
		.append(address.getState())
		.append("','")
		.append(address.getZip())
		.append("','")
		.append(address.getCountry())
		.append("','")
		.append(address.getPhone())
		.append("');");
		
		String sql = sb.toString();
		logger.debug("SQL Query - "+sql);
		
		try {
			Statement st = conn.createStatement();
			int rs = st.executeUpdate(sql);
			logger.debug("Result = "+rs);
			
		} catch (SQLException e) {
			logger.error("SQLException occured while inserting address."+e);
			throw e;
		} /*catch (ClassNotFoundException e) {
			logger.error("ClassNotFoundException while  loading the driver."+e);
			throw e;
		} finally {
			try {
				DBUtils.closeConnection(conn);
			} catch (SQLException e) {
				logger.error("SQLException occured while closing connection."+e);
			}
		}*/
		
	}
	
	public Integer getMaxAddressId() throws SQLException, ClassNotFoundException {
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT MAX(ADDRID) FROM ")
		.append(myProps.getProperty("schemaName"))
		.append("ADDRESS");
		
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
			logger.error("SQLException occured while getting MAX(ADDRID)."+e);
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
	
	public Address getAddressByUserId(String userId) throws SQLException, ClassNotFoundException {
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT * FROM ")
		.append(myProps.getProperty("schemaName"))
		.append("ADDRESS WHERE USERID = ")
		.append("'")
		.append(userId)
		.append("'");
		
		String sql = sb.toString();
		logger.debug("SQL Query - "+sql);
		
		Connection con = null;
		Address address = null;
		
		try {
			con  = DBUtils.getConnection();
			logger.debug("Got the connection...");
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			logger.debug("Result = "+rs);
			while(rs.next()) {
				address = new Address();
				address.setAddressId(rs.getInt("ADDRID"));
				address.setUserId(rs.getString("USERID"));
				address.setAddressType(AddressTypeEnum.getEnumByValue(rs.getString("ADDRTYPE")));
				address.setAddress1(rs.getString("ADDR1"));
				address.setAddress2(rs.getString("ADDR2"));
				address.setCity(rs.getString("CITY"));
				address.setState(rs.getString("STATE"));
				address.setZip(rs.getString("ZIP"));
				address.setCountry("COUNTRY");
				address.setPhone(rs.getString("PHONE"));
			}
			
		} catch (SQLException e) {
			logger.error("SQLException occured while getting MAX(ADDRID)."+e);
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
		
		return address;
	}
	
	public Address getAddressByAddrId(Integer addrId) throws SQLException, ClassNotFoundException {
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT * FROM ")
		.append(myProps.getProperty("schemaName"))
		.append("ADDRESS WHERE ADDRID = ")
		.append(addrId);
		
		String sql = sb.toString();
		logger.debug("SQL Query - "+sql);
		
		Connection con = null;
		Address address = null;
		
		try {
			con  = DBUtils.getConnection();
			logger.debug("Got the connection...");
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			logger.debug("Result = "+rs);
			while(rs.next()) {
				address = new Address();
				address.setAddressId(rs.getInt("ADDRID"));
				address.setUserId(rs.getString("USERID"));
				address.setAddressType(AddressTypeEnum.getEnumByValue(rs.getString("ADDRTYPE")));
				address.setAddress1(rs.getString("ADDR1"));
				address.setAddress2(rs.getString("ADDR2"));
				address.setCity(rs.getString("CITY"));
				address.setState(rs.getString("STATE"));
				address.setZip(rs.getString("ZIP"));
				address.setCountry("COUNTRY");
				address.setPhone(rs.getString("PHONE"));
			}
			
		} catch (SQLException e) {
			logger.error("SQLException occured while getting MAX(ADDRID)."+e);
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
		
		return address;
	}

}
