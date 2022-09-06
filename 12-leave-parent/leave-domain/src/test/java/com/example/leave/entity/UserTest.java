package com.example.leave.entity;

import com.example.leave.domain.entity.User;
import com.example.leave.domain.exception.ParamVerifyException;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
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

    @CsvFileSource(resources = "/user.csv",delimiter = ',',numLinesToSkip = 1)
    @ParameterizedTest
    void matchPwdSuccess(long id,String username,String password,String verify,boolean result){
        log.info("id:{},username:{},password:{}",id,username,password);
        User user = new User(id,username,password);
        assertEquals(user.matchPwd(verify), result, "密码验证失败.");
    }
}