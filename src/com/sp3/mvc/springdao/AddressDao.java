package com.sp3.mvc.springdao;



import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import com.sp3.mvc.models.Address;

public class AddressDao extends BaseDao{
	
	private static Logger logger = Logger.getLogger(AddressDao.class);
	
	public AddressDao(JdbcTemplate jdbcTemp)  {
		this.jdbcTemp = jdbcTemp ;
	}
	
	public void insertAddress(Address address) throws DataAccessException {
		
		StringBuffer sb = new StringBuffer();
		sb.append("INSERT INTO SOPV2.ADDRESS VALUES(")
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
		
		int rs = jdbcTemp.update(sql);
		logger.debug("Result of insertAddress = "+rs);
		
		
	}
	
	public Integer getMaxAddressId() throws DataAccessException {
		
		String sql = "SELECT MAX(ADDRID) FROM SOPV2.ADDRESS";
		logger.debug("SQL Query - "+sql);
		
		int maxId = jdbcTemp.queryForInt(sql);
		logger.debug("Max AddressId" + maxId);
		
		return maxId;
	}

}
