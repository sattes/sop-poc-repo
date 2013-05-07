package com.sp3.mvc.springdao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.sp3.mvc.enums.CustomerStatusEnum;
import com.sp3.mvc.enums.CustomerTypeEnum;
import com.sp3.mvc.models.Customer;

public class CustomerDao extends BaseDao{
	
	public CustomerDao(JdbcTemplate jdbcTemp)  {
		this.jdbcTemp = jdbcTemp ;
	}
	
	private static Logger logger = Logger.getLogger(CustomerDao.class);
	
	public Customer getCustomerByUserId(String userName) throws SQLException {
		
		String sql = "SELECT * FROM SOPV2.CUSTOMER WHERE USERID = ?";
		logger.debug("SQL Query - "+sql);
		
		@SuppressWarnings("unchecked")
		Customer customer = (Customer) jdbcTemp.queryForObject(sql,
			    new Object[]{userName},
			    new RowMapper() {

			        public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
			        	Customer customer = new Customer();
						customer.setUserName(rs.getString("USERID"));
						customer.setPassword(rs.getString("PASSWORD"));
						customer.setEnabled(rs.getInt("ENABLED"));
						customer.setRoleId(rs.getInt("ROLEID"));
						customer.setEmail(rs.getString("EMAIL"));
						customer.setFname(rs.getString("FIRSTNAME"));
						customer.setLname(rs.getString("LASTNAME"));
						customer.setCustType(CustomerTypeEnum.getEnumByValue(rs.getString("CUSTTYPE")));
						customer.setStatus(CustomerStatusEnum.getEnumByValue(rs.getString("STATUS")));
			            return customer;
			        }
		});
		
		return customer;
	}
	
	public void insertCustomer(Customer customer) throws SQLException {
		
		StringBuffer sb = new StringBuffer();
		sb.append("INSERT INTO SOPV2.CUSTOMER VALUES('")
		.append(customer.getUserName())
		.append("','")
		.append(customer.getPassword())
		.append("','")
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
		logger.debug("jdbcTemp - "+jdbcTemp);
		
		int rs = jdbcTemp.update(sql);
		logger.debug("Result = "+rs);
		
		
		
	}
	
	
	public List<Customer> getDisbledCustomers() throws SQLException {
		
		String sql = "SELECT * FROM SOPV2.CUSTOMER WHERE enabled = 0";
		List<Customer> customers = new ArrayList<Customer>();
		
		List<Map<String,Object>> rows = jdbcTemp.queryForList(sql);
		for (Map row : rows) {
			Customer customer = new Customer();
			customer.setUserName((String)row.get("USERID"));
			customer.setPassword((String)row.get("PASSWORD"));
			customer.setEmail((String)row.get("EMAIL"));
			customer.setFname((String)row.get("FIRSTNAME"));
			customer.setLname((String)row.get("LASTNAME"));
			customer.setCustType(CustomerTypeEnum.getEnumByValue((String)row.get("CUSTTYPE")));
			customer.setStatus(CustomerStatusEnum.getEnumByValue((String)row.get("STATUS")));
			customers.add(customer);
		}
		logger.debug("SQL Query - "+sql);
		
		return customers;
	}

	public void enableCustomers(String customers) throws SQLException {
		
		StringBuffer sb = new StringBuffer();
		sb.append("UPDATE SOPV2.CUSTOMER SET enabled = 1 WHERE userid IN(")
		.append(customers)
		.append(");");
		
		String sql = sb.toString();
		logger.debug("SQL Query - "+sql);
		logger.debug("jdbcTemp - "+jdbcTemp);
		
		int rs = jdbcTemp.update(sql);
		logger.debug("Result = "+rs);
		
	}

}
