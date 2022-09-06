package com.example.leave.domain.event;


import com.codingapi.springboot.framework.event.IEvent;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;

@Getter
@AllArgsConstructor
public class LeaveCreateEvent implements IEvent {

    private Long leaveId;
    private String content;
    private Long createUserId;
    private Date createTime;


}
