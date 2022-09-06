package com.example.leave.infrastructure.db.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Setter
@Getter
@Entity
@Table(name = "t_user")
public class UserEntity {

    @Id
    private Long id;

    private String username;

    private String password;

}
