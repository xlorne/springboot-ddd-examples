package com.example.leave.infrastructure.db.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "t_leave_comment")
@Setter
@Getter
public class LeaveCommentEntity {

    @Id
    private Long id;

    private Long leaveId;

    private String content;

    private Date createTime;

    private Long userId;

    private boolean flag;
}
