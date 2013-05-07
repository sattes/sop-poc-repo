package com.sp3.mvc.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.sp3.mvc.enums.CustomerStatusEnum;
import com.sp3.mvc.enums.CustomerTypeEnum;
import com.sp3.mvc.models.Customer;

public class CustomerDaoSpring {
	
	private static Logger logger = Logger.getLogger(CustomerDaoSpring.class);
	
	@Resource(name = "myProps")
	private Properties myProps;
	
	JdbcTemplate jdbcTemp;
	
	public JdbcTemplate getJdbcTemp() {
		return jdbcTemp;
	}
	public void setJdbcTemp(JdbcTemplate jdbcTemp) {
		this.jdbcTemp = jdbcTemp;
	}
	
	@SuppressWarnings("unchecked")
	public Customer getCustomerByUserId(String userId) {
		StringBuffer sb = new StringBuffer();
		sb.append("select USERID,PASSWORD,EMAIL,FIRSTNAME,LASTNAME,CUSTTYPE,STATUS FROM ")
		.append(myProps.getProperty("schemaName"))
		.append("CUSTOMER WHERE USERID = ?");
		String sql = sb.toString();
		logger.debug("sql = "+sql);
		
		Customer actor = (Customer) jdbcTemp.queryForObject(sql,  new Object[]{userId},
			    new RowMapper() {

			        public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
			        	Customer actor = new Customer();
			            actor.setUserName(rs.getString("USERID"));
			            actor.setPassword(rs.getString("PASSWORD"));
			            actor.setEmail(rs.getString("EMAIL"));
			            actor.setFname(rs.getString("FIRSTNAME"));
			            actor.setLname(rs.getString("LASTNAME"));
			            actor.setCustType(CustomerTypeEnum.getEnumByValue(rs.getString("CUSTTYPE")));
			            actor.setStatus(CustomerStatusEnum.getEnumByValue(rs.getString("CUSTTYPE")));
			            return actor;
			        }
			    });
		logger.debug("Order retrieved = "+actor);
		return actor;

	}
	
	public int insertCustomer(Customer customer) {
		StringBuffer sb = new StringBuffer();
		sb.append("insert into ")
		.append(myProps.getProperty("schemaName"))
		.append("customer (USERID,PASSWORD,EMAIL,FIRSTNAME,LASTNAME,CUSTTYPE,STATUS) values (?,?,?,?,?,?,?)");
		String sql = sb.toString();
		logger.debug("sql = "+sql);
		
		return jdbcTemp.update(sql,

				customer.getUserName(),customer.getPassword(),customer.getEmail(),customer.getFname(),customer.getLname(),customer.getCustType().toString(),customer.getStatus().toString()

				);


	}

}
