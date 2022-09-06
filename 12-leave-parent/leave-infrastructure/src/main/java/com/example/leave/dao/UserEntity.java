package com.example.leave.dao;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Setter
@Getter
@Entity(name = "t_user")
public class UserEntity {

    @Id
    private Long id;

    private String username;

    private String password;

}
