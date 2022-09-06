package com.example.leave.controller;

import com.codingapi.springboot.framework.dto.response.MultiResponse;
import com.codingapi.springboot.framework.dto.response.SingleResponse;
import com.codingapi.springboot.framework.exception.LocaleMessageException;
import com.example.leave.application.command.UserCmd;
import com.example.leave.application.executor.UserExecutor;
import com.example.leave.domain.exception.ParamVerifyException;
import com.example.leave.dto.UserDTO;
import com.example.leave.infrastructure.db.entity.UserEntity;
import com.example.leave.infrastructure.db.jpa.repository.UserEntityRepository;
import com.example.leave.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/open/user")
@AllArgsConstructor
public class UserController {
    private final UserExecutor userExecutor;
    private final UserEntityRepository userEntityRepository;

    private final UserService userService;

    @PostMapping("/create")
    public SingleResponse<Long> createUser(@RequestBody UserCmd.CreateCommand command){
        try {
            return SingleResponse.of(userExecutor.createUser(command));
        } catch (ParamVerifyException e) {
            throw new LocaleMessageException("user.verify.error");
        }
    }

    @GetMapping("/list")
    public MultiResponse<UserEntity> list(UserDTO.ListRequest request){
        return MultiResponse.of(userEntityRepository.findAll(request.toPageRequest()));
    }


    @PostMapping("/login")
    public SingleResponse<String> login(@RequestBody UserCmd.LoginCommand command){
        return userService.login(command);
    }

}
