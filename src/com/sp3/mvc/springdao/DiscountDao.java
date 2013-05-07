package com.sp3.mvc.springdao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.sp3.mvc.enums.CustomerStatusEnum;
import com.sp3.mvc.enums.CustomerTypeEnum;
import com.sp3.mvc.models.Customer;
import com.sp3.mvc.models.Discount;

public class DiscountDao extends BaseDao  {
	
	public DiscountDao(JdbcTemplate jdbcTemp)  {
		this.jdbcTemp = jdbcTemp ;
	}
	
	private static Logger logger = Logger.getLogger(CustomerDao.class);
	
	public Discount getDiscountByCustType(String custType) throws SQLException, ClassNotFoundException {
		
				
		String sql = "SELECT * FROM SOPV2.DISCOUNT WHERE CUSTTYPE = ?";
		logger.debug("SQL Query - "+sql);
		
		
		@SuppressWarnings("unchecked")
		Discount discount = (Discount) jdbcTemp.queryForObject(sql,
			    new Object[]{custType},
			    new RowMapper() {

			        public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
			        	Discount discount = new Discount();
						discount.setDiscId(rs.getInt("DISCID"));
						discount.setCustType(rs.getString("CUSTTYPE"));
						discount.setDiscType(rs.getString("DISCTYPE"));
						discount.setDiscPercent(rs.getDouble("DISCPERCENT"));
			            return discount;
			        }
		});
		return discount;
		
	}

}
