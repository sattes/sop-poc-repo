package com.sp3.mvc.springdao;


import org.springframework.jdbc.core.JdbcTemplate;

public class BaseDao {
	
	
	JdbcTemplate jdbcTemp;
	
	public JdbcTemplate getJdbcTemp() {
		return jdbcTemp;
	}
	public void setJdbcTemp(JdbcTemplate jdbcTemp) {
		this.jdbcTemp = jdbcTemp;
	}

}
