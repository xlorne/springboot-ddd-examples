package com.example.leave.service;

import com.codingapi.springboot.framework.dto.response.SingleResponse;
import com.codingapi.springboot.framework.exception.LocaleMessageException;
import com.example.leave.application.command.UserCmd;
import com.example.leave.application.exception.UserNotFoundException;
import com.example.leave.application.exception.UserVerifyException;
import com.example.leave.application.executor.UserExecutor;
import com.example.leave.jwt.Jwt;
import com.example.leave.jwt.Token;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {

    private final Jwt jwt;

    private final UserExecutor userExecutor;

    public SingleResponse<String> login(UserCmd.LoginCommand command) {
        try {
            long userId = userExecutor.login(command);
            Token token =  jwt.create(userId,command.getUsername());
            return SingleResponse.of(token.getToken());
        } catch (UserNotFoundException | UserVerifyException e) {
            throw new LocaleMessageException("login.error");
        }
    }
}
