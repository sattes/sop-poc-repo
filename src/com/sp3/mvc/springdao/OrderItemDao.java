package com.sp3.mvc.springdao;

import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;

import com.sp3.mvc.models.OrderItem;

public class OrderItemDao  extends BaseDao  {
	
	public OrderItemDao(JdbcTemplate jdbcTemp)  {
		this.jdbcTemp = jdbcTemp ;
	}
	
private static Logger logger = Logger.getLogger(OrderItemDao.class);
	
	public void insertOrderItem(OrderItem orderItem) throws SQLException, ClassNotFoundException {
		orderItem.setStatus("OK");
		StringBuffer sb = new StringBuffer();
		sb.append("INSERT INTO SOPV2.ORDERITEMS VALUES(")
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
		
		int rs = jdbcTemp.update(sql);
		logger.debug("Result = "+rs);
		
	}
	
	public Integer getMaxItemId() throws SQLException, ClassNotFoundException {
	
		String sql = "SELECT MAX(ITEMID) FROM SOPV2.ORDERITEMS";
		logger.debug("SQL Query - "+sql);

		Integer maxId = jdbcTemp.queryForInt(sql);

		
		return maxId;

	}
	
	
}
