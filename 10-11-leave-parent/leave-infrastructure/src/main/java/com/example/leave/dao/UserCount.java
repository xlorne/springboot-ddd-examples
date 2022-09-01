package com.example.leave.dao;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Setter
@Getter
@Entity(name = "t_user_count")
@AllArgsConstructor
@NoArgsConstructor
public class UserCount {

    @Id
    private int id;

    private int count;

}
