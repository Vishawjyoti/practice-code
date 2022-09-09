package com.institute.eazy.service;

import com.institute.eazy.dao.UserDao;
import com.institute.eazy.entity.User;
import com.institute.eazy.response.*;
import com.institute.eazy.util.Util;
import com.institute.eazy.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
@Service
public class UserService {
    @Autowired
    UserDao userDao;

    @Autowired
    UserValidator validator;

    public Response validateLogin(Map<String, Object> params) throws Exception {

        Response response = new Response();
        List<ResponseError> errors = new ArrayList<>();
        response.setErrors(errors);
        response.setStatus(Statuses.LoginFailed_102);
         System.out.println("in user service "+params);
        String loginId = params.get("loginIdMap").toString();

        String password = params.get("passwordMap").toString();
        String encrptPassword = userDao.getPassword(params,errors)  ;
        if(encrptPassword==null)
        {
            return response;
        }
         Boolean validPassword = Util.checkPassword(password, encrptPassword );

         if(validPassword == true) {
          response.setStatus(Statuses.LoginSuccesful_101);
      }
         else {
          errors.add(Errors.PasswordIncorrect_3);
          return response;
      }
    return response;
    }
    public Response addUser(Map<String, Object> params) throws Exception
    {
        Response response = new Response();
        List <ResponseError> errors = new ArrayList<>();
        if(!validator.validateAddUser(params, errors)) {
            response.setErrors(errors);
            response.setStatus(Statuses.UserAddingUnSuccessfull_103);
            return response;
        }
        Integer id = userDao.addUser(params, errors);
        if(id != null)
        response.setStatus(Statuses.UserAddedSuccessfully_104);
        response.setId(id);
        return response;
    }
    public Response getUser(Map<String, Object> params) {
        Response response = new Response();
        List <ResponseError> errors = new ArrayList<>();
        response.setErrors(errors);
        response.setStatus(Statuses.UserFetchingUnSuccessfull_105);
        List <User> userList = userDao.getUser(params, errors);
        response.setStatus(Statuses.UserFetchingSuccessfull_106);
        response.setData(userList);
        return response;
    }
    public Response updateUser(Map<String, Object> params) {
        Response response = new Response();
        List<ResponseError> errors = new ArrayList<ResponseError>();

        if(!validator.validateUpdateUser(params, errors)) {
            response.setErrors(errors);
            response.setStatus(Statuses.UserNotUpdateSuccessfully_107);
            return response;
        }
       Integer id = userDao.updateUser(params,errors);
       response.setId((id));
       response.setStatus(Statuses.UserUpdatedSuccessfully_108);
       return response;
    }
    public Response patchUser(Map<String, Object> params) {
        Response response = new Response();
        List<ResponseError> errors = new ArrayList<ResponseError>();

        if(!validator.validatePatchUser(params, errors)) {
            response.setErrors(errors);
            response.setStatus(Statuses.UserNotUpdateSuccessfully_107);
            return response;
        }
        Long id = userDao.patchUser(params,errors);
        response.setId(Math.toIntExact((id)));
        response.setStatus(Statuses.UserUpdatedSuccessfully_108);
        return response;
    }
    public Response deleteUser(Map<String, Object> params) {Response response = new Response();
        List<ResponseError> errors = new ArrayList<ResponseError>();
        System.out.println("Id received in Service class      " + params.get("id"));
        System.out.println("Id received in Service class      " + validator.validateDeleteUser(params, errors));
        if(!validator.validateDeleteUser(params, errors)) {
            response.setErrors(errors);
            response.setStatus(Statuses.UserDeletionUnsuccessful_109);
            return response;
        }
        Integer noOfRowsAffected = userDao.deleteUser(params);
        if(noOfRowsAffected>0)
        response.setStatus(Statuses.UserDeletedSuccessfully_110);
        return response;
    }
}
