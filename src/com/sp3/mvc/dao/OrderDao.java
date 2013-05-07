package com.sp3.mvc.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.sp3.mvc.enums.OrderStatusEnum;
import com.sp3.mvc.helper.DateUtils;
import com.sp3.mvc.models.Order;

public class OrderDao {
	
	private static Logger logger = Logger.getLogger(OrderDao.class);
	
	private Properties myProps;
	
	public OrderDao() {
		
	}
	
	public OrderDao(Properties myProps) {
		this.myProps = myProps;
	}
	
	public void insertOrder(Connection con, Order order) throws SQLException, ClassNotFoundException {
		StringBuffer sb = new StringBuffer();
		sb.append("INSERT INTO ")
		.append(myProps.getProperty("schemaName"))
		.append("ORDERS VALUES('")
		.append(order.getOrderId())
		.append("','")
		.append(order.getUserId())
		.append("',")
		.append(order.getAddressId())
		.append(",'")
		.append(DateUtils.getFormattedDateStr(order.getOrderDate()))
		.append("',")
		.append(order.getTotalPrice())
		.append(",'")
		.append(order.getStatus())
		.append("');");
		
		String sql = sb.toString();
		logger.debug("SQL Query - "+sql);
		
		//Connection con = null;
		
		try {
			/*con  = DBUtils.getConnection();
			logger.debug("Got the connection...");*/
			Statement st = con.createStatement();
			int rs = st.executeUpdate(sql);
			logger.debug("Result = "+rs);
			
		} catch (SQLException e) {
			logger.error("SQLException occured while inserting Order."+e);
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
	
	public Order getOrderByOrderId(String orderId) throws SQLException, ClassNotFoundException {
		
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT * FROM ")
		.append(myProps.getProperty("schemaName"))
		.append("ORDERS WHERE ORDERID = ")
		.append("'")
		.append(orderId)
		.append("'");
		
		String sql = sb.toString();
		logger.debug("SQL Query - "+sql);
		
		Connection con = null;
		Order order = null;
		
		try {
			con  = DBUtils.getConnection();
			logger.debug("Got the connection...");
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			logger.debug("Result = "+rs);
			while(rs.next()) {
				order = getLoadedOrderObj(rs);
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
		return order;
	}
	
	private Order getLoadedOrderObj(ResultSet rs) throws SQLException {
		Order order = new Order();
		try {
			order.setAddressId(rs.getInt("ADDRID"));
			order.setOrderDate(rs.getDate("ORDERDATE"));
			order.setOrderId(rs.getString("ORDERID"));
			order.setStatus(OrderStatusEnum.getEnumByValue(rs.getString("STATUS")));
			order.setTotalPrice(rs.getDouble("TOTALPRICE"));
			order.setUserId(rs.getString("USERID"));
		} catch (SQLException e) {
			logger.error("SQLException occured while inserting registration."+e);
			throw e;
		}
		return order;
	}
	
	public String getMaxOrderId() throws SQLException, ClassNotFoundException {
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT MAX(CAST(SUBSTR(ORDERID, 7) AS INT)) FROM ")
		.append(myProps.getProperty("schemaName"))
		.append("ORDERS");
		
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
		
		if(maxId == 0) {
			return null;
		} else {
			maxId = maxId+1;
			return "ORDER-"+maxId;
		}
		
	}
	
	/*public static void main(String[] args) throws SQLException, ClassNotFoundException {
		OrderDao dao = new OrderDao();
		Order ord = dao.getOrderByOrderId("O100");
		System.out.println("Order = "+ord);
		System.out.println("Order Status = "+ord.getStatus());
	}*/

}
