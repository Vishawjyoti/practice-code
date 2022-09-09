package com.institute.eazy.dao;


import com.institute.eazy.dao.rowmapper.UserRowMapper;
import com.institute.eazy.entity.User;
import com.institute.eazy.response.Errors;
import com.institute.eazy.response.ResponseError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class UserDao {

    @Autowired
    JdbcTemplate jt;

    @Autowired
    NamedParameterJdbcTemplate npjt;


    public String getPassword(Map<String,Object> params, List<ResponseError> errors) throws Exception {
        if( params.get("loginIdMap") == null)
            return null;
        String loginId = params.get("loginIdMap").toString();
        MapSqlParameterSource sqlParams = new MapSqlParameterSource();
        sqlParams.addValue("sqlLoginId",loginId );
        String newQuery = "select password from userlogin where userid in (" +
                " select id from user where email = :sqlLoginId  or mobile = :sqlLoginId  or loginid = :sqlLoginId) ";
       List<String> userPasswords =  npjt.queryForList(newQuery,sqlParams,String.class);
       if(userPasswords.size() == 1)
       {
           return userPasswords.get(0);
       }
        if(userPasswords.size() == 0) {
            errors.add(Errors.InvalidLoginId_1);
            return null;
        }
        if(userPasswords.size() > 1)
        {
            errors.add(Errors.MultipleRowAgainstGivenLoginId_2);
          return null;
        }
           return null;
    }
    public Integer addUser(Map<String, Object> params, List<ResponseError> errors) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        User user = (User) params.get("user");
        String query = "insert into user (firstname, lastname, `gender`,`type`,`email`,`mobile`,`loginid`)" +
                " values (:firstName, :lastName, :gender, :type, :email, :mobile, :loginId) ";

        MapSqlParameterSource sqlParams = new MapSqlParameterSource();
        sqlParams.addValue("firstName", user.getFirstName());
        sqlParams.addValue("lastName", user.getLastName());
        sqlParams.addValue("gender", user.getGender());
        sqlParams.addValue("type", user.getType());
        sqlParams.addValue("email", user.getEmailId());
        sqlParams.addValue("mobile", user.getMobileNo());
        sqlParams.addValue("loginId", user.getLoginId());

        npjt.update(query, sqlParams, keyHolder);
        return keyHolder.getKey().intValue();


    }

    public Integer updateUser(Map<String, Object> params, List<ResponseError> errors) {
        User user = (User) params.get("user");

        MapSqlParameterSource sqlParams = new MapSqlParameterSource();
        sqlParams.addValue("id", user.getId());
        sqlParams.addValue("firstName", user.getFirstName());
        sqlParams.addValue("lastName", user.getLastName());
        sqlParams.addValue("gender", user.getGender());
        sqlParams.addValue("type", user.getType());
        sqlParams.addValue("email", user.getEmailId());
        sqlParams.addValue("mobile", user.getMobileNo());
        sqlParams.addValue("loginId", user.getLoginId());

        String query = "update user set firstname =  :firstName, lastname = :lastName, gender = :gender, " +
                " type = :type, email = :email, mobile = :mobile, loginid = :loginId where id = :id";
        npjt.update(query, sqlParams);
        return Math.toIntExact((user.getId()));
    }

    public List<User> getUser(Map<String, Object> params, List<ResponseError> errors) {
        MapSqlParameterSource sqlParams = new MapSqlParameterSource();
    StringBuilder query = new StringBuilder("select * from user where 1=1");
    if(params.get("idMap")!= null){
        query.append(" and id = :idsqlMap");
        sqlParams.addValue("idsqlMap", params.get("idMap").toString());
    }

    if(params.get("firstName") != null){
        sqlParams.addValue("firstName", params.get("firstName").toString());
        query.append(" and firstname like '%").append(params.get("firstName").toString()).append("%' ");
    }

    if(params.get("lastName")!= null){
        sqlParams.addValue("lastName", params.get("lastName").toString());
        query.append(" and lastname like '%").append(params.get("lastName").toString()).append("%' ");
    }

    if(params.get("gender")!= null){
        sqlParams.addValue("gender", params.get("gender").toString());
        query.append(" and gender = :gender ");
    }

    if(params.get("status")!= null){
        sqlParams.addValue("status", params.get("status").toString());
        query.append(" and status = :status ");
    }

    if(params.get("type")!= null){
        sqlParams.addValue("type", params.get("type").toString());
        query.append(" and type = :type ");
    }

    if(params.get("emailId")!= null){
        sqlParams.addValue("emailId", params.get("emailId").toString());
        query.append(" and email like '%").append(params.get("emailId").toString()).append("%' ");
    }

    if(params.get("mobileNo")!= null){
        sqlParams.addValue("mobileNo", params.get("mobileNo").toString());
        query.append(" and mobile like '%").append(params.get("mobileNo").toString()).append("%' ");
    }

    if(params.get("loginId")!= null){
        sqlParams.addValue("loginId", params.get("loginId").toString());
        query.append(" and loginid like '%").append(params.get("loginId").toString()).append("%' ");
    }
        System.out.println(" Query :::: "+query);
        List<User> user = npjt.query(query.toString(), sqlParams, new UserRowMapper());
        return user;
     }


    public Boolean isIdExist(Long id) {
        String query = "select id from user where id = :id";
        MapSqlParameterSource sqlParams = new MapSqlParameterSource();
        sqlParams.addValue("id", id);
        List list = npjt.queryForList(query, sqlParams);
        return list.size() > 0;
    }

    public Long patchUser(Map<String, Object> params, List<ResponseError> errors) {
        MapSqlParameterSource sqlParams = new MapSqlParameterSource();
        String query = "update user set id=id";
        if (params.get("firstName")!=null)
        {
            sqlParams.addValue("firstName", params.get("firstName").toString());
            query = query + " ,firstname = :firstName";
        }
        if (params.get("lastName")!=null)
        {
            sqlParams.addValue("lastName", params.get("lastName").toString());
            query = query + " ,lastname = :lastName";
        }
        if (params.get("emailId")!=null)
        {
            sqlParams.addValue("email", params.get("emailId").toString());
            query = query + " ,email = :email";
        }
        if (params.get("loginId")!=null)
        {
            sqlParams.addValue("loginId", params.get("loginId").toString());
            query = query + " ,loginid = :loginId";
        }
        if (params.get("gender")!=null)
        {
            sqlParams.addValue("gender", params.get("gender").toString());
            query = query + " ,gender = :gender";
        }
        if (params.get("status")!=null)
        {
            sqlParams.addValue("status", params.get("status").toString());
            query = query + " ,status = :status";
        }
        if (params.get("type")!=null)
        {
            sqlParams.addValue("type", params.get("type").toString());
            query = query + " ,type = :type";
        }
        if (params.get("mobileNo")!=null)
        {
            sqlParams.addValue("mobile", params.get("mobileNo").toString());
            query = query + " ,mobile = :mobile";
        }
        sqlParams.addValue("id", params.get("id"));
        query = query + " where id = :id";
        System.out.println(" sqlparam  type      "+ sqlParams);
        System.out.println("query :::    "+ query);
        npjt.update(query, sqlParams);

        return (Long) params.get("id");
    }

    public Integer deleteUser(Map<String, Object> params) {
        Integer  noOfRowsAffected;
        MapSqlParameterSource sqlParams = new MapSqlParameterSource();
        sqlParams.addValue("id", params.get("id"));
        String query = "delete from user where id =:id";

        noOfRowsAffected= npjt.update(query, sqlParams);
        return noOfRowsAffected;

    }

    public boolean isEmailExist(String emailId, Long id) {
        MapSqlParameterSource sqlParams = new MapSqlParameterSource();
        sqlParams.addValue("email", emailId);
        sqlParams.addValue("id", id);
        String query = "select id from user where email = :email and id<>:id";
        List list = npjt.queryForList(query, sqlParams);
        return list.size() > 0;
    }
    public boolean isEmailExist(String emailId) {
        MapSqlParameterSource sqlParams = new MapSqlParameterSource();
        sqlParams.addValue("email", emailId);
        String query = "select id from user where email = :email ";
        List list = npjt.queryForList(query, sqlParams);
        return list.size() > 0;
    }


    public boolean isMobileNumberExist(String mobileNo, Long id) {
        MapSqlParameterSource sqlParams = new MapSqlParameterSource();
        sqlParams.addValue("mobile", mobileNo);
        sqlParams.addValue("id", id);
        String query = "select id from user where mobile = :mobile and id<>:id";
        List list = npjt.queryForList(query, sqlParams);
        return list.size() > 0;
    }
    public boolean isMobileNumberExist(String mobileNo) {
        MapSqlParameterSource sqlParams = new MapSqlParameterSource();
        sqlParams.addValue("mobile", mobileNo);
        String query = "select id from user where mobile = :mobile";
        List list = npjt.queryForList(query, sqlParams);
        return list.size() > 0;
    }


    public boolean isLoginIdExist(String loginId, Long id) {
        MapSqlParameterSource sqlParams = new MapSqlParameterSource();
        sqlParams.addValue("loginId", loginId);
        sqlParams.addValue("id", id);
        String query = "select id from user where loginid = :loginId and id<>:id";
        List list = npjt.queryForList(query, sqlParams);
        return list.size() > 0;
    }
    public boolean isLoginIdExist(String loginId) {
        MapSqlParameterSource sqlParams = new MapSqlParameterSource();
        sqlParams.addValue("loginId", loginId);
                String query = "select id from user where loginid = :loginId ";
        List list = npjt.queryForList(query, sqlParams);
        return list.size() > 0;
    }
}

