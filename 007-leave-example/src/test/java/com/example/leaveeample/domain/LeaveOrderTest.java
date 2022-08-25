package com.example.leaveeample.domain;

import com.example.leaveeample.exception.ParamVerifyException;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LeaveOrderTest {

    @Tag("请假单参数校验测试")
    @Test
    void verifyPass() {
        String content = "明天请假一天";
        User createUser = new User(1L,"xiaoming","123456");
        User approvalUser = new User(2L,"zhangsan","123456");

        LeaveOrder leaveOrder = new LeaveOrder(content,createUser,approvalUser);
        assertDoesNotThrow(()->leaveOrder.verify(),"请假参数校验失败。");
        leaveOrder.createFinish();
    }


    @Tag("请假通过审批业务测试")
    @Test
    void approvalPass() throws ParamVerifyException {
        String content = "明天请假一天";
        User createUser = new User(1L,"xiaoming","123456");
        User approvalUser = new User(2L,"zhangsan","123456");

        LeaveOrder leaveOrder = new LeaveOrder(content,createUser,approvalUser);
        leaveOrder.verify();

        Comment comment = new Comment(approvalUser);

        assertDoesNotThrow(()->leaveOrder.approval(comment),"请假通过审批业务失败。");

    }

    @Tag("请假拒绝审批业务测试")
    @Test
    void approvalNotPass() throws ParamVerifyException {
        String content = "明天请假一天";
        User createUser = new User(1L,"xiaoming","123456");
        User approvalUser = new User(2L,"zhangsan","123456");

        LeaveOrder leaveOrder = new LeaveOrder(content,createUser,approvalUser);
        leaveOrder.verify();

        Comment comment = new Comment("",approvalUser);

        assertThrows(ParamVerifyException.class,()->leaveOrder.approval(comment),"请假通过审批业务失败。");

    }
}