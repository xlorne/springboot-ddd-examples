package com.example.leave.infrastructure.db.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Setter
@Getter
@Table(name = "t_leave_order")
@Entity
public class LeaveOrderEntity {

    @Id
    private Long id;

    private String content;
    private Date createTime;
    private Long createUserId;
    private Long approvalUserId;
    private int state;
}
