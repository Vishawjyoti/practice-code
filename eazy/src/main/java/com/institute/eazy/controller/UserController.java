package com.institute.eazy.controller;

import com.institute.eazy.entity.User;
import com.institute.eazy.response.Response;
import com.institute.eazy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping(value = "/eazy")

public class UserController {
    @Autowired
    UserService userService;


@PostMapping(value = "/user/login")
    public Response loginUser(
            @RequestParam(value = "loginId", required = false) String loginId,
            @RequestParam(value = "password",required = false) String password
            ) throws Exception
    {
    Map<String, Object> params = new HashMap<String, Object>();
        System.out.println("API Login Id is     "+ loginId);
        System.out.println("API password is    "+ password );
        params.put("loginIdMap", loginId);
        params.put("passwordMap", password);
        System.out.println(params);
        Response response =  userService.validateLogin(params) ;
        return  response;
    }

@PostMapping(value = "/user/post")// TO CALL ADD USER METHOD WE SEND REQUEST TO THIS PATH
// value = "/user/post" AND IT ACCEPTS USER OBJECT METHOD IS POST

    public Response addUser(@RequestBody User user) throws Exception
{
    Map<String, Object> params = new HashMap<>();
    System.out.println("the user sent by postman is       "+ user);
    params.put("user", user);
    Response response  = userService.addUser(params);
    return response;
}

@GetMapping(value = "/user/get")
    public Response getUser(
            @RequestParam(value = "id", required = false) Long id,
            @RequestParam(value = "firstName", required = false) String firstName,
            @RequestParam(value = "lastName", required = false) String lastName,
            @RequestParam(value = "mobileNo", required = false) String mobileNo,
            @RequestParam(value = "emailId", required = false) String emailId,
            @RequestParam(value = "status", required = false) String status,
            @RequestParam(value = "type", required = false) String type,
            @RequestParam(value = "loginId", required = false) String loginId,
            @RequestParam(value = "gender",required = false) String gender
    )
{
    Map <String, Object> params = new HashMap<>();
    params.put("idMap", id);
    params.put("firstName",firstName);
    params.put("lastName",lastName);
    params.put("mobileNo",mobileNo);
    params.put("emailId",emailId);
    params.put("status",status);
    params.put("type",type);
    params.put("loginId",loginId);
    params.put("gender",gender);

    Response response = userService.getUser(params);
    return response;
    }

@PutMapping(value = "/user/put")
    public Response updateUser(@RequestBody User user){
    Map<String, Object> params = new HashMap<>();
    params.put("user", user);
    Response response = userService.updateUser(params);
    return response;

   }

@PatchMapping(value = "/user/patch")
    public Response patchUser(
        @RequestParam(value = "id", required = false) Long id,
        @RequestParam(value = "firstName", required = false) String firstName,
        @RequestParam(value = "lastName", required = false) String lastName,
        @RequestParam(value = "mobileNo", required = false) String mobileNo,
        @RequestParam(value = "emailId", required = false) String emailId,
        @RequestParam(value = "status", required = false) String status,
        @RequestParam(value = "type", required = false) String type,
        @RequestParam(value = "loginId", required = false) String loginId,
        @RequestParam(value = "gender",required = false) String gender
    )
{
    Map <String, Object> params = new HashMap<>();
    params.put("id", id);
    params.put("firstName",firstName);
    params.put("lastName",lastName);
    params.put("mobileNo",mobileNo);
    params.put("emailId",emailId);
    params.put("status",status);
    params.put("type",type);
    params.put("loginId",loginId);
    params.put("gender",gender);
    System.out.println("id received from postman     "+ id  );
    Response response = userService.patchUser(params);
    return response;
}

@DeleteMapping(value = "/user/delete")
    public Response deleteUser(
            @RequestParam(value = "id", required = false) Long id
    )
    {
    Map <String, Object> params = new HashMap<>();
    params.put("id", id);
        System.out.println("Id send through postman is    "+ id);
    Response response = userService.deleteUser(params);
    return response;
    }
}