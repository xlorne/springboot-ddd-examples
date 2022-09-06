package com.example.leave.infrastructure.db.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Setter
@Getter
@Entity
@Table(name = "t_user_count")
@AllArgsConstructor
@NoArgsConstructor
public class UserCount {

    @Id
    private int id;

    private int count;

}
