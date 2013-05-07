package com.sp3.mvc.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.sp3.mvc.enums.OrderStatusEnum;
import com.sp3.mvc.models.Order;
import com.sp3.mvc.models.OrderItem;

public class OrderItemDao {
	
	private static Logger logger = Logger.getLogger(OrderItemDao.class);

	private Properties myProps;
	
	public OrderItemDao() {
		
	}
	
	public OrderItemDao(Properties myProps) {
		this.myProps = myProps;
	}
	
	public void insertOrderItem(Connection con, OrderItem orderItem) throws SQLException, ClassNotFoundException {
		orderItem.setStatus("OK");
		StringBuffer sb = new StringBuffer();
		sb.append("INSERT INTO ")
		.append(myProps.getProperty("schemaName"))
		.append("ORDERITEMS VALUES(")
		.append(orderItem.getItemId())
		.append(",'")
		.append(orderItem.getOrderId())
		.append("','")
		.append(orderItem.getProductId())
		.append("',")
		.append(orderItem.getQuantity())
		.append(",")
		.append(orderItem.getListPrice())
		.append(",")
		.append(orderItem.getUnitPrice())
		.append(",'")
		.append(orderItem.getStatus())
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
			logger.error("SQLException occured while inserting OrderItem."+e);
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
	
	public Integer getMaxItemId() throws SQLException, ClassNotFoundException {
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT MAX(ITEMID) FROM ")
		.append(myProps.getProperty("schemaName"))
		.append("ORDERITEMS");
		
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
			logger.error("SQLException occured while getting MAX(ITEMID)."+e);
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
	
	public List<OrderItem> getItemsByOrderId(String orderId) throws SQLException, ClassNotFoundException {
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT * FROM ")
		.append(myProps.getProperty("schemaName"))
		.append("ORDERITEMS WHERE ORDERID = ")
		.append("'")
		.append(orderId)
		.append("'");
		
		String sql = sb.toString();
		logger.debug("SQL Query - "+sql);
		
		Connection con = null;
		List<OrderItem> itemsList = null;
		
		try {
			con  = DBUtils.getConnection();
			logger.debug("Got the connection...");
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			logger.debug("Result = "+rs);
			itemsList = new ArrayList<OrderItem>();
			while(rs.next()) {
				OrderItem orderItem = new OrderItem();
				orderItem.setItemId(rs.getInt("ITEMID"));
				orderItem.setOrderId(rs.getString("ORDERID"));
				orderItem.setProductId(rs.getString("PRODUCTID"));
				orderItem.setQuantity(rs.getInt("QUANTITY"));
				orderItem.setListPrice(rs.getDouble("LISTPRICE"));
				orderItem.setUnitPrice(rs.getDouble("UNITCOST"));
				orderItem.setStatus(rs.getString("STATUS"));
				itemsList.add(orderItem);
			}
			
		} catch (SQLException e) {
			logger.error("SQLException occured while GETTING ORDERS registration."+e);
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
		return itemsList;
	}
	
	private OrderItem getLoadedOrderObj(ResultSet rs) throws SQLException {
		OrderItem orderItem = new OrderItem();
		try {
			orderItem.setItemId(rs.getInt("ITEMID"));
			orderItem.setOrderId(rs.getString("ORDERID"));
			orderItem.setProductId(rs.getString("PRODUCTID"));
			orderItem.setQuantity(rs.getInt("QUANTITY"));
			orderItem.setListPrice(rs.getDouble("LISTPRICE"));
			orderItem.setUnitPrice(rs.getDouble("UNITCOST"));
			orderItem.setStatus(rs.getString("STATUS"));
		} catch (SQLException e) {
			logger.error("SQLException occured while inserting registration."+e);
			throw e;
		}
		return orderItem;
	}
	
	public static void main(String[] args) throws SQLException, ClassNotFoundException {
		OrderItemDao itemDao = new OrderItemDao();
		System.out.println(itemDao.getMaxItemId());
	}

}
