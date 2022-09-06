package com.example.leave.entity;

import com.example.leave.domain.entity.User;
import com.example.leave.domain.exception.ParamVerifyException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    @Test
    void verify() {
        User user1 = new User("x","");
        assertThrows(ParamVerifyException.class, user1::verify,"参数校验失败.");

        User user2 = new User(1L,"","x");
        assertThrows(ParamVerifyException.class, user2::verify,"参数校验失败.");

        User user3 = new User("x","x");
        assertDoesNotThrow(user3::verify,"参数校验失败.");
    }

    @Test
    void matchPwd() {
        User user = new User("x","123");
        assertTrue(user.getId()>0,"获取id异常.");
        assertNotNull(user.getUsername(),"获取username异常.");
        assertNotNull(user.getPassword(),"获取password异常.");
        assertTrue(user.matchPwd("123"),"密码匹配异常.");
    }
}