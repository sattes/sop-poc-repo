package com.sp3.mvc.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import org.apache.log4j.Logger;

public class DirectDebitDao {
	
	private static Logger logger = Logger.getLogger(DirectDebitDao.class);
	
	private Properties myProps;
	
	public DirectDebitDao() {
		
	}
	
	public DirectDebitDao(Properties myProps) {
		this.myProps = myProps;
	}
	
	public String getMaxDebitId() throws SQLException, ClassNotFoundException {
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT MAX(CAST(SUBSTR(DEBTID, 7) AS INT)) FROM ")
		.append(myProps.getProperty("schemaName"))
		.append("DIRECTDEBIT");
		
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
			logger.error("SQLException occured while getting MAX(DEBTID)."+e);
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
		if(maxId == 0) {
			return null;
		} else {
			maxId = maxId+1;
			return "DEBIT-"+maxId;
		}
		
	}

}
