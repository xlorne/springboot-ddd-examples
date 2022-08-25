package com.example.leaveeample.domain;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
class UserTest {


    @CsvFileSource(resources = "/user.csv",delimiter = ',',numLinesToSkip = 1)
    @ParameterizedTest
    void matchPwdSuccess(long id,String username,String password,String verify,boolean result){
        log.info("id:{},username:{},password:{}",id,username,password);
        User user = new User(id,username,password);
        assertEquals(user.matchPwd(verify), result, "密码验证失败.");
    }

}