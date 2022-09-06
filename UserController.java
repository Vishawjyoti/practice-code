package com.example.spring.data.jpa.api.controller;

import com.example.spring.data.jpa.api.entity.User;
import com.example.spring.data.jpa.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("spring-data-jpa")
public class UserController {

    @Autowired
    private UserService service;

@GetMapping("/getUser")
public List<User> getAllUser()
{
    return service.getUser();
}

@GetMapping("/getUserByProfession/{profession}")
    public List<User> getUserByProfession(@PathVariable String profession)
{
    return service.getUserByProfession(profession);
}

@GetMapping("/getCountByAge/{age}")
    public Integer getCountByAge(@PathVariable int age)
{
    return service.getAgeCount(age);
}

@DeleteMapping("/getDeletedUser/{name}")
public List<User> getDeletedUserByName(@PathVariable String name)
{
    return service.getDeletedUser(name);
}

@GetMapping("/multiConditionFetching/{name}/{profession}")
public List<User> getUserOnMultiCondition(@PathVariable String name, @PathVariable String profession)
{
    return service.getUserByNameAndProfession(name, profession);
}

@GetMapping("/getUserFromCustomQuery")
    public List<User> getUserFromCustomQuery()
{
    return service.getUserCustomQuery();
}
}
