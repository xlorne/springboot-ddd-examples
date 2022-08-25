package com.example.leaveeample.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class User {

    private Long id;
    private String username;
    private String password;

    public boolean matchPwd(String pwd){
        return password.equals(pwd);
    }

}
