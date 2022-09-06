package com.example.spring.data.jpa.api.dao;

import com.example.spring.data.jpa.api.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {
    public List<User> findByProfession(String profession);
    public Integer countByAge(int age);
    public List<User> deleteByName(String name);
    public List<User> findByNameAndProfession(String name, String profession);

    @Query("select u from User u")
    public List<User> getUserCustomQuery();
}
