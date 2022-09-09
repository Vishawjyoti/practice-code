package com.institute.eazy.dao.rowmapper;

import com.institute.eazy.entity.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRowMapper implements RowMapper <User>{


    @Override
public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        User ele = new User();
        ele.setId((long) rs.getInt("id"));
        ele.setFirstName(rs.getString("firstname"));
        ele.setLastName(rs.getString("lastname"));
        ele.setEmailId(rs.getString("email"));
        ele.setGender(rs.getString("gender"));
        ele.setStatus(rs.getString("status"));
        ele.setType(rs.getString("type"));
        ele.setMobileNo(rs.getString("mobile"));
        ele.setLoginId(rs.getString("loginid"));
        return ele;
        }
}
