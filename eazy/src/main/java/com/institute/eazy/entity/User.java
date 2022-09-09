package com.institute.eazy.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class User {

    private Long id;
    private String firstName;
    private String lastName;
    private String gender;
    private String status;
    private String type;
    private String emailId;
    private String mobileNo;
    private String loginId;

}