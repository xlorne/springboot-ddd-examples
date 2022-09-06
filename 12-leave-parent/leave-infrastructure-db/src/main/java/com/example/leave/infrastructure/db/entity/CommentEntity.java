package com.example.leave.infrastructure.db.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table
@Setter
@Getter
public class CommentEntity {

    @Id
    private Long id;

    private Long leaveId;

    private String content;

    private Date createTime;

    private Long userId;

    private boolean flag;
}
