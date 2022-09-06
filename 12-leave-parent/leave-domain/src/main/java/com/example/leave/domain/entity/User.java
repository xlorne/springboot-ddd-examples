package com.example.leave.domain.entity;

import com.example.leave.domain.exception.ParamVerifyException;
import lombok.Getter;
import org.springframework.util.StringUtils;

@Getter
public class User {

    private Long id;
    private String username;
    private String password;


    public User(Long id) {
        this.id = id;
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.id = System.nanoTime();
    }

    public User(Long id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof User){
            User target = (User) obj;
            return target.getId().equals(id);
        }
        return super.equals(obj);
    }

    public void verify() throws ParamVerifyException{
        if(!StringUtils.hasLength(username)){
            throw new ParamVerifyException("username mast not null.");
        }

        if(!StringUtils.hasLength(password)){
            throw new ParamVerifyException("password mast not null.");
        }

    }

    public boolean matchPwd(String pwd){
        return password.equals(pwd);
    }
}
