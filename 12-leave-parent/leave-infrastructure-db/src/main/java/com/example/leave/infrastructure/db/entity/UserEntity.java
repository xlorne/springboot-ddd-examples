package com.example.leave.infrastructure.db.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@Entity
@Table(name = "t_user")
public class UserEntity {

    @Id
    private Long id;


    @Column(unique = true,nullable = false)
    private String username;

    private String password;

}
