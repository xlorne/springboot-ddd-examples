package com.example.leaveeample.event;

import com.codingapi.springboot.framework.event.IEvent;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;

@Getter
@AllArgsConstructor
public class LeaveApprovalEvent implements IEvent {

    private Long leaveId;
    private String content;
    private Long createUserId;
    private Date createTime;
    private boolean approvalFlag;
    private String approvalContent;

}
