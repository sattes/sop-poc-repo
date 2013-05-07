package com.sp3.mvc.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.sp3.mvc.models.Payment;

public class PaymentDao {
	
	private static Logger logger = Logger.getLogger(PaymentDao.class);
	
	private Properties myProps;
	
	public PaymentDao() {
		
	}
	
	public PaymentDao(Properties myProps) {
		this.myProps = myProps;
	}
	
	public String getMaxPaymentId() throws SQLException, ClassNotFoundException {
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT MAX(CAST(SUBSTR(PAYMENTID, 9) AS INT)) FROM ")
		.append(myProps.getProperty("schemaName"))
		.append("PAYMENT");
		
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
			logger.error("SQLException occured while getting MAX(PAYMENTID)."+e);
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
			return "PAYMENT-"+maxId;
		}
	}
	
	public List<Payment> getPaymentsByStatus(String status) throws SQLException, ClassNotFoundException {
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT * FROM ")
		//.append(myProps.getProperty("schemaName"))
		.append("SOPV2.PAYMENT WHERE STATUS = ")
		.append("'")
		.append(status)
		.append("'");
		
		String sql = sb.toString();
		logger.debug("SQL Query - "+sql);
		
		Connection con = null;
		List<Payment> payments = new ArrayList<Payment>();
		
		try {
			con  = DBUtils.getConnection();
			logger.debug("Got the connection...");
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			logger.debug("Result = "+rs);
			while(rs.next()) {
				Payment payment = new Payment();
				payment.setPaymentId(rs.getString("PAYMENTID"));
				payment.setOrderId(rs.getString("ORDERID"));
				payment.setPaymentAmount(rs.getDouble("TOTALAMOUNT"));
				payment.setPaymentDate(rs.getDate("PAYMENTDATE"));
				payment.setPaymentStatus(rs.getString("STATUS"));
				payments.add(payment);
			}
			
		} catch (SQLException e) {
			logger.error("SQLException occured in getPaymentsByStatus(String status)."+e);
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
		
		return payments;
	}
	
	public static void main(String []args) throws SQLException, ClassNotFoundException {
		PaymentDao dao = new PaymentDao();
		List<Payment> payments = dao.getPaymentsByStatus("PENDING");
	}

}
