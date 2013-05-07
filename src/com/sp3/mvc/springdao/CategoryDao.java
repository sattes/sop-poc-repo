package com.sp3.mvc.springdao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.sp3.mvc.models.Category;

public class CategoryDao extends BaseDao {
	
	public CategoryDao(JdbcTemplate jdbcTemp)  {
		this.jdbcTemp = jdbcTemp ;
	}
	
	private static Logger logger = Logger.getLogger(CategoryDao.class);
	
	public List<Category> getAllCategories() throws SQLException {
		String sql = "SELECT * FROM SOPV2.CATEGORY";
		logger.debug("SQL Query - "+sql);
		
		List<Category> categoryList = new ArrayList<Category>();
    	  categoryList  = jdbcTemp.query(sql,
					new BeanPropertyRowMapper<Category>(Category.class));

		return categoryList;
	}
	
	public Category getCategoryByCatId(String catId) throws SQLException {
		
		String sql = "SELECT * FROM SOPV2.CATEGORY WHERE CATID = ?";
		logger.debug("SQL Query - "+sql);
		
		@SuppressWarnings("unchecked")
		Category category = (Category) jdbcTemp.queryForObject(sql,
			    new Object[]{catId},
			    new RowMapper() {

			        public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
			        	Category actor = new Category();
			        	actor.setCatId(rs.getString("CATID"));
			        	actor.setName(rs.getString("NAME"));
			        	actor.setDescription(rs.getString("DESCN"));
			            return actor;
			        }
		});
			
		return category;
	}

}
