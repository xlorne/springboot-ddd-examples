package com.example.leave.controller;

import com.codingapi.springboot.framework.dto.response.MultiResponse;
import com.codingapi.springboot.framework.dto.response.SingleResponse;
import com.codingapi.springboot.framework.exception.LocaleMessageException;
import com.example.leave.command.UserCommand;
import com.example.leave.dao.UserEntity;
import com.example.leave.dto.UserDTO;
import com.example.leave.exception.ParamVerifyException;
import com.example.leave.executor.UserExecutor;
import com.example.leave.jpa.repository.UserCountRepository;
import com.example.leave.jpa.repository.UserEntityRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {
    private final UserExecutor userExecutor;
    private final UserEntityRepository userEntityRepository;

    private final UserCountRepository userCountRepository;

    @PostMapping("/create")
    public SingleResponse<Long> createUser(@RequestBody UserCommand.CreateCommand command){
        try {
            return SingleResponse.of(userExecutor.saveUser(command));
        } catch (ParamVerifyException e) {
            throw new LocaleMessageException("param.verify.error");
        }
    }

    @GetMapping("/list")
    public MultiResponse<UserEntity> list(UserDTO.ListRequest request){
        return MultiResponse.of(userEntityRepository.findAll(request.toPageRequest()));
    }


    @GetMapping("/count")
    public SingleResponse<Integer> count(){
        return SingleResponse.of(userCountRepository.getReferenceById(1).getCount());
    }
}
