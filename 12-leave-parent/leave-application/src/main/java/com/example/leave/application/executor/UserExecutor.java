package com.example.leave.application.executor;

import com.example.leave.application.command.UserCmd;
import com.example.leave.application.exception.UserNotFoundException;
import com.example.leave.application.exception.UserVerifyException;
import com.example.leave.domain.entity.User;
import com.example.leave.domain.exception.ParamVerifyException;
import com.example.leave.domain.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class UserExecutor {

    private final UserRepository userRepository;

    public long createUser(UserCmd.CreateCommand command) throws ParamVerifyException {
        User user = new User(command.getUsername(),command.getPassword());
        user.verify();

        try {
            userRepository.save(user);
        }catch (Exception e){
            throw new ParamVerifyException("username had exist.");
        }

        return user.getId();
    }

    public long login(UserCmd.LoginCommand command) throws UserNotFoundException, UserVerifyException {
        User user = userRepository.getUserByUsername(command.getUsername());
        if(user==null){
            throw new UserNotFoundException(command.getUsername());
        }
        if(user.matchPwd(command.getPassword())){
            return user.getId();
        }
        throw new UserVerifyException(command.getUsername());
    }
}
