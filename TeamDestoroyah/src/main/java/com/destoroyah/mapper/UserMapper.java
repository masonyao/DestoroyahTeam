package com.destoroyah.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.destoroyah.model.User;

public class UserMapper implements RowMapper<User>{
	
	@Override
	public User mapRow(ResultSet rs, int rowNum) throws SQLException {
	 User u = new User();
	 u.setFirstname(rs.getString("firstname"));
	 u.setLastname(rs.getString("lastname"));
	 u.setUsername(rs.getString("username"));
	 u.setEmail(rs.getString("email"));
	 u.setUser_id(rs.getInt("user_id"));
	 u.setGender(rs.getString("gender"));
	 u.setUserpass(rs.getString("userpass"));
	 u.setBirthday(rs.getTimestamp("birthday"));
	 u.setAge(rs.getInt("age"));
	 
		return u;
	}

}
