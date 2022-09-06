package com.example.leave.domain.entity;


import com.codingapi.springboot.framework.event.EventPusher;
import com.example.leave.domain.event.LeaveApprovalEvent;
import com.example.leave.domain.event.LeaveCreateEvent;
import com.example.leave.domain.exception.LeaveApprovalNotMatchException;
import com.example.leave.domain.exception.ParamVerifyException;
import lombok.Getter;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
public class LeaveOrder {

    private Long id;
    private String content;
    private Date createTime;
    private User createUser;
    private User approvalUser;
    private State state;
    private List<Comment> comments;

    public LeaveOrder(String content,User createUser,User approvalUser) {
        this.id = System.currentTimeMillis();
        this.comments = new ArrayList<>();
        this.approvalUser = approvalUser;
        this.createUser = createUser;
        this.createTime = new Date();
        this.state = State.CREATE;
        this.content = content;
    }

    public enum State{
        CREATE,APPROVAL,FINISH
    }


    public void verify() throws ParamVerifyException {

        if(!StringUtils.hasText(content)){
            throw new ParamVerifyException("content must not null.");
        }

        if(createUser == null){
            throw new ParamVerifyException("createUser must not null.");
        }

        if(approvalUser == null){
            throw new ParamVerifyException("approvalUser must not null.");
        }

        if(state == State.CREATE && comments.size() > 0 ){
            throw new ParamVerifyException("create state comments must was null.");
        }

        if(state != State.CREATE && comments.size() == 0 ){
            throw new ParamVerifyException("approval,finish state comments must not null.");
        }


    }

    public void createFinish(){
        //push LeaveCreateEvent
        EventPusher.push(new LeaveCreateEvent(id,content,createUser.getId(),createTime));
    }


    public void approval(Comment comment) throws LeaveApprovalNotMatchException, ParamVerifyException {
        if(comment==null){
            throw new ParamVerifyException("comment must not null.");
        }

        if(state != State.CREATE){
            throw new LeaveApprovalNotMatchException("approval state error.");
        }

        comment.verify();
        this.comments.add(comment);
        state = State.APPROVAL;

        //push LeaveApprovalEvent
        EventPusher.push(new LeaveApprovalEvent(id,content,createUser.getId(),createTime,comment.isFlag(),comment.getContent()));
    }

}
